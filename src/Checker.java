import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Checker extends Logger{
    public static void Checker() throws Exception {
        List<String> lines = new ArrayList<>();
        List<String> globallines = new ArrayList<>();

        String SelOperator = SI().toLowerCase();
        int OperatorNum = OperatorNumFinder.OpNumFinder(SelOperator);

        String FileCheck = Files.readAllLines(Paths.get("src\\logging\\completelast.txt")).get(OperatorNumFinder.OpNumFinder(SelOperator));
        BufferedReader reader = new BufferedReader(new FileReader(FileCheck));
        String line;

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        String operator = lines.get(0);
        int kills = parseInt(lines.get(1));
        int deaths = parseInt(lines.get(2));
        int assist = parseInt(lines.get(3));
        double kd = parseDouble(lines.get(4));

        System.out.printf("Your stats with %s are: ",Capitalizer(operator));
        System.out.println("Kills: "+kills);
        System.out.println("Deaths: "+deaths);
        System.out.println("Assists: "+assist);
        System.out.println("Your total Kill/Dead ratio is: "+kd);

        reader = new BufferedReader(new FileReader("src\\logging\\global.txt"));

        while ((line = reader.readLine()) != null) {
            globallines.add(line);
        }
        reader.close();

        int gkills = parseInt(lines.get(0));
        int gdeaths = parseInt(lines.get(1));
        int gassist = parseInt(lines.get(1));
        double gkd = parseDouble(lines.get(1));

        System.out.println("Your global stats are: ");
        System.out.println("Kills: "+gkills);
        System.out.println("Deaths: "+gdeaths);
        System.out.println("Assists: "+gassist);
        System.out.println("Your total Kill/Dead ratio is: "+gkd);
    }
}
