import java.util.ArrayList;
import java.util.List;

public class Table {

    private String tableName;
    private List<String> colNames;
    private List<Row> rows;

    public Table(String name, String contents) {
        this.tableName = name;
        this.colNames = new ArrayList<>(List.of(contents.split(",")));
        rows = new ArrayList<>();
    }

    private static class Projection {
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

            for (String colName : colNames) {
                String e = String.format("%-5s", colName);
                sb.append(e).append("\t");
            }

            sb.append("|\n");

            for (Row row : rows) {
                sb.append("|\t");

                for (int j : colIndices) {
                    String e = String.format("%-5s", row.getElement(j));
                    sb.append(e).append("\t");
                }

                sb.append("|\n");
            }

            return sb.toString();
        }
    }

    public void setTableName(String tableName) {this.tableName = tableName;}

    public void rename(String name) {this.tableName = name;}

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

        sb.append("Table Name: ").append(this.tableName).append("\n");

        sb.append("|\t");

        for (String colName : colNames) {
            String e = String.format("%-5s", colName);
            sb.append(e).append("\t");
        }

        sb.append("|\n");

        for (Row row : rows)
            sb.append(row.toString()).append("\n");

        sb.append("\n");

        return sb.toString();

    }

}