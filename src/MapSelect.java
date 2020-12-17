import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MapSelect extends Main {
    static String map;
    static String ConfirmedMap;
    static int ArraySize;
    private static String playlist;
    static List<String> maps;
    static List<String> lines = new ArrayList<>();

    public static void setPlaylist(String playlist) {
        MapSelect.playlist = playlist;
    }

    public static void RankedMapArraySetter() throws Exception {
        FileToArray("src\\Misc\\rmaps.txt");//converts text file into arraylist
        maps = lines;
    }

    public static void UnrankedMapArraySetter() throws Exception {
        FileToArray("src\\Misc\\umaps.txt");
        maps = lines;
    }

    public static void QuickmatchMapArraySetter() throws Exception {
        FileToArray("src\\Misc\\qmaps.txt");
        maps = lines;
    }

    public static String MapFinder() throws Exception{
        ConfirmedMap = null;

        switch (playlist) {
            case "ranked" -> RankedMapArraySetter();
            case "unranked" -> UnrankedMapArraySetter();
            case "quickmatch" -> QuickmatchMapArraySetter();
            default -> {
                System.out.println("Something went wrong.");
                throw new BackException("back");//sends you back to Main loop
            }
        }
        ArraySize = maps.size();

        System.out.println("----------");
        System.out.println("Which map did you play?");
        System.out.println("See documentation for options.");

        map = SI().toLowerCase();

        if(map.equals("back")||map.equals("stop")||map.equals("main menu")){
            throw new BackException("back");
        }

        for (int i = 0; i < maps.size(); i++){//for method that verifies the Map you played.
            if(map.equals(maps.get(i))){
                ConfirmedMap = map;
            }else if(!map.equals(maps.get(i)) && i == ArraySize){//if the input isn't in the array, and your on the last item return false
                throw new BackException("back");
            }
        }
        ErrorMessage();
        return ConfirmedMap;
    }

    public static void FileToArray(String PlaylistFile) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(PlaylistFile));
        String line;

        while((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
    }
}
