package reza.decoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DecodeServer {
    int PORT = 3000;


    public static void main(String[] args) {
        new DecodeServer().listen();
    }

    public void listen(){

        try{
            //create serversocket
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Listening to port " + PORT);

            while (true){
                //accept connections
                Socket clientSocket = serverSocket.accept();
                //create a new thread with the connection
                Thread thread = new Thread(new ClientHandler(clientSocket));
                //run the thread
                thread.start();
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }



    public class ClientHandler implements Runnable{
        BufferedReader reader;
        PrintWriter writer;
        Socket socket;

        String encodedString = "";
        DecodeEngine decoder = new DecodeEngine();

        //constructor
        public ClientHandler(Socket clientSocket){
            socket = clientSocket;
        }

        @Override
        public void run() {
            try{
                //read encoded string
                System.out.println("Reading Data.....");
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                encodedString = reader.readLine();

                System.out.println("Decoding...");


                //decode the string and save local
                boolean decoded = decoder.decode(encodedString);
                //check if decoding fails
                if (!decoded) {
                    sendResponse("Decoding Failed...!!!");
                    return;
                }
                //send success response
                sendResponse("Decoding Successful...");


            } catch (IOException e){
                e.printStackTrace();
            }

        }

        public void sendResponse(String message){
            try {
                writer = new PrintWriter(socket.getOutputStream());
                writer.println(message);
                writer.flush();
                System.out.println("Response sent to client...");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
