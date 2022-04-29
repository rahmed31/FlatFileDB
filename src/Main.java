import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        Database database = new Database("MyDatabase");

        try (PrintWriter writer = new PrintWriter(database.getName() + ".txt")) {
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.scheduleAtFixedRate(() -> {
                if (!database.checkSave()) {
                    database.updateTmp();
                    writer.flush();
                    writer.println(database);
                }
            },10L,10L, TimeUnit.SECONDS);

            Scanner input = new Scanner(System.in);
            String inputData;

            while(!(inputData = input.nextLine()).equals("exit")) {
                String contents = inputData.toLowerCase();

                if (contents.contains("create")) {
                    String tname = inputData.split(" ")[2];
                    String columns = inputData.substring(inputData.indexOf("(")+1, inputData.indexOf(")"));

                    database.add(new Table(tname, columns));
                }
                else if (contents.contains("insert")) {
                    String tname = inputData.trim().split(" ")[2];
                    String[] inputs = inputData.substring(inputData.indexOf("(")+1, inputData.indexOf(")")).split(",");

                    database.get(tname).insert(inputs);
                }
                else if (contents.contains("select")) {
                    String tname = inputData.split(" ")[3];
                    String[] vars = inputData.split(" ")[1].split(",");

                    System.out.println(database.get(tname).select(vars));

                } else {System.out.println("Command not supported.");}

            }

            service.shutdownNow();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }
}
