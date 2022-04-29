import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Database {

    List <Table> myDatabase;
    public String name;
    private String tmp = "";

    public Database(String name) {
        this.name = name;
        myDatabase = new ArrayList<Table>();
    }

    public String getName() {
        return this.name;
    }

    public void add(Table table) {
        myDatabase.add(table);
    }

    public void delete(Table table) {
        myDatabase.remove(table);
    }

    public Table get(String table) {

        Iterator it = myDatabase.iterator();

        while(it.hasNext()) {
            Table t = (Table)it.next();

            if (t.getTableName().equals(table)) {
                return t;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        myDatabase.forEach((t) -> sb.append(t.toString()));

        return sb.toString();
    }

    public boolean checkSave() {

        if (myDatabase.toString().equals(this.tmp))
            return true;

        return false;
    }

    public void updateTmp() {
        this.tmp = this.toString();
    }

}
