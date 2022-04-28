import java.util.ArrayList;
import java.util.List;

public class Row {
    List<String> myRowContents;

    public Row(String[] args) {
        myRowContents = new ArrayList<String>();

        for (int i = 0; i < args.length; i++) {
            myRowContents.add(args[i]);
        }
    }

    public String getData(int i) {
        return myRowContents.get(i);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("|\t");

        for (int i = 0; i < myRowContents.size(); i++) {
            sb.append(myRowContents.get(i) + "\t");
        }

        sb.append("|");

        return sb.toString();
    }
}
