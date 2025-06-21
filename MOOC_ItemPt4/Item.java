package MOOC;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; //display current time

public class Item {

    private String name;
    private LocalDateTime createdAt;//display current time

    public Item(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();//display current time
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"); //display current time

        return this.name + " (created at: " + formatter.format(this.createdAt) + ")";
    }
}

