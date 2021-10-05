package reza.encoder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class FileEncoder {
    private static String portSeparator = ":";
    private static String IP = "127.0.0.1"; //default IP
    private static int PORT = 5000; //default PORT
    private static Socket socket;
    private static PrintWriter writer;


    public static void main(String[] args) {
        String encodedString;
        //create instance of EncodeFile class
        EncodeFile encoder = new EncodeFile();

        try{
            File file = new File(args[0]);
            String encodingMethod = args[1];
            String[] serverInfo = args[2].split(portSeparator);

            //set IP and PORT
            setIP(serverInfo[0]);
            setPORT(Integer.parseInt(serverInfo[1]));

            //read and encode the file
            encodedString = encoder.encode(file, encodingMethod);
            //check if the output string is empty or not
            if (encodedString.equals("")) return;

            //create connection with the server
            connect();
            //send the encoded string to the server
            write(encodedString);


        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    //establish network connection
    public static void connect(){
        try{
            socket = new Socket(getIP(), getPORT());
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("Connected to " + getIP() + " at port: " + getPORT());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //write with the output stream
    public static void write(String data){
        try{
            writer.write(data);
            writer.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getIP() {
        return IP;
    }

    public static void setIP(String IP) {
        FileEncoder.IP = IP;
    }

    public static int getPORT() {
        return PORT;
    }

    public static void setPORT(int PORT) {
        FileEncoder.PORT = PORT;
    }
}
