package model;

public class FreeRoom extends Room {

    public FreeRoom() {
        super.setPrice(0);
    }

    public String toString() {
        return(String.format("Room Number: %s %d bed room Price: %d - FreeRoom", super.getRoomNumber(), super.getRoomType().toString(), super.getPrice()));
    }
}
