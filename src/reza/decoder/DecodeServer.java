package reza.decoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class DecodeServer {
    int PORT = 3000;

    public void listen(){
        try{
            //create serversocket
            ServerSocket serverSocket = new ServerSocket(PORT);

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
        Socket socket;

        String encodedString = "";
        DecodeEngine decoder = new DecodeEngine();

        //constructor
        public ClientHandler(Socket clientSocket){
            try{
                socket = clientSocket;
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try{
                //read encoded string
                String line = null;
                while ((line = reader.readLine()) != null) encodedString += line;
                reader.close();

                //decode the string and save local
                decoder.decode(encodedString);

            } catch (IOException e){
                e.printStackTrace();
            }

        }
    }

}
