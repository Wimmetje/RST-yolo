import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Operator extends MapSelect{
    static String ConfirmedOperator;
    static String PlayedOperator;
    static String PlayedSide;
    static int j;
    static List<String> operators;
    static List<String> lines = new ArrayList<>();
    static boolean SelOperatorLoop = true;

    public static String OperatorFinder() throws Exception {
        System.out.println("Did you play as offense or as defense?");
        PlayedSide = SI().toLowerCase();

        if(PlayedSide.equals("back")){
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

        System.out.println("Which operator did you play?");

        j=operators.size();

        do{
            PlayedOperator = SI().toLowerCase();

            if(PlayedOperator.equals("back")){
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
        BufferedReader abc = new BufferedReader(new FileReader(PlayedSideFile));
        String line;

        while((line = abc.readLine()) != null) {
            lines.add(line);
        }
        abc.close();
    }
}
