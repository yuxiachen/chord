import java.net.InetSocketAddress;

public class Stabilization extends Thread {
    private Node localNode;
    private boolean alive;

    public Stabilization(Node local) {
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
                InetSocketAddress nd = Util.requestAddress(successor, "YOURPRE");
                // if cannot get the address of successor, delete it
                if (nd == null) {
                    localNode.updateFingers(-1, null);
                } else if (!nd.equals(successor)) { // if successor's predecessor is not the successor itself
                    int localID = Util.hashSocketAddress(localNode.getAddress());
                    int successor_relative_id = Util.computeRelativeId(Util.hashSocketAddress(successor), localID);
                    int x_node_relative_id = Util.computeRelativeId(Util.hashSocketAddress(nd),localID);
                    if (x_node_relative_id>0 && x_node_relative_id < successor_relative_id) {
                        localNode.updateFingers(0, nd);
                    }
                } else { // if successor's predecessor is the successor itself, notify successor
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
