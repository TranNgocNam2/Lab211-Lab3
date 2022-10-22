/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author ADMIN
 */
public class CD implements Comparable<CD> {

    public static final String SEPARATOR = ",";
    private String id;
    private String name;
    private String type;
    private String title;
    private int price;
    private int year;

    public CD() {
    }

    public CD(String line) {
        String[] parts = line.split(this.SEPARATOR);
        id = parts[0].trim();
        name = parts[1].trim();
        type = parts[2].trim();
        title = parts[3].trim();
    }

    public CD(String id, String name, String type, String title, int price, int year) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.title = title;
        this.price = price;
        this.year = year;
    }

  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + type + ", " + title + ", " + price + ", " + year + "\n";
    }

    @Override
    public int compareTo(CD t) {
        return t.getPrice() - this.getPrice();
    }
}
