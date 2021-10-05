package reza.decoder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class DecodeEngine {
    private String lineSeparator = " ";
    private String charSeparator = ";";
    private String outputFileName = null;
    private String decodingMethod = null;
    private byte[] fileData;

    public boolean decode(String encodedString){
        //separate the data from the encoded string
        String[] dataArray = encodedString.split(lineSeparator);
        //set output file name
        outputFileName = dataArray[0];
        //set decoding method
        decodingMethod = dataArray[1];
        //crate new instance of output file
        File outputFile = new File(outputFileName);

        //decode with the appropriate method
        switch (decodingMethod){
            case "base64":
                useBase64Decoding(dataArray[2]);
                break;
            case "character":
                useCharacterDecoding(dataArray[2]);
                break;
            default:
                System.out.println("Decoding Method Not Supported!!");
                return false;
        }

        try {
            //create writer
            BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(outputFile));
            //write to binary file
            writer.write(fileData);
            //close writer stream
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        //prompt success message
        System.out.println("Decoding successful.");
        System.out.println("Output filename: " + outputFileName);

        //return ture
        return true;
    }

    private void useBase64Decoding(String data){
        fileData = Base64.getDecoder().decode(data);
    }

    private void useCharacterDecoding(String data){
        String[] charArray = data.split(charSeparator);
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) byteArray[i] = Byte.parseByte(charArray[i]);

        fileData = byteArray;
    }
}
