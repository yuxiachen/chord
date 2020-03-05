import java.net.InetSocketAddress;

public class Stabilize extends Thread {
    private Node localNode;
    private boolean alive;

    public Stabilize(Node local) {
        localNode = local;
        alive = true;
    }

    @Override
    public void run() {
        while (alive) {
            InetSocketAddress successor = localNode.getSuccessor();
            if (successor == null || successor.equals(localNode.getAddress())) {
                localNode.updateFingers(-3, null); //call fillSuccessor()
            }
            successor = localNode.getSuccessor();
            if (successor != null && !successor.equals(localNode.getAddress())) {
                InetSocketAddress nd = Helper.requestAddress(successor, "YOURPRE");
                // if cannot get the address of successor, delete it
                if (nd == null) {
                    localNode.updateFingers(-1, null);
                }
                // if successor's predecessor is not the successor itself
                else if (!nd.equals(successor)) {
                    long localID = Helper.hashSocketAddress(localNode.getAddress());
                    long successor_relative_id = Helper.computeRelativeId(Helper.hashSocketAddress(successor), localID);
                    long x_node_relative_id = Helper.computeRelativeId(Helper.hashSocketAddress(nd),localID);
                    if (x_node_relative_id>0 && x_node_relative_id < successor_relative_id) {
                        localNode.updateFingers(1, nd);
                    }
                }
                // if successor's predecessor is the successor itself, notify successor
                else {
                    localNode.notify(successor);
                }
            }
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void toDie() {
        alive = false;
    }

}
