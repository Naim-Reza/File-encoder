package reza.encoder;

import java.io.*;
import java.util.Base64;

public class EncodeFile {
    private String encodedString = "";
    private String lineSeparator = " ";
    private String charSeparator = ";";
    private String outputString = "";

    public String encode(File file, String encodingMethod) {
        try{
            //check if the provided file is a file or not
            if (!file.isFile()) return "";

            //create reader
            BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
            byte[] data = new byte[(int) file.length()];
            //read bytes and store into byte array
            reader.read(data);
            //close reader
            reader.close();

            //encode file with chosen method
            switch (encodingMethod){
                case "base64":
                    useBase64Encoding(data);
                    break;
                case "character":
                    useCharacterEncoding(data);
                    break;
                default:
                    System.out.println("Encoding method not available..");
                    return "";
            }

            //generate output string
            outputString = file.getName() + lineSeparator + encodingMethod + lineSeparator + encodedString;


        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return outputString;
    }

    private void useBase64Encoding(byte[] data){
        encodedString = Base64.getEncoder().encodeToString(data);
    }

    private void useCharacterEncoding(byte[] data){
        //reset encoded string to empty string
        encodedString = "";
        //encode and store to encoded string
        for (byte b : data) encodedString += (Byte.toString(b) + charSeparator);
    }
}
