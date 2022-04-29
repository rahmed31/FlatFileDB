import java.util.ArrayList;
import java.util.List;

public class Table {

    public String tableName;
    public List<String> colNames;
    private List<Row> rows;

    public Table(String name, String contents) {
        this.tableName = name;
        this.colNames = new ArrayList<String>(List.of(contents.split(",")));
        rows = new ArrayList<>();
    }

    private class Projection {
        private List<String> colNames;
        private int[] colIndices;
        private List<Row> rows;

        Projection(Table t, String[] args) {
            colNames = new ArrayList<>();
            colIndices = new int[args.length];
            rows = t.getRows();

            List<String> tcolumns = t.getColNames();

            for (int i = 0; i < args.length; i++) {
                colNames.add(args[i]);
                colIndices[i] = tcolumns.indexOf(args[i]);
            }
        }

        @Override
        public String toString() {

            StringBuilder sb = new StringBuilder();
            sb.append("|\t");

            for (int k = 0; k < colNames.size(); k++) {
                sb.append(colNames.get(k) + "\t");
            }

            sb.append("|\n");

            for (int i = 0; i < rows.size(); i++) {
                sb.append("|\t");
                for (int j : colIndices) {
                    sb.append(rows.get(i).getElement(j) + "\t");
                }
                sb.append("|\n");
            }

            return sb.toString();
        }
    }

    public void setTableName(String tableName) {this.tableName = tableName;}

    public String getTableName() {return this.tableName;}

    public void setColNames(ArrayList colNames) {this.colNames = colNames;}

    public List<String> getColNames() {return this.colNames;}

    public int getNumRows() {return rows.size();}

    public String viewData() {return this.toString();}

    public List<Row> getRows() {return this.rows;}

    public String select(String[] args) {return new Projection(this, args).toString();}

    public void insert(String[] args) {rows.add(new Row(args));}

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Table Name: " + this.tableName + "\n");
        sb.append("|\t");

        for (int k = 0; k < colNames.size(); k++) {
            sb.append(colNames.get(k) + "\t");
        }

        sb.append("|\n");

        for (int i = 0; i < rows.size(); i++) {
            sb.append(rows.get(i).toString() + "\n");
        }

        sb.append("\n");

        return sb.toString();

    }

}