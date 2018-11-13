package com.codecool.singletonDojo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Printer {

    private static Random rand = new Random();

    private static int printerId;
    private static Printer instance = null;
    private static List<Printer> printers = new ArrayList<>();

    private int id; // ID of the printer. Unique.
    private LocalTime busyEndTime;

    private Printer(int id) {
        this.id = id;
        printers.add(this);
    }

    static Printer getInstance(){
        if (printers.size() < 10){
            instance = new Printer(++printerId);
        } else {
            for (Printer printer : printers){
                if (printer.isAvailable()){
                    return printer;
                }
            }
            return printers.get(rand.nextInt(printers.size()));
        }
        return instance;
    }

    // Prints out the given String
    void print(String toPrint) {
        // Its not needed to actually print with a printer in this exercise
        System.out.println("Printer " + id + " is printing this: " + toPrint);
        busyEndTime = LocalTime.now().plusSeconds(5);
    }

    // Returns true if the printer is ready to print now.
    boolean isAvailable() {
        return LocalTime.now().isAfter(busyEndTime);
    }
}
