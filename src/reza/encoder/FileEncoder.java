package reza.encoder;

import java.io.File;

public class FileEncoder {
    private static String portSeparator = ":";

    public static void main(String[] args) {

        try{
            File file = new File(args[0]);
            String encodingMethod = args[1];
            String[] serverInfo = args[2].split(portSeparator);

            //read and encode the file


        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
}
