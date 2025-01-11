package MultiThreaded;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.function.Consumer;

public class Server {

        public Consumer<Socket> getConsume() {
        return (clientSOcket) -> {
            try {
                PrintWriter toCLinet = new PrintWriter(clientSOcket.getOutputStream());
                toCLinet.println("Hello from server");
                toCLinet.close();
                clientSOcket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
        int port = 8010;
        Server server = new Server();
        try {

            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Server is listenning on port " + port);
            while (true) {
                Socket acceptedSocket = serverSocket.accept();
                Thread thread = new Thread(() -> server.getConsume().accept(acceptedSocket));
                thread.start();
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
