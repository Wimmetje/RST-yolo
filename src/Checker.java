import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Checker extends Logger{
    public static void MethodChecker() throws Exception {
        List<String> lines = new ArrayList<>();
        List<String> globallines = new ArrayList<>();
        List<String> lastlines = new ArrayList<>();
        String Playlist;
        String FileCheck = "Null";
        String choice;
        boolean loop = true;

        System.out.println("What Operator do you want to check out?");

        String SelOperator = SI().toLowerCase();
        if(SelOperator.equals("back")||SelOperator.equals("stop")||SelOperator.equals("main menu")){
            throw new BackException("back");
        }
        int OperatorNum = OperatorNumFinder.OpNumFinder(SelOperator);

        System.out.println("What Playlist do you want to check? (Your Global stats will always be displayed");
        Playlist = SI().toLowerCase();

        do {
            switch (Playlist) {
                case "ranked" ->
                        {
                            FileCheck = Files.readAllLines(Paths.get("src\\logging\\ranked.fileindex.txt")).get(OperatorNumFinder.OpNumFinder(SelOperator));
                            loop = false;
                        }
                case "unranked" ->
                        {
                            FileCheck = Files.readAllLines(Paths.get("src\\logging\\unranked.fileindex.txt")).get(OperatorNumFinder.OpNumFinder(SelOperator));
                            loop = false;
                        }
                case "quickmatch" ->
                        {
                            FileCheck = Files.readAllLines(Paths.get("src\\logging\\quickmatch.fileindex.txt")).get(OperatorNumFinder.OpNumFinder(SelOperator));
                            loop = false;
                        }
                case "back", "stop", "main menu" -> throw new BackException("back");
                default -> System.out.println("That is not a valid Playlist");
            }
        }while (loop);
        BufferedReader reader = new BufferedReader(new FileReader(FileCheck));
        String line;

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        String operator = FileDecrypter(lines.get(0));
        int kills = parseInt(FileDecrypter(lines.get(1)));
        int deaths = parseInt(FileDecrypter(lines.get(2)));
        int assist = parseInt(FileDecrypter(lines.get(3)));
        double kd = parseDouble(FileDecrypter(lines.get(4)));

        System.out.printf("Your stats with %s are: ",Capitalizer(operator));
        System.out.println("Kills: "+kills);
        System.out.println("Deaths: "+deaths);
        System.out.println("Assists: "+assist);
        System.out.printf("Your total Kill/Dead ratio is: %.2f",kd);

        reader = new BufferedReader(new FileReader("src\\logging\\global.txt"));

        while ((line = reader.readLine()) != null) {
            globallines.add(line);
        }
        reader.close();

        int gkills = parseInt(FileDecrypter(globallines.get(0)));
        int gdeaths = parseInt(FileDecrypter(globallines.get(1)));
        int gassist = parseInt(FileDecrypter(globallines.get(2)));
        double gkd = parseDouble(FileDecrypter(globallines.get(3)));

        System.out.println("Your global stats are: ");
        System.out.println("Kills: "+gkills);
        System.out.println("Deaths: "+gdeaths);
        System.out.println("Assists: "+gassist);
        System.out.printf("Your total Kill/Dead ratio is: %.2f\n",gkd);

        System.out.println("Do you want to see your last results? [y/n]");
        choice = SI().toLowerCase();
        if(choice.equals("y")){
            reader = new BufferedReader(new FileReader("src\\logging\\last.txt"));

            while ((line = reader.readLine()) != null) {
                lastlines.add(line);
            }
            reader.close();

            String lOperator = FileDecrypter(lastlines.get(0));
            String lMap = FileDecrypter(lastlines.get(1));
            int lkills = parseInt(FileDecrypter(lastlines.get(2)));
            int ldeaths = parseInt(FileDecrypter(lastlines.get(3)));
            int lassist = parseInt(FileDecrypter(lastlines.get(4)));
            double lkd = parseDouble(FileDecrypter(lastlines.get(5)));

            System.out.println("Your last stats were: ");
            System.out.println("Operator: "+lOperator);
            System.out.println("Map: "+lMap);
            System.out.println("Kills: "+lkills);
            System.out.println("Deaths: "+ldeaths);
            System.out.println("Assists: "+lassist);
            System.out.printf("Your total Kill/Dead ratio is: %.2f\n",lkd);
        }
    }
}
