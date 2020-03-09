import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

//Listener thread keeps listening at a port and new a talker thread to process the coming request

public class Listener extends Thread {

    private Node node;
    private ServerSocket serverSocket;
    private boolean alive;

    public Listener (Node node) {
        this.node = node;
        alive = true;
        InetSocketAddress localAddress = node.getAddress();
        int port = localAddress.getPort();

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Cannot open listener at port: " + port + ". " + e.getMessage());
        }
    }

    @Override
    public void run() {
        Socket socket = null;
        while (alive && !serverSocket.isClosed()) {
            try {
                socket = serverSocket.accept();
                new Thread(new Speaker(socket, node)).start();
            } catch (IOException e) {
                System.err.println("Cannot accepting connection: " + e.getMessage());
            }
        }

    }

    public void toDie() {
        alive = false;
    }
}