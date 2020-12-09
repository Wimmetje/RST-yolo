/**
 * (c)Wim Bron, 2020
 * This application is for free use, and falls under the GNU General Public License
*/
public class Main extends MethodeSetter {
    public static void main(String[] args) throws Exception {
        String playlist;
        String choice;
        boolean loop = true;

        do {
            //start try-catch method that can bring you back to main menu
            try {
                System.out.println("What gamemode did you play?");
                choice = SI().toLowerCase(); //get String input (found in MethodeSetter)
                switch (choice) {
                    case "fill in" -> {
                        playlist = SI().toLowerCase();
                        switch (playlist){
                            case "casual", "quickmatch" -> {
                                Results.MapSetter("quickmatch");
                                Results.OperatorSetter();
                                Results.Register();
                            }
                            case "ranked" -> {
                                Results.MapSetter("ranked");
                                Results.OperatorSetter();
                                Results.Register();
                            }
                            case "unranked" -> {
                                Results.MapSetter("unranked");
                                Results.OperatorSetter();
                                Results.Register();
                            }
                        }
                    }
                    case "check" -> Checker.MethodChecker();
                    case "exit" -> loop = false; //stops the programming from running
                    default -> {
                        //if in a rare scenario you get here, you'll get an error message telling you whats wrong
                        loop = false;
                        System.out.println("Error: 002");
                    }

                }
            } catch (BackException e) {
                //catches the "errors" that'll bring you back to the menu
            }
        }while (loop);
    }
}
