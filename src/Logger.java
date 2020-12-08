import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Logger extends Operator{
    private static int lastkills;
    private static int lastdeaths;
    private static int lastassist;
    private static int ngkills;
    private static int ngdeaths;
    private static int ngassist;

    public static void LoggerReader(String Operator) throws Exception {
        List<String> lines = new ArrayList<>();
        try {
            String LastFile = Files.readAllLines(Paths.get("src\\logging\\completelast.txt")).get(OperatorNumFinder.OpNumFinder(Operator));
            BufferedReader reader = new BufferedReader(new FileReader(LastFile));
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
            lastkills = parseInt(lines.get(1));
            lastdeaths = parseInt(lines.get(2));
            lastassist = parseInt(lines.get(3));

            File f = new File(LastFile);
            if (f.delete()) {
                System.out.println(LastFile + " deleted");
            }
        }catch(IOException e){
            System.out.println("File will be created...");
        }
    }

    public static void LoggerWriter(String Map, String Operator, int newkills, int newdeaths, int newassist, double newUKD) throws Exception{
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd.hhmmss");
        String strDate = dateFormat.format(date);
        System.out.println(strDate);

        LoggerReader(Operator);

        String Filename = "src\\logging\\" + Operator + " " + strDate + ".txt";
        File sfile = new File(String.valueOf(Filename));

        if(sfile.createNewFile()){
            System.out.println("File created: " + sfile.getName());
        } else {
            System.out.println("File already exists");
        }

        DecimalFormat df = new DecimalFormat("#.##");
        double lastUKD = ((double) newkills +lastkills)/((double) newdeaths +lastdeaths);
        String rkd = df.format(lastUKD);

        FileWriter writer = new FileWriter(sfile);
        writer.write(Operator + "\n");
        writer.write(String.valueOf(newkills+lastkills) + "\n");
        writer.write(String.valueOf(newdeaths+lastdeaths) + "\n");
        writer.write(String.valueOf(newassist+lastassist) + "\n");
        writer.write(rkd);

        writer.flush();
        writer.close();

        LoggerUpdater(Map,Operator,newkills,newdeaths,newassist);

        int Operatornum = OperatorNumFinder.OpNumFinder(Operator);

        setVariable(Operatornum,Filename);
    }

    public static void LoggerUpdater(String cacheOperator, String cacheMap, int cacheKills, int cacheDeaths, int cacheAssist) throws IOException {
        int tkills = lastkills+cacheKills;
        int tdeaths = lastdeaths+cacheDeaths;
        int tassist = lastassist+cacheAssist;
        double tkd;
        double cacheKD;
        if(cacheKills==0){
            cacheKD=0;
        }else{
            cacheKD = ((double) cacheKills)/cacheDeaths;
        }
        if(tkills==0){
            tkd=0;
        }else{
            tkd = ((double) tkills)/tdeaths;
        }

        File gfile = new File("src\\logging\\global.txt");
        FileWriter writer = new FileWriter(gfile, false);
        writer.write(String.valueOf(tkills));
        writer.write(String.valueOf(tdeaths));
        writer.write(String.valueOf(tassist));
        writer.write(String.valueOf(tkd));

        File cacheFile = new File("src\\logging\\last.txt");
        writer = new FileWriter(cacheFile, false);
        writer.write(cacheOperator);
        writer.write(cacheMap);
        writer.write(String.valueOf(cacheKills));
        writer.write(String.valueOf(cacheDeaths));
        writer.write(String.valueOf(cacheAssist));
        writer.write(String.valueOf(cacheKD));

    }

    public static void setVariable(int lineNumber, String data) throws IOException {
        Path path = Paths.get("src\\logging\\completelast.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.set(lineNumber, data);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }
}
