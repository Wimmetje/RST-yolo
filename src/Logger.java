import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
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
import static java.lang.Integer.parseInt;

public class Logger extends FileEncryption{
    private static int lastkills;
    private static int lastdeaths;
    private static int lastassist;
    private static int gkills;
    private static int gdeaths;
    private static int gassists;

    public static void LoggerReader(String Operator, String Playlist) throws Exception {
        List<String> lines = new ArrayList<>();
        try {
            String Filename = "src\\logging\\" + Playlist + ".fileindex.txt";
            String LastFile = Files.readAllLines(Paths.get(Filename)).get(OperatorNumFinder.OpNumFinder(Operator));
            BufferedReader reader = new BufferedReader(new FileReader(LastFile));
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
            lastkills = parseInt(FileDecrypter((lines.get(1))));
            lastdeaths = parseInt(FileDecrypter(lines.get(2)));
            lastassist = parseInt(FileDecrypter(lines.get(3)));

            File file = new File(LastFile);

            if (file.delete()){
                System.out.println("Step 1 complete.");
            }else{
                System.out.println("Step 1 failed! See documentation!");
            }
        }catch(IOException e){
            System.out.println("First time file setup!");
        }
    }

    public static void LoggerWriter(String Map, String Operator,String Playlist, int newkills, int newdeaths, int newassist, double newUKD) throws Exception{
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd.hhmmss");
        String strDate = dateFormat.format(date);

        LoggerReader(Operator,Playlist);

        String Filename = "src\\logging\\" + Playlist + "." + Operator + "." + strDate + ".txt";
        File sfile = new File(String.valueOf(Filename));

        if(sfile.createNewFile()){
            System.out.println("Step 2 complete.\n");
        } else {
            System.out.println("Step 2 failed! See documentation!\n");
        }

        DecimalFormat df = new DecimalFormat("#.##");
        double lastUKD = ((double) newkills +lastkills)/((double) newdeaths +lastdeaths);
        String rkd = df.format(lastUKD);

        FileWriter writer = new FileWriter(sfile);
        writer.write(FileEncrypter(Operator) + "\n");
        writer.write(FileEncrypter(String.valueOf(newkills+lastkills)) + "\n");
        writer.write(FileEncrypter(String.valueOf(newdeaths+lastdeaths)) + "\n");
        writer.write(FileEncrypter(String.valueOf(newassist+lastassist)) + "\n");
        writer.write(FileEncrypter(rkd));

        writer.flush();
        writer.close();

        GlobalReader();

        LoggerUpdater(Map,Operator,newkills,newdeaths,newassist);

        int Operatornum = OperatorNumFinder.OpNumFinder(Operator);

        setVariable(Operatornum,Filename,Playlist);
    }

    public static void LoggerUpdater(String cacheOperator, String cacheMap, int cacheKills, int cacheDeaths, int cacheAssist) throws IOException {
        int tkills = gkills+cacheKills;
        int tdeaths = gdeaths+cacheDeaths;
        int tassist = gassists+cacheAssist;
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

        DecimalFormat df = new DecimalFormat("#.##");
        String rkd = df.format(tkd);

        File gfile = new File("src\\logging\\global.txt");
        FileWriter writer = new FileWriter(gfile, false);
        writer.write(FileEncrypter(String.valueOf(tkills))+"\n");
        writer.write(FileEncrypter(String.valueOf(tdeaths))+"\n");
        writer.write(FileEncrypter(String.valueOf(tassist))+"\n");
        writer.write(FileEncrypter(rkd));

        writer.close();

        File cacheFile = new File("src\\logging\\last.txt");
        writer = new FileWriter(cacheFile, false);
        writer.write(FileEncrypter(cacheOperator)+"\n");
        writer.write(FileEncrypter(cacheMap)+"\n");
        writer.write(FileEncrypter(String.valueOf(cacheKills))+"\n");
        writer.write(FileEncrypter(String.valueOf(cacheDeaths))+"\n");
        writer.write(FileEncrypter(String.valueOf(cacheAssist))+"\n");
        writer.write(FileEncrypter(String.valueOf(cacheKD)));

        writer.close();

    }

    public static void setVariable(int lineNumber, String OperatorPath, String Playlist) throws IOException {
        Path path = switch (Playlist) {
            case "ranked" -> Paths.get("src\\logging\\ranked.fileindex.txt");
            case "unranked" -> Paths.get("src\\logging\\unranked.fileindex.txt");
            case "quickmatch" -> Paths.get("src\\logging\\quickmatch.fileindex.txt");
            default -> throw new BackException("back");
        };
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.set(lineNumber, OperatorPath);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    public static void GlobalReader() throws Exception {
        List<String> globallines = new ArrayList<>();
        String FileCheck = "Null";
        String line;

        BufferedReader reader = new BufferedReader(new FileReader("src\\logging\\global.txt"));

        while ((line = reader.readLine()) != null) {
            globallines.add(line);
        }
        reader.close();

        gkills = parseInt(FileDecrypter(globallines.get(0)));
        gdeaths = parseInt(FileDecrypter(globallines.get(1)));
        gassists = parseInt(FileDecrypter(globallines.get(2)));
    }
}
