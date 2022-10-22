/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Tools.MyTools;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CD_Catalog extends CD {

    public ArrayList<CD> cdList = new ArrayList<>(1000);
    private final String dataFile = "CD.txt";

    public CD getCD(int i) {
        return cdList.get(i);
    }

    public ArrayList getcdList() {
        return cdList;
    }

    public void setcdList(ArrayList cdList) {
        this.cdList = cdList;
    }

    public void loadCDFromFile() {
        List<String> lines = MyTools.readLinesFromFile(dataFile);
        for (String x : lines) {
            if (!x.isEmpty()) {
                String[] a = x.split(", ");
                CD cd = new CD(a[0].trim(), a[1].trim(), a[2].trim(), a[3].trim(), Integer.parseInt(a[4].trim()), Integer.parseInt(a[5].trim()));
                cdList.add(cd);
            }
        }
    }

    public void addCD(CD cd) {
        cdList.add(cd);
    }

    public int checkTitle(String checkTitle) {
        for (int i = 0; i < cdList.size(); i++) {
            if (cdList.get(i).getTitle().equals(checkTitle)) {
                return i;
            }
        }
        return -1;
    }

    public int checkCD_ID(String cd_ID) {
        for (int i = 0; i < cdList.size(); i++) {
            if (cdList.get(i).getId().equals(cd_ID)) {

                return i;
            }
        }
        return -1;
    }

    public int containTitle(String containTitle) {
        for (int i = 0; i < cdList.size(); i++) {
            if (cdList.get(i).getTitle().toUpperCase().contains(containTitle)) {
                return i;
            }
        }
        return -1;
    }

    public void printContainTitle(String containTitle) {
        for (int i = 0; i < cdList.size(); i++) {
            if (cdList.get(i).getTitle().contains(containTitle)) {
                System.out.println(" - " + cdList.get(i).getTitle());
            }
        }
    }

    public int getTotal() {
        int count = 0;
        for (int i = 0; i < cdList.size(); i++) {
            count++;
        }
        return count;
    }

    public void updateCD(String newName, String newType, String newTitle, int newPrice, int newYear) {
        String checkID = "";
        int pos = checkCD_ID(checkID);
        if (pos > 0) {
            cdList.get(pos).setName(newName);
            cdList.get(pos).setType(newType);
            cdList.get(pos).setTitle(newTitle);
            cdList.get(pos).setPrice(newPrice);
            cdList.get(pos).setYear(newYear);
        } else {
            System.out.println("CD does not exit!");
        }
    }

    public void deleteCD(String delID) {
        int pos = checkCD_ID(delID);
        if (pos > 0) {
            cdList.remove(pos);
        } else {
            System.out.println("CD does not exit!");
        }
    }

    public void saveCD() {
        MyTools.writeFile(dataFile, cdList);
    }
}
