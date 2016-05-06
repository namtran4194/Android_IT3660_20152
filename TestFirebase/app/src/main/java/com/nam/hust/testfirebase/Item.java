package com.nam.hust.testfirebase;

/**
 * Created by Legendary on 27/04/2016.
 */
public class Item {
    private String cost;
    private String date;
    private int id;
    private String name;
    private String note;
    private String type;

    public Item() {
    }
//
//    public Item(String name, String cost, String type, String note, String date, int id) {
//        this.name = name;
//        this.cost = cost;
//        this.type = type;
//        this.note = note;
//        this.date = date;
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public String getCost() {
        return cost;
    }

    public String getType() {
        return type;
    }

    public String getNote() {
        return note;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }
}
