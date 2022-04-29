import java.util.ArrayList;
import java.util.List;

public class Database {

    List <Table> myDatabase;
    public String name;
    private String tmp = "";

    public Database(String name) {
        this.name = name;
        myDatabase = new ArrayList<>();
    }

    public String getName() {return this.name;}

    public void add(Table table) {myDatabase.add(table);}

    public void drop(Table table) {myDatabase.remove(table);}

    public Table get(String table) {

        for (Table t : myDatabase) {
            if (t.getTableName().equals(table))
                return t;
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        myDatabase.forEach((t) -> sb.append(t.toString()));

        return sb.toString();
    }

    public boolean checkSave() {return myDatabase.toString().equals(this.tmp);}

    public void updateTmp() {this.tmp = this.toString();}

}
