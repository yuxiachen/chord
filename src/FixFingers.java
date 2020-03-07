import java.net.InetSocketAddress;
import java.util.Random;

// Fixfingers thread runs in the background, and keeps selecting one of the entry of the finger table and update/fix it.

public class FixFingers extends Thread{

    boolean alive;
    private Node node;

    public FixFingers (Node node) {
        this.node = node;
        alive = true;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (alive) {
            // randomly generate a int between [0,5] and updated corresponding entry of node's finger table
            int i = random.nextInt(6);
            InetSocketAddress ithFinger = node.find_successor(Helper.ithStart(node.getId(), i));
            node.updateFingers(i, ithFinger);
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
