
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Create tavuni.
 *
 * @author Mithat Konar
 * @author Suhaib Abugdera
 */
public class TavunuApp {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws IOException {

        ArrayList<Tavunu> list = new ArrayList<Tavunu>();
        Scanner scan = new Scanner(System.in);
        while (true) {
            // Print menu
            System.out.println("[a] Add a member\r\n" + "[r] Remove a member\r\n" + "[d] Display the tribe\r\n"
                    + "[s] Save the tribe\r\n" + "[o] Open the tribe\r\n" + "[q] Quit");
            System.out.print("Please select an option: ");
            String input = scan.next();

            if (input.equalsIgnoreCase("a")) {
                Tavunu obj;
                System.out.print("Please enter name of tavuna: ");
                String name = scan.next();
                System.out.print("Please enter year of birth: ");
                int birthYear = scan.nextInt();
                System.out.print("Please enter pava: ");
                int pava = scan.nextInt();
                obj = CreateTavunuObject(name, birthYear, pava);
                list.add(obj);
                System.out.println("Added member successfully\n");

            } else if (input.equalsIgnoreCase("r")) {
                System.out.print("Please enter name of tavuna: ");
                String name = scan.next();
                System.out.print("Please enter year of birth: ");
                int birthYear = scan.nextInt();
                for (int i = 0; i < list.size(); i++) {
                    Tavunu obj = list.get(i);
                    if (obj.getName().equalsIgnoreCase(name) && obj.getBirthYear() == birthYear) {
                        list.remove(i);
                        System.out.println("Removed " + name);
                        continue;
                    }
                }

            } else if (input.equalsIgnoreCase("d")) {
                int totalPava = 0;
                for (int i = 0; i < list.size(); i++) {
                    Tavunu obj = list.get(i);
                    System.out.println(obj);
                    totalPava += obj.getPava();
                }
                System.out.println("Total Pava = " + totalPava);
                System.out.println("Members = " + list.size());
            } else if (input.equalsIgnoreCase("s")) {
                FileWriter myWriter = new FileWriter("tribe.txt");
                for (int i = 0; i < list.size(); i++) {
                    Tavunu obj = list.get(i);
                    myWriter.write(obj.getName() + " " + obj.getBirthYear() + " " + obj.getPava() + "\n");
                }
                myWriter.close();
                System.out.println("Saved to file....");

            } else if (input.equalsIgnoreCase("o")) {

                if (list.size() != 0) {
                    System.out.println("There are some tribe members in memory, do you want to continue and lose the data? press y to continue");
                    String inputFromUser = scan.next();
                    if (!inputFromUser.equalsIgnoreCase("y")) {
                        System.out.println("Aborting...");
                    }
                }

                list = new ArrayList<Tavunu>();
                Scanner fileScan = new Scanner(new File("tribe.txt"));
                while (fileScan.hasNextLine()) {
                    String line = fileScan.nextLine();
                    String tokens[] = line.split(" ");
                    Tavunu obj = CreateTavunuObject(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                    list.add(obj);

                }
                System.out.println("File Read");
            } else if (input.equalsIgnoreCase("q")) {
                System.out.println("Quitting");
                return;
            }
        }
    }

    private static Tavunu CreateTavunuObject(String name, int birthYear, int pava) {
        Tavunu obj;
        if (pava > 80) {
            if (birthYear % 2 == 0) {
                obj = new CrodeExalted(name, birthYear, pava);
            } else {
                obj = new Crode(name, birthYear, pava);
            }

        } else if (pava > 20) {
            obj = new Beele(name, birthYear, pava);
        } else {
            obj = new Avo(name, birthYear, pava);
        }
        return obj;
    }

}
