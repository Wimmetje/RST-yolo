public class Results extends Logger{
    static String SelectedMap;
    static String SelectedOperator;
    static double kd;
    static double dkills;
    static double ddeaths;
    static int kills;
    static int deaths;
    static int assists;
    static String confirm;
    static String savechoice;
    static String PlayedPlaylist;

    public static void MapSetter(String Playlist) throws Exception {
        switch (Playlist) {
            case "ranked" -> {
                setPlaylist("ranked");
                PlayedPlaylist = Playlist;
            }
            case "unranked" -> {
                setPlaylist("unranked");
                PlayedPlaylist = Playlist;
            }
            case "quickmatch" -> {
                setPlaylist("quickmatch");
                PlayedPlaylist = Playlist;
            }
        }
        SelectedMap = MapFinder();
    }

    public static void OperatorSetter() throws Exception {
        SelectedOperator = OperatorFinder();
    }

    public static void Register() throws Exception {
        System.out.println("How many kills did you have?");
        kills = II();
        dkills = kills;
        System.out.println("How many deaths did you have?");
        deaths = II();
        ddeaths = deaths;
        System.out.println("How many assists did you have?");
        assists = II();
        if (kills != 0){
            kd = dkills/ddeaths;
        } else {
            kd = 0;
        }
        System.out.printf("Do you confirm these results? %d K, %d D, %d A\n", kills,deaths,assists);
        confirm = SI();
        if(confirm.equals("back")||confirm.equals("stop")||confirm.equals("main menu")||confirm.equals("no")){
            throw new BackException("back");
        }

        System.out.printf("You played %s on %s with %d kills, %d deaths, %d assists and a KD of %.2f\n",Capitalizer(SelectedOperator),Capitalizer(SelectedMap),kills,deaths,assists,kd);

        System.out.println("Would you like to save these results? [y/n]");
        savechoice = SI().toLowerCase();
        switch (savechoice){
            case "y" -> Logger.LoggerWriter(SelectedMap,SelectedOperator,PlayedPlaylist,kills,deaths,assists,kd);
            case "n" -> throw new BackException("back");
            default -> System.out.println("Sorry, didn't understand what you meant by that");
        }
    }
}
