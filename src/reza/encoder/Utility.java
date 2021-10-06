package reza.encoder;

public class Utility {
    public void displayHelp(){
        System.out.println();
        System.out.println("Usage: java reza.encoder.FileEncoder [source-file] [encoding-method] [server-address]");
        System.out.println("\tsource-file: path to source file with extension.");
        System.out.println("\tencoding-method: available encoding method - base64 / character");
        System.out.println("\tserver-address: provide server address and port. (optional)");
        System.out.println("\t\tExpected syntax: <address>:<port>");
        System.out.println("\tIf Server address is not provided it connects to the default IP address and PORT.");
        System.out.println("\t\tDefault-server-address: 127.0.0.1:3000");
    }

    public void displayHelp(String message){
        System.out.println(message);
        System.out.println("Usage: java reza.encoder.FileEncoder [source-file] [encoding-method] [server-address]");
        System.out.println("\tsource-file: path to source file with extension.");
        System.out.println("\tencoding-method: available encoding method - base64 / character");
        System.out.println("\tserver-address: provide server address and port. (optional)");
        System.out.println("\t\tExpected syntax: <address>:<port>");
        System.out.println("\tIf Server address is not provided it connects to the default IP address and PORT.");
        System.out.println("\t\tDefault-server-address: 127.0.0.1:3000");
    }
}
