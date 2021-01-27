package client;

import java.io.*;
import java.net.*;

public class Client {

    private static final String EXIT_CODE = "987654321";

    public static void main(String[] args) {

        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 4999);

            if(socket.isConnected()) {
                System.out.println("Connected to server.");
            }

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter toServer = new PrintWriter(socket.getOutputStream());

            String message;
            while(true) {
                System.out.print("Me: ");
                message = console.readLine();

                if(!message.equals("quit")) {
                    // sending message
                    toServer.println(message);
                    toServer.flush();

                    // receiving echoed message
                    message = fromServer.readLine();
                    System.out.println("From server: " + message);
                } else {
                    toServer.println(EXIT_CODE);
                    System.out.println("Disconnected from server");
                    System.exit(0);
                }
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }



}
