import java.net.InetSocketAddress;
import java.util.Random;

/**
 * Fixfingers thread runs in the background, and keeps selecting one of the entry of the
 * finger table and update/fix it.
 */

public class FixFingers extends Thread{

    private Node local;
    Random random;
    boolean alive;

    public FixFingers (Node node) {
        local = node;
        alive = true;
        random = new Random();
    }

    @Override
    public void run() {
        while (alive) {
            int i = random.nextInt(6);
            InetSocketAddress ithfinger = local.find_successor(Helper.ithStart(local.getId(), i));
            local.updateFingers(i, ithfinger);
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
