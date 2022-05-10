import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        Database database = new Database("MyDatabase");

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(() -> {
            if (!database.checkSave()) {
                database.writeToDatabase();
            }
        },10L,2L, TimeUnit.SECONDS);

        Scanner input = new Scanner(System.in);
        String inputData;

        while(!(inputData = input.nextLine()).equals("exit")) {
            String contents = inputData.toLowerCase();

            if (contents.contains("create")) {
                String t_name = inputData.split(" ")[2];
                String columns = parse(inputData);

                if (database.get(t_name) != null)
                    System.out.println("Sorry, this table name already exists");
                else database.add(new Table(t_name, columns));
            }
            else if (contents.contains("insert")) {
                String t_name = inputData.trim().split(" ")[2];
                String[] inputs = parse(inputData).split(",");

                database.get(t_name).insert(inputs);
            }
            else if (contents.contains("select")) {
                String t_name = inputData.split(" ")[3];
                String[] vars = inputData.split(" ")[1].split(",");

                System.out.println(database.get(t_name).select(vars));
            }
            else if (contents.contains("drop table")) {
                String t_name = inputData.split(" ")[2];
                database.drop(database.get(t_name));
            } else {System.out.println("Command not supported.");}

        }

        service.shutdownNow();

        System.out.println("Done");
    }

    public static String parse(String inputData) {
        return inputData.substring(inputData.indexOf("(")+1, inputData.indexOf(")"));
    }
}
