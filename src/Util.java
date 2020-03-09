import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Util {

    private static HashMap<Integer, Long> powerOfTwo;


    public Util() {
        //fill the powerOfTwo table
        powerOfTwo = new HashMap<Integer, Long>();
        long base = 1;
        for (int i = 0; i <= 32; i++) {
            powerOfTwo.put(i, base);
            base *= 2;
        }
    }

    // Use SHA-1 algorithm to calculate the hashcode
    private static long hashHashCode (String key) {
        int maxNode = (int)Math.pow(2, 6);
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Cannot get SHA-1 obj");
            e.printStackTrace();
        }
        md.reset();
        md.update((key).getBytes(StandardCharsets.UTF_8));
        byte[] hashBytes = md.digest();
        BigInteger hashValue = new BigInteger(1, hashBytes);
        return (long) Math.abs(hashValue.intValue()) % maxNode;
    }

    public static long hashSocketAddress (InetSocketAddress addr) {
        return hashHashCode(addr.toString());
    }

    public static long hashString (String s) {
        return hashHashCode(s);
    }

    //send request to server and post-process the response
    public static InetSocketAddress requestAddress (InetSocketAddress server, String req) {

        // invalid input
        if (server == null || req == null) {
            return null;
        }

        // send request
        String response = sendRequest(server, req);

        if (response == null) {
            return null;
        }

        // if server cannot find nothing, return server itself
        else if (response.startsWith("NOTHING")) {
            return server;
        }

        // server find something,
        // using response to create, might fail then and return null
        else {
            InetSocketAddress ret = Util.createSocketAddress(response.split("_")[1]);
            return ret;
        }
    }
    // use talk socket to communication
    public static String sendRequest(InetSocketAddress server, String req) {

        // invalid input
        if (server == null || req == null)
            return null;

        Socket talk = null;

        // open talkSocket at server side, output request to this socket
        try {
            talk = new Socket(server.getAddress(),server.getPort());
            PrintStream output = new PrintStream(talk.getOutputStream());
            output.println(req);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try {
            Thread.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // get input stream, try to read something from it
        InputStream input = null;
        try {
            input = talk.getInputStream();
        } catch (IOException e) {
            System.out.println("Cannot get input stream from server");
        }
        String response = Util.inputStreamToString(input);

        // try to close socket
        try {
            talk.close();
        } catch (IOException e) {
            System.out.println("Cannot close talk socket");
        }
        return response;
    }

    //split input, use ip addr and port to create InetSocketAddress
    public static InetSocketAddress createSocketAddress (String addr) {

        // invalid input
        if (addr == null) {
            return null;
        }

        // split input into ip address and port number
        String[] split = addr.split(":");


        String ip = split[0];
        if (ip.startsWith("/")) {
            ip = ip.substring(1);
        }

        //parse ip address
        InetAddress inet_ip = null;
        try {
            inet_ip = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            System.out.println("Cannot create ip address: "+ip);
            e.printStackTrace();
            return null;
        }

        // parse port number
        String port = split[1];
        int inet_port = Integer.parseInt(port);

        return new InetSocketAddress(inet_ip, inet_port);
    }

    // read the first line of input stream
    public static String inputStreamToString (InputStream in) {

        // invalid input
        if (in == null) {
            return null;
        }

        // try to read line from input stream
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = null;
        try {
            line = br.readLine();
        } catch (IOException e) {
            System.out.println("Cannot read from input stream");
            return null;
        }

        return line;
    }


    //get the relativeId, used in stabilize function
    public static long computeRelativeId (long universal, long local) {
        long relativeId = universal - local;
        if (relativeId < 0) {
            relativeId += powerOfTwo.get(6);
        }
        return relativeId;
    }

    // print the specific format
    public static String hexIdAndPosition (InetSocketAddress addr) {
        long hash = hashSocketAddress(addr);
        return (hash+"("+hash*100/ Util.getPowerOfTwo(6)+"%)");
    }



    public static int ithStart (long nodeid, int i) {
        return Math.toIntExact((nodeid + powerOfTwo.get(i)) % powerOfTwo.get(6));
    }

    // get the power of 2
    public static long getPowerOfTwo (int k) {
        return powerOfTwo.get(k);
    }



}