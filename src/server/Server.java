package server;

import java.io.*;
import java.net.*;

public class Server {

    private static final String EXIT_CODE = "987654321";

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(4999);
            System.out.println("Server started");
            Socket clientSocket = serverSocket.accept();

            if(clientSocket.isConnected()) {
                System.out.println("Client connected");
            }

            BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());

            String message;
            while(true) {
                message = fromClient.readLine();

                if(!message.equals(EXIT_CODE)) {
                    System.out.println("From client: " + message);
                    toClient.println(message);
                    toClient.flush();
                } else {
                    throw new SocketException();
                }
            }
        } catch(SocketException e) {
            System.out.println("Client disconnected");
            System.exit(0);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
