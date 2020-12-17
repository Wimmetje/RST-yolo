public class Results extends Logger{
    static String SelectedMap;
    static String SelectedOperator;
    static double kd;
    static int kills;
    static int deaths;
    static int assists;
    static String confirm;
    static String savechoice;
    static String PlayedPlaylist;

    public static void MapSetter(String Playlist) throws Exception {
        switch (Playlist) {//Makes that you can choose the right maps
            case "ranked" -> {
                setPlaylist("ranked");
                PlayedPlaylist = Playlist;//necessary for later in the code (line: 57)
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
        SelectedOperator = OperatorFinder();//checks if the operator you played is valid
    }

    public static void Register() throws Exception {
        System.out.println("----------");
        System.out.println("How many kills did you have?");
        kills = II();//lets you input an integer (In MethodeSetter)
        System.out.println("How many deaths did you have?");
        deaths = II();
        System.out.println("How many assists did you have?");
        assists = II();
        if (kills != 0){
            kd = (double)kills/deaths;
        } else {
            kd = 0;
        }
        System.out.printf("Do you confirm these results? %dK, %dD, %dA on %s with %s? [y/n]\n",kills,deaths,assists,Capitalizer(SelectedMap),Capitalizer(SelectedOperator));
        confirm = SI();
        if(confirm.equals("back")||confirm.equals("stop")||confirm.equals("main menu")||confirm.equals("n")){
            throw new BackException("back");//returns you to main menu
        }

        System.out.printf("You played %s on %s with %d kills, %d deaths, %d assists and a KD of %.2f\n",Capitalizer(SelectedOperator),Capitalizer(SelectedMap),kills,deaths,assists,kd);

        System.out.println("----------");
        System.out.println("Would you like to save these results? [y/n]");
        savechoice = SI().toLowerCase();
        switch (savechoice){
            case "y" -> Logger.LoggerWriter(SelectedMap,SelectedOperator,PlayedPlaylist,kills,deaths,assists,kd);//saves your data in a file
            case "n" -> throw new BackException("back");
            default -> System.out.println("Sorry, didn't understand what you meant by that");
        }
    }
}
