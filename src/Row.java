import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Row {
    List<String> myRowContents = new ArrayList<>();

    public Row(String[] args) {Collections.addAll(myRowContents, args);}

    public String getElement(int i) {return myRowContents.get(i);}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("|\t");

        for (String myRowContent : myRowContents) {
            String e = String.format("%-5s", myRowContent);
            sb.append(e).append("\t");
        }

        sb.append("|");

        return sb.toString();
    }
}
