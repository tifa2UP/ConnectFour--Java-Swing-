package src.src;

/**
 * Created by Abdellatif.
 */
public class Player {
    //the name of the player
    private String name;
    //the chip type that the player is holding
    private Chip chip;

    public Player(String name, Chip chip) {
        this.name = name;
        this.chip = chip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Chip getChip() {
        return chip;
    }

    public void setChip(Chip chip) {
        this.chip = chip;
    }
}
