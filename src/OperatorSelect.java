import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class OperatorSelect extends MapSelect{
    static String ConfirmedOperator;
    static String PlayedOperator;
    static String PlayedSide;
    static int j;
    static List<String> operators;
    static List<String> lines = new ArrayList<>();
    static boolean SelOperatorLoop = true;

    public static String OperatorFinder() throws Exception {
        System.out.println("----------");
        System.out.println("Did you play as offense or as defense?");
        System.out.println("[Offense]/[Defense]");
        PlayedSide = SI().toLowerCase();

        if(PlayedSide.equals("back")||PlayedSide.equals("stop")||PlayedSide.equals("main menu")){
            throw new BackException("back");
        }

        switch (PlayedSide){
            case "offense" -> {
                FileToArray("src\\Misc\\Ooperators.txt");
                operators = lines;
            }
            case "defense" -> {
                FileToArray("src\\Misc\\Doperators.txt");
                operators = lines;
            }
            default -> {
                Errorcode = "005";
                Error = true;
            }
        }

        ErrorMessage();

        System.out.println("----------");
        System.out.println("Which operator did you play?");
        System.out.println("See documentation for options.");

        j=operators.size();

        do{
            PlayedOperator = SI().toLowerCase();

            if(PlayedOperator.equals("back")||PlayedOperator.equals("stop")||PlayedOperator.equals("main menu")){
                throw new BackException("back");
            }


            for (int i = 0; i < operators.size(); i++){
                if (PlayedOperator.equals(operators.get(i))){
                    ConfirmedOperator = PlayedOperator;
                    SelOperatorLoop = false;
                } else if (!PlayedOperator.equals(operators.get(i)) && i == j){
                    System.out.println("Operator not found.");
                }
            }
        }while(SelOperatorLoop);

        SelOperatorLoop = true;

        System.out.println(ConfirmedOperator);
        return ConfirmedOperator;
    }

    public static void FileToArray(String PlayedSideFile) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(PlayedSideFile));
        String line;

        while((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
    }
}
