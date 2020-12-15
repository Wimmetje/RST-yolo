import java.util.Base64;

public class FileEncryption extends Operator{
    public static String FileEncrypter(String input){
        byte[] encodedString = Base64.getEncoder().encode(input.getBytes());
        String output = new String(encodedString);
        return output;
    }
    public static String FileDecrypter(String input){
        byte[] decodedString = Base64.getDecoder().decode(input.getBytes());
        String output = new String(decodedString);
        return output;
    }
}
