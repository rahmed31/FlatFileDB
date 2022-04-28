import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Database database = new Database();

        String inputData;

        while(!(inputData = input.nextLine()).equals("exit")) {
            String contents = inputData.toLowerCase();

            if (contents.contains("create")) {
                String tname = inputData.split(" ")[2];
                String columns = inputData.substring(inputData.indexOf("(")+1, inputData.indexOf(")"));

                database.add(new Table(tname, columns));

                System.out.println("Creating database");
            }
            else if (contents.contains("insert")) {
                String tname = inputData.trim().split(" ")[2];
                String[] inputs = inputData.substring(inputData.indexOf("(")+1, inputData.indexOf(")")).split(",");

                database.get(tname).insert(inputs);

                System.out.println("Inserting into database");
            }
            else if (contents.contains("select")) {
                String tname = inputData.split(" ")[3];
                String[] vars = inputData.split(" ")[1].split(",");

                System.out.println(database.get(tname).select(vars));

                System.out.println("Selecting from database");
            } else {System.out.println("Command not supported.");}

        }

        System.out.println("Done");
    }
}
