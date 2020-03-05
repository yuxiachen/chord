import java.net.InetSocketAddress;
import java.util.HashMap;

public class Node {

    private long localId;
    private InetSocketAddress localAddress;
    private InetSocketAddress predecessor;
    private String predecessorText;
    private HashMap<Integer, InetSocketAddress> finger;
    private Listener listener;
    private Stabilize stabilize;
    private FixFingers fix_fingers;
    private AskPredecessor ask_predecessor;

    private int[] ithStarts = new int[6];
    private InetSocketAddress[] fingers = new InetSocketAddress[6];
    private String[] IDs = new String[6];

    public Node (InetSocketAddress address) {

        localAddress = address;
        localId = Helper.hashSocketAddress(localAddress);
        // initialize an empty finge table
        finger = new HashMap<Integer, InetSocketAddress>();
        for (int i = 0; i < 6; i++) {
            updateIthFinger (i, null);
        }

        predecessor = null;
        listener = new Listener(this);
        stabilize = new Stabilize(this);
        fix_fingers = new FixFingers(this);
        ask_predecessor = new AskPredecessor(this);
    }

    public int[] getIthStarts() {
        return ithStarts;
    }

    public InetSocketAddress[] getFingers() {
        return fingers;
    }

    public String[] getIDs() {
        return IDs;
    }

    public String getPredecessorText() {
        return predecessorText;
    }

    // Create or join a ring
    public boolean join (InetSocketAddress message) {
        if (message != null && !message.equals(localAddress)) {
            InetSocketAddress successor = Helper.requestAddress(message, "FINDSUCC_" + localId);
            if (successor == null)  {
                System.out.println("\nCannot find node you are trying to message. Please exit.\n");
                return false;
            }
            updateIthFinger(1, successor);
        }
        listener.start();
        stabilize.start();
        fix_fingers.start();
        ask_predecessor.start();
        return true;
    }

    // Notify successor that this node should be its predecessor
    public String notify(InetSocketAddress successor) {
        if (successor!=null && !successor.equals(localAddress))
            return Helper.sendRequest(successor, "IAMPRE_"+localAddress.getAddress().toString()+":"+localAddress.getPort());
        else
            return null;
    }

    // Being notified by another node, set it as my predecessor if it is.
    public void notified (InetSocketAddress newPre) {
        if (predecessor == null || predecessor.equals(localAddress)) {
            this.setPredecessor(newPre);
        }
        else {
            long oldpre_id = Helper.hashSocketAddress(predecessor);
            long local_relative_id = Helper.computeRelativeId(localId, oldpre_id);
            long newpre_relative_id = Helper.computeRelativeId(Helper.hashSocketAddress(newPre), oldpre_id);
            if (newpre_relative_id > 0 && newpre_relative_id < local_relative_id)
                this.setPredecessor(newPre);
        }
    }

    // Ask current node to find id's successor.
    public InetSocketAddress find_successor (long identifier) {
        // initialize return value as this node's successor (might be null)
        InetSocketAddress ret = this.getSuccessor();
        InetSocketAddress pre = find_predecessor(identifier);
        // if other node found, ask it for its successor
        if (!pre.equals(localAddress))
            ret = Helper.requestAddress(pre, "YOURSUCC");
        // if ret is still null, set it as local node, return
        if (ret == null)
            ret = localAddress;

        return ret;
    }

    // Ask current node to find id's predecessor
    private InetSocketAddress find_predecessor (long identifier) {
        InetSocketAddress n = this.localAddress;
        InetSocketAddress n_successor = this.getSuccessor();
        InetSocketAddress most_recently_alive = this.localAddress;
        long n_successor_relative_id = 0;
        if (n_successor != null)
            n_successor_relative_id = Helper.computeRelativeId(Helper.hashSocketAddress(n_successor), Helper.hashSocketAddress(n));
        long findid_relative_id = Helper.computeRelativeId(identifier, Helper.hashSocketAddress(n));
        while (!(findid_relative_id > 0 && findid_relative_id <= n_successor_relative_id)) {
            // save current node
            InetSocketAddress pre_n = n;
            // if current node is local node, find my closest
            if (n.equals(this.localAddress)) {
                n = this.closest_preceding_finger(identifier);
            }
            // else current node is remote node, sent request to it for its closest
            else {
                InetSocketAddress result = Helper.requestAddress(n, "CLOSEST_" + identifier);
                // if fail to get response, set n to most recently
                if (result == null) {
                    n = most_recently_alive;
                    n_successor = Helper.requestAddress(n, "YOURSUCC");
                    if (n_successor==null) {
                        System.out.println("It's not possible.");
                        return localAddress;
                    }
                    continue;
                } else if (result.equals(n)) {// if n's closest is itself, return n
                    return result;
                } else { // n's closest is other node "result"
                    // set n as most recently alive
                    most_recently_alive = n;
                    // ask "result" for its successor
                    n_successor = Helper.requestAddress(result, "YOURSUCC");
                    // if we can get its response, then "result" must be our next n
                    if (n_successor!=null) {
                        n = result;
                    } else {// n sticks, ask n's successor
                        n_successor = Helper.requestAddress(n, "YOURSUCC");
                    }
                }
                // compute relative ids for while loop judgement
                n_successor_relative_id = Helper.computeRelativeId(Helper.hashSocketAddress(n_successor), Helper.hashSocketAddress(n));
                findid_relative_id = Helper.computeRelativeId(identifier, Helper.hashSocketAddress(n));
            }
            if (pre_n.equals(n))
                break;
        }
        return n;
    }

    // Return closest finger preceding node.
    public InetSocketAddress closest_preceding_finger (long findid) {
        long findid_relative = Helper.computeRelativeId(findid, localId);
        // check from last item in finger table
        for (int i = 5; i >= 0; i--) {
            InetSocketAddress ith_finger = finger.get(i);
            if (ith_finger == null) {
                continue;
            }
            long ith_finger_id = Helper.hashSocketAddress(ith_finger);
            long ith_finger_relative_id = Helper.computeRelativeId(ith_finger_id, localId);
            // if its relative id is the closest, check if its alive
            if (ith_finger_relative_id > 0 && ith_finger_relative_id < findid_relative)  {
                String response  = Helper.sendRequest(ith_finger, "KEEP");
                //it is alive, return it
                if (response!=null &&  response.equals("ALIVE")) {
                    return ith_finger;
                }
                // else, remove its existence from finger table
                else {
                    updateFingers(-2, ith_finger);
                }
            }
        }
        return localAddress;
    }

    /**
     * Update the finger table based on parameters.
     * Synchronize, all threads trying to modify
     * finger table only through this method.
     */
    public synchronized void updateFingers(int i, InetSocketAddress value) {
        // valid index in [0, 5], just update the ith finger
        if (i >= 0 && i < 6) {
            updateIthFinger(i, value);
        } else if (i == -1) {// caller wants to delete
            deleteSuccessor();
        } else if (i == -2) {// caller wants to delete a finger in table
            deleteCertainFinger(value);
        } else if (i == -3) {// caller wants to fill successor
            fillSuccessor();
        }
    }

    // Update ith finger in finger table using new value
    private void updateIthFinger(int i, InetSocketAddress value) {
        finger.put(i, value);
        // if the updated one is successor, notify the new successor
        if (i == 0 && value != null && !value.equals(localAddress)) {
            notify(value);
        }
    }

    // Delete successor and all following fingers equal to successor
    private void deleteSuccessor() {
        InetSocketAddress successor = getSuccessor();
        //nothing to delete, just return
        if (successor == null)
            return;
        // find the last existence of successor in the finger table
        int i = 6;
        for (i = 6; i > 0; i--) {
            InetSocketAddress ithfinger = finger.get(i);
            if (ithfinger != null && ithfinger.equals(successor))
                break;
        }
        // delete it, from the last existence to the first one
        for (int j = i; j >= 1 ; j--) {
            updateIthFinger(j, null);
        }
        // if predecessor is successor, delete it
        if (predecessor!= null && predecessor.equals(successor))
            setPredecessor(null);
        // try to fill successor
        fillSuccessor();
        successor = getSuccessor();
        // if successor is still null or local node,
        // and the predecessor is another node, keep asking
        // it's predecessor until find local node's new successor
        if ((successor == null || successor.equals(successor)) && predecessor!=null && !predecessor.equals(localAddress)) {
            InetSocketAddress p = predecessor;
            InetSocketAddress p_pre = null;
            while (true) {
                p_pre = Helper.requestAddress(p, "YOURPRE");
                if (p_pre == null)
                    break;
                // if p's predecessor is node is just deleted,
                // or itself (nothing found in p), or local address,
                // p is current node's new successor, break
                if (p_pre.equals(p) || p_pre.equals(localAddress)|| p_pre.equals(successor)) {
                    break;
                } else { // else, keep asking
                    p = p_pre;
                }
            }
            // update successor
            updateIthFinger(1, p);
        }
    }

    // Delete a node from the finger table, here "delete" means deleting all existence of this node
    private void deleteCertainFinger(InetSocketAddress f) {
        for (int i = 6; i > 0; i--) {
            InetSocketAddress ithfinger = finger.get(i);
            if (ithfinger != null && ithfinger.equals(f))
                finger.put(i, null);
        }
    }

    // Try to fill successor with candidates in finger table or even predecessor
    private void fillSuccessor() {
        InetSocketAddress successor = this.getSuccessor();
        if (successor == null || successor.equals(localAddress)) {
            for (int i = 2; i <= 6; i++) {
                InetSocketAddress ithfinger = finger.get(i);
                if (ithfinger!=null && !ithfinger.equals(localAddress)) {
                    for (int j = i-1; j >=1; j--) {
                        updateIthFinger(j, ithfinger);
                    }
                    break;
                }
            }
        }
        successor = getSuccessor();
        if ((successor == null || successor.equals(localAddress)) && predecessor!=null && !predecessor.equals(localAddress)) {
            updateIthFinger(1, predecessor);
        }
    }

    public void clearPredecessor () {
        setPredecessor(null);
    }

    // Set predecessor using a new value.
    private synchronized void setPredecessor(InetSocketAddress pre) {
        predecessor = pre;
    }

    public long getId() {
        return localId;
    }

    public InetSocketAddress getAddress() {
        return localAddress;
    }

    public InetSocketAddress getPredecessor() {
        return predecessor;
    }

    public InetSocketAddress getSuccessor() {
        if (finger != null && finger.size() > 0) {
            return finger.get(1);
        }
        return null;
    }

    public void printNeighbors () {
        InetSocketAddress successor = finger.get(1);
        // if it cannot find both predecessor and successor
        if ((predecessor == null || predecessor.equals(localAddress)) && (successor == null || successor.equals(localAddress))) {
            predecessorText = "Yourself";
        } else { // else, it can find either predecessor or successor
            if (predecessor != null) {
                predecessorText = predecessor.getAddress().toString().substring(1);
            } else {
                predecessorText = "updating...";
            }
        }
    }

    public void printDataStructure () {
        for (int i = 0; i < 6; i++) {
            ithStarts[i] = Helper.ithStart(Helper.hashSocketAddress(localAddress), i);
            if (finger.get(i) != null) {
                fingers[i] = finger.get(i);
                IDs[i] = Helper.hexIdAndPosition(fingers[i]);
            } else {
                fingers[i] = null;
                IDs[i] = "Null";
            }
        }
    }

    // Stop this node's all threads.
    public void stopAllThreads() {
        if (listener != null)
            listener.toDie();
        if (fix_fingers != null)
            fix_fingers.toDie();
        if (stabilize != null)
            stabilize.toDie();
        if (ask_predecessor != null)
            ask_predecessor.toDie();
    }
}