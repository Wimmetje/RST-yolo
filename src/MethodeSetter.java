import java.util.*;

public class MethodeSetter {
    static String Errorcode = null;
    static boolean Error = false;

    public static String SI(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int II(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void ErrorMessage(){
        if (Error){
            System.out.println(Errorcode);
            System.exit(0);
        }
    }

    public static String Capitalizer(String input){
        char[] cp = input.toCharArray();

        char ccap = cp[0];
        String Scap = String.valueOf(ccap);
        Scap = Scap.toUpperCase();

        char[] capitalized = Scap.toCharArray();
        cp[0] = capitalized[0];

        String output = String.valueOf(cp);

        return output;
    }
}
