import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class OperatorNumFinder extends Logger {
    public static int OpNumFinder(String SelectedOperator) throws Exception{

        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src\\Misc\\TotalOperators.txt"));
        String line;

        while((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        int j = lines.size();

        for(int i = 0; i < lines.size(); i++){
            if(SelectedOperator.equals(lines.get(i))){
                return i;
            }
        }

        throw new BackException("back");
    }
}
