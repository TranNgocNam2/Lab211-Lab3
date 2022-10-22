package MNG;

import Data.CD;
import Data.CD_Catalog;
import Tools.MyTools;
import java.util.Collections;
import Interfaces.I_CD;

/**
 *
 * @author ADMIN
 */
public class CD_Management implements I_CD {

    CD_Catalog cataLog = new CD_Catalog();
    boolean changed = false;

    public CD_Management() {
        cataLog.loadCDFromFile();
    }

    @Override
    public void print() {
        if (cataLog.getcdList().isEmpty()) {
            System.out.println("   Empty Catalog!");
        } else {
            for (int i = 0; i < cataLog.getcdList().size(); i++) {
                System.out.println("  " + (i + 1) + ": " + cataLog.getCD(i).toString());
            }
        }
        System.out.println("There are " + cataLog.getTotal() + " CDs in the catalog");
        if (cataLog.getcdList().size() >= 1000) {
            System.out.println("   Full details of those CDs");
        }
    }

    @Override
    public void create() {
        if (cataLog.getcdList().size() >= 1000) {
            System.out.println("This catalog has been already full! Unable to add CD");
        } else {
            String name, type, title, ID;
            int price, year, pos;
            do {
                ID = MyTools.readPattern("Enter CD ID(CDxxx): ", "CD\\d{3}");
                pos = cataLog.checkCD_ID(ID);
                if (pos >= 0) {
                    System.out.println("CDs ID is duplicated!");
                }
            } while (pos >= 0);
            name = MyTools.readPattern("Enter CD collection name (game/movie/music): ", "(?i)(game)|(movie)|(music)");
            type = MyTools.readPattern("Enter CD type (audio/video): ", "(?i)(audio)|(video)");
            title = MyTools.readNonBlank("Enter CD title: ");
            price = MyTools.getIntFromMinToMax("Enter CD price: ", 1, 100000);
            year = MyTools.getIntFromMinToMax("Enter CD publishing year: ", 1950, 2022);
            cataLog.addCD(new CD(ID, name, type, title, price, year));
            System.out.println("Added new CD successfully!");
            changed = true;
            int num = MyTools.getIntFromMinToMax("Do you want to add more CD?\n  1: Yes\n  2: No\n    Your Choice: ", 1, 2);
            if (num == 1) {
                create();
            } else {
                System.out.println("Return to main menu!");
            }
        }
    }

    @Override
    public void search() {
        System.out.println("There are " + cataLog.getTotal() + " CDs in the catalog");
        String title = MyTools.readNonBlank("Enter CD title you want to search: ").toUpperCase();
        int pos = cataLog.containTitle(title);
        if (pos >= 0) {
            cataLog.printContainTitle(title);
        } else {
            System.out.println("This CD does not exist!");
        }
        int num = MyTools.getIntFromMinToMax("Do you want to search more CD?\n  1: Yes\n  2: No\n    Your Choice: ", 1, 2);
        if (num == 1) {
            search();
        } else {
            System.out.println("Return to main menu!");
        }
    }

    @Override
    public void update() {
        String ID = MyTools.readPattern("Enter CD ID (CDxxx) you want to update: ", "CD\\d{3}");
        int pos = cataLog.checkCD_ID(ID);
        if (pos >= 0) {
            String newName = MyTools.readPattern("Enter new CD collection name (game/movie/music): ", "(?i)(game)|(movie)|(music)");
            String newType = MyTools.readPattern("Enter new CD type (audio/video): ", "(?i)(audio)|(video)");
            String newTitle = MyTools.readNonBlank("Enter new CD title: ");
            int newPrice = MyTools.getIntFromMinToMax("Enter CD price: ", 1, 10000);
            int newYear = MyTools.getIntFromMinToMax("Enter CD publishing year: ", 1950, 2022);
            cataLog.updateCD(newName, newType, newTitle, newPrice, newYear);
            System.out.println(cataLog.getCD(pos));
            System.out.println("Updated CD successfully!");
            changed = true;
        } else {
            System.out.println("CD ID is not exist!");
        }
        int num = MyTools.getIntFromMinToMax("Do you want to update more CD ?\n  1: Yes\n  2: No\n    Your Choice: ", 1, 2);
        if (num == 1) {
            update();
        } else {
            System.out.println("Return to main menu!");
        }
    }

    @Override
    public void delete() {
        String ID = MyTools.readPattern("Enter CD ID (CDxxx) you want to delete: ", "CD\\d{3}");
        int pos = cataLog.checkCD_ID(ID);
        if (pos >= 0) {
            cataLog.deleteCD(ID);
            System.out.println("Delete CD successfully!");
            changed = true;
            int num = MyTools.getIntFromMinToMax("Do you want to delete more CD ?\n  1: Yes\n  2: No\n    Your Choice: ", 1, 2);
            if (num == 1) {
                delete();
            } else {
                System.out.println("Return to main menu!");
            }
        } else {
            System.out.println("CD ID does not exist!");
        }
    }

    @Override
    public void save() {
        cataLog.saveCD();
        System.out.println("Save product succesfully!");
        changed = true;
    }

    @Override
    public void printFromFile() {
        CD_Catalog cataLog2 = new CD_Catalog();
        cataLog2.loadCDFromFile();
        Collections.sort(cataLog2.getcdList());
        if (cataLog2.getcdList().isEmpty()) {
            System.out.println("Empty List!");
        } else {
            for (int i = 0; i < cataLog2.getcdList().size(); i++) {
                System.out.println("   " + (i + 1) + ": " + cataLog2.getCD(i).toString());
            }
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
