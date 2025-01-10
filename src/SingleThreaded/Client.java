package SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public void run() throws IOException {
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, port);
        PrintWriter toSocket = new PrintWriter(socket.getOutputStream());
        BufferedReader fromSocet = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toSocket.println("Hello World from socket "+socket.getLocalSocketAddress());
        String line = fromSocet.readLine();
        toSocket.close();
        fromSocet.close();
        socket.close();
        System.out.println("Response from the socket: " + line);
    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
