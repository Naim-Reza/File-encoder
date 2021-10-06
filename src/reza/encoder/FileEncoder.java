package reza.encoder;

import java.io.*;
import java.net.Socket;

public class FileEncoder {
    private static String portSeparator = ":";
    private static String IP = "127.0.0.1"; //default IP
    private static int PORT = 3000; //default PORT
    private static Socket socket;
    private static PrintWriter writer;
    private static BufferedReader reader;


    public static void main(String[] args) {
        String encodedString;
        //create instance of EncodeFile class
        EncodeFile encoder = new EncodeFile();
        //create an instance of Utility class to view help text
        Utility utility = new Utility();

        try{
            File file = new File(args[0]);
            String encodingMethod = args[1];

            //if server address is provided use that otherwise use the default IP and Port to connect
            if (args.length > 2){
                String[] serverInfo = args[2].split(portSeparator);

                //set IP and PORT
                setIP(serverInfo[0]);
                setPORT(Integer.parseInt(serverInfo[1]));
            }

            //read and encode the file
            encodedString = encoder.encode(file, encodingMethod);
            //check if the output string is empty or not
            if (encodedString.equals("")) {
                utility.displayHelp("Error Encoding File...!!!");
                return;
            }

            //create connection with the server
            if (!connect()) {
                utility.displayHelp();
                return;
            }
            //send the encoded string to the server
            write(encodedString);
            //read response from the server
            getResponse();


        } catch (ArrayIndexOutOfBoundsException e){
            utility.displayHelp();
        }
    }

    //establish network connection
    public static boolean connect(){
        try{
            socket = new Socket(getIP(), getPORT());
            System.out.println("Connected to " + socket.getLocalAddress() + " at port: " + socket.getPort());
            return true;
        } catch (IOException e){
            System.err.println("Error Connecting to the server...!!!");
            e.printStackTrace();
            return false;

        }
    }

    //write with the output stream
    public static void write(String data){
        try{
            System.out.println("Writing to server...");
            writer = new PrintWriter(socket.getOutputStream());
            writer.write(data);
            writer.println();
            writer.flush();
        } catch (Exception e){
            System.err.println("Error writing to server...!!!");
            e.printStackTrace();
        }
    }

    //get response from the server
    public static void getResponse(){
        try {
            System.out.println("Reading from server....");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = reader.readLine();
            System.out.println(message);

        } catch (IOException e){
            System.out.println("Error reading response from server...");
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
