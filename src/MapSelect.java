import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MapSelect extends Match{
    static String map;
    static String ConfirmedMap;
    static int k;
    static int j;
    private static String playlist;
    static List<String> maps;
    static List<String> lines = new ArrayList<>();

    public static void setPlaylist(String playlist) {
        MapSelect.playlist = playlist;
    }

    public static void RankedMapArraySetter() throws Exception {
        FileToArray("src\\Misc\\rmaps.txt");
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
        k = 0;

        switch (playlist) {
            case "ranked" -> RankedMapArraySetter();
            case "unranked" -> UnrankedMapArraySetter();
            case "quickmatch" -> QuickmatchMapArraySetter();
            default -> {
                Errorcode = "003";
                Error = true;
            }
        }
        j = maps.size();
        ErrorMessage();

        System.out.println("Which map did you play?");

        map = SI().toLowerCase();

        if(map.equals("back")){
            throw new BackException("back");
        }

        for (int i = 0; i < maps.size(); i++){
            if(map.equals(maps.get(i))){
                System.out.println("You've played " + map);
                ConfirmedMap = map;
            }else if(!map.equals(maps.get(i)) && i == j){
                ConfirmedMap = "ERROR - SEE ERRORCODE";
                Errorcode = "Error: 001";
                Error = true;
            }
        }
        ErrorMessage();
        return ConfirmedMap;
    }

    public static void FileToArray(String PlaylistFile) throws Exception{
        BufferedReader abc = new BufferedReader(new FileReader(PlaylistFile));
        String line;

        while((line = abc.readLine()) != null) {
            lines.add(line);
        }
        abc.close();
    }
}
