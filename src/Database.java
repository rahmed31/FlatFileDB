import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Database {

    List <Table> myDatabase;

    public Database() {
        myDatabase = new ArrayList<Table>();
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

}
