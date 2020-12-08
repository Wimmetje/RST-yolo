/**
 * (c)Wim Bron, 2020
 * This application is for free use, and falls under the GNU General Public License
*/
public class Match extends MethodeSetter {
    public static void main(String[] args) throws Exception {
        String playlist; //variable where type of playlist is saved
        boolean loop = true; // initialize for loop

        do {
            //start try-catch method that can bring you back to main menu
            try {
                System.out.println("What gamemode did you play?");
                playlist = SI().toLowerCase(); //get String input (found in MethodeSetter)
                switch (playlist) {
                    case "casual", "quickmatch" -> {
                        Quickmatch.MapSetter();
                        Quickmatch.OperatorSetter();
                        Quickmatch.Register();
                    }
                    case "ranked" -> {
                        Ranked.MapSetter();
                        Ranked.OperatorSetter();
                        Ranked.Register();
                    }
                    case "unranked" -> {
                        Unranked.MapSetter();
                        Unranked.OperatorSetter();
                        Unranked.Register();
                    }
                    case "check" -> {
                        Checker.Checker();
                    }
                    case "stop" -> loop = false; //stops the programming from running
                    default -> {
                        //if in a rare scenario you get here, you'll get an error message telling you whats wrong
                        loop = false;
                        System.out.println("Error: 002");
                    }

                }
            } catch (BackException e) {
                //catches the "errors" that'll bring you back to the menu
            }
        }while (loop);//runs as long loop equals true
    }
}
