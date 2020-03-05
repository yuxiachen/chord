import java.net.InetSocketAddress;

/**
 *  Ask Predecessor thread periodically sends request to node's Predecessor and set node's
 *  predecessor status accordingly.
 */
public class AskPredecessor extends Thread {
    private Node node;
    private boolean alive;

    public AskPredecessor(Node node) {
        this.node = node;
        alive = true;
    }

    @Override
    public void run() {
        while (alive) {
            InetSocketAddress predecessor = node.getPredecessor();
            if (predecessor != null) {
                String response = Helper.sendRequest(predecessor, "KEEP");
                if (response == null || !response.equals("ALIVE")) {
                    node.clearPredecessor();
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void toDie() {
        alive = false;
    }
}
