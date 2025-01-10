package SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public void run() throws IOException {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);

        while (true) {
            try {
                System.out.println("SingleThreaded.Server is listening on port " + port);
                Socket acceptConnection = socket.accept();
                System.out.println("Connection accepted from " + acceptConnection.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(acceptConnection.getOutputStream());
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptConnection.getInputStream()));
                toClient.println("Hello from server");
                toClient.close();
                fromClient.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
