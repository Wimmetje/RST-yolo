public class Unranked extends Logger{
    static String SelectedMap;
    static String SelectedOperator;
    static int Totalkills;
    static int Totaldeaths;
    static int Totalassists;
    static double kd;
    static double dkills;
    static double ddeaths;
    static int TempTotalkills;
    static int TempTotaldeaths;
    static int TempTotalassists;
    static int Tempkd;
    static int kills;
    static int deaths;
    static int assists;
    static String confirm;
    static String savechoice;

    public static void MapSetter() throws Exception {
        setPlaylist("unranked");
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
        kd = dkills/ddeaths;
        System.out.printf("Do you confirm these results? %d K, %d D, %d A [y/n]", kills,deaths,assists);
        confirm = SI();
        if(confirm.equals("back")){
            throw new BackException("back");
        }

        System.out.printf("You played %s on %s with %d kills, %d deaths, %d assists and a KD of %.2f\n",Capitalizer(SelectedOperator),Capitalizer(SelectedMap),kills,deaths,assists,kd);

        System.out.println("Would you like to save these results? [y/n]");
        savechoice = SI().toLowerCase();
        switch (savechoice){
            case "y" -> Logger.LoggerWriter(SelectedMap,SelectedOperator,kills,deaths,assists,kd);
            case "n" -> throw new BackException("back");
            default -> System.out.println("Sorry, didn't understand what you meant by that");
        }
    }
}
