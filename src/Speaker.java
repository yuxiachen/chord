import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

//Speaker thread processes request accepted by listener and writes response to socket.

public class Speaker implements Runnable{

    Socket socket;
    private Node node;

    public Speaker(Socket socket, Node node) {
        this.socket = socket;
        this.node = node;
    }

    public void run() {
        try {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            String request = Util.inputStreamToString(in);
            String response = processRequest(request);
            if (response != null) {
                out.write(response.getBytes());
            }
            in.close();
            out.close();
        } catch (IOException e) {
            System.err.println("Server is not able to handle incoming request at port + " + socket.getPort() + ": " + e.getMessage());
        }
    }

    private String processRequest(String request) {
        InetSocketAddress result = null;
        String ret = null;
        // if the request is null, return null
        if (request  == null) {
            return null;
        }
        // if request is looking for the closest node of an id, call the closest_preceding_finger method in node to
        // get the alive closet node in the finger table
        if (request.startsWith("CLOSEST")) {
            int id = Integer.parseInt(request.split("_")[1]);
            result = node.closest_preceding_finger(id);
            String ip = result.getAddress().toString();
            int port = result.getPort();
            ret = "MYCLOSEST_"+ip+":"+port;
        } else if (request.startsWith("YOURSUCC")) {
            // if request is looking for the successor of local node, call the getSuccessor method to get the first node in the finger table
            result = node.getSuccessor();
            if (result != null) {
                String ip = result.getAddress().toString();
                int port = result.getPort();
                ret = "MYSUCC_"+ip+":"+port;
            }
            else {
                ret = "NOTHING";
            }
        } else if (request.startsWith("YOURPRE")) {
            // if request is looking for the predecessor of local node, return the predecessor of the local node
            result = node.getPredecessor();
            if (result != null) {
                String ip = result.getAddress().toString();
                int port = result.getPort();
                ret = "MYPRE_"+ip+":"+port;
            } else {
                ret = "NOTHING";
            }
        } else if (request.startsWith("FINDSUCC")) {
            // if request is looking for the successor of an id,
            // recursively call the find_successor method of node in the ring to get the successor of the given id
            int id = Integer.parseInt(request.split("_")[1]);
            result = node.find_successor(id);
            String ip = result.getAddress().toString();
            int port = result.getPort();
            ret = "FOUNDSUCC_"+ip+":"+port;
        } else if (request.startsWith("IAMPRE")) {
            // if request is to notify the local node that it's local node's predecessor,
            // set local node's predecessor as this caller node.
            InetSocketAddress new_pre = Util.createSocketAddress(request.split("_")[1]);
            node.notified(new_pre);
            ret = "NOTIFIED";
        } else if (request.startsWith("KEEP")) {
            // if the coming request is checking local node is alive or not, return ALIVE
            ret = "ALIVE";
        }
        return ret;
    }
}