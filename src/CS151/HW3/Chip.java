package CS151.HW3;

/**
 * Created by Abdellatif.
 */

/**
 * This class represents the chip used in the game
 */
public class Chip {
    //the color of the chip
    private String color;
    //a unique identifier given to each type of chips
    private int identifier;

    public Chip(String color, int identifier) {
        this.color = color;
        this.identifier = identifier;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
}
