package MNG;

import Tools.MyTools;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Menu extends ArrayList<String> {

    public Menu() {
        super();
    }

    public Menu(String[] items) {
        super();
        for (String item : items) {
            this.add(item);
        }
    }

    public String getChoice(String title) {
        System.out.println(title);
        for (int i = 0; i < this.size(); i++) {
            System.out.println((i + 1) + " - " + this.get(i));
        }
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("Your choice: ");
                return sc.nextLine();
            } catch (Exception ex) {
                System.out.println("Your choice is invalid!");
            }
        } while (true);
    }

    public static void showMenu() {
        String[] options = {"Add CD to the catalog.", "Search CD by CD title.", "Display the catalog.", "Update CD:\n 4.1: Update CD\n 4.2: Delete CD", "Save account to file.", "Print list CDs from the file.", "Others - Quits"};
        Menu mnu = new Menu(options);
        CD_Management pM = new CD_Management();
        String choice;
        do {
            choice = mnu.getChoice("--------------------Managing products--------------------");
            switch (choice) {
                case "1":
                    pM.create();
                    break;
                case "2":
                    pM.search();
                    break;
                case "3":
                    pM.print();
                    break;
                case "4":
                    System.out.println("Please input 4.1 or 4.2! Try again!");
                    System.out.println();
                    break;
                case "4.1":
                    pM.update();
                    break;
                case "4.2":
                    pM.delete();
                    break;
                case "5":
                    pM.save();
                    break;
                case "6":
                    pM.printFromFile();
                    break;
                default:
                    if (pM.isChanged() == true) {
                        System.out.println("Data is changed. Save to file ?");
                        int num = MyTools.getIntFromMinToMax("  1: Yes\n  2: No\n    Your Choice: ", 1, 2);
                        if (num == 1) {
                            pM.save();
                            System.out.println("--------------------Bye--------------------");
                            return;
                        } else {
                            System.out.println("--------------------Bye--------------------");
                            return;
                        }
                    }
            }
        } while (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("4.1")
                || choice.equals("4.2") || choice.equals("5") || choice.equals("6"));
        System.out.println("----------GoodBye----------");

    }
}
