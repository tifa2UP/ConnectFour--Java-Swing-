/**
 * Created by Abdellatif.
 */

/**
 * This is the "brain" behind the connect four game
 */
public class ConnectFour {

    //the structure of the board
    //the first array contains the x component, the second part contains the y one
    private Chip[][] structure;
    //the available position of the next insert (since the chip has to be above another one unless the column is empty)
    private int[] positionForInsert;
    //the first player
    private Player player1;
    //the second player
    private Player player2;
    //the number of connections required to win the game
    private int connectionsRequired;

    public ConnectFour(int boardSize, Player player1, Player player2, int connectionsRequired) {
        //create a new empty board of Chips
        structure = new Chip[boardSize][boardSize];
        //initialize the new position for insert at any column to 0
        positionForInsert = new int[boardSize];
        for (int i = 0; i < boardSize; i++) {
            positionForInsert[i] = 0;
        }
        this.player1 = player1;
        this.player2 = player2;
        this.connectionsRequired = connectionsRequired;
    }

    /**
     * checks whether there's a win condition
     * @param x the coloumn position of the insert
     * @param player the player who inserted the chip
     * @return true if one of the players wins
     */
    public boolean didWin(int x, Player player) {
        Chip chip = player.getChip();
        int y = positionForInsert[x] - 1;
        int counter = 0;
        //check horizontally
        for (int i = 0; i < structure.length; i++) {
            if (structure[i][y] != null && chip.getIdentifier() == structure[i][y].getIdentifier()) {
                counter++;
                if (counter >= connectionsRequired) {
                    return true;
                }
            } else {
                counter = 0;
            }
        }
        //check vertically
        counter = 0;
        for (int i = 0; i < structure.length; i++) {
            if (structure[x][i] != null && chip.getIdentifier() == structure[x][i].getIdentifier()) {
                counter++;
                if (counter >= connectionsRequired) {
                    return true;
                }
            } else {
                counter = 0;
            }

        }
        //reset the counter
        counter = 0;
        //TODO: implement the two diagonal orientations
        int xReplica = x;
        int yReplica = y;
        //check the positive slope diagonal
        //start from the chip position and decrement x and y until the position hits a boundary
        while (xReplica > 0 && yReplica > 0) {
            xReplica--;
            yReplica--;
        }

        for (int i = 0; i < structure.length; i++) {
            if (structure[xReplica][yReplica] != null && chip.getIdentifier() == structure[xReplica][yReplica].getIdentifier()) {
                counter++;
                xReplica++;
                yReplica++;
                if (counter >= connectionsRequired) {
                    return true;
                }
                //break the loop when it's out of bounds
                if (xReplica < 0 || yReplica < 0 || xReplica > structure.length - 1 || yReplica > structure.length - 1){
                    counter = 0;
                    break;
                }
            } else {
                counter = 0;
            }
        }
        //check the negative slope diagonal for a win condition
        //reset the counter
        counter = 0;
        //reinitialize the x and y components to the original chip position
        xReplica = x;
        yReplica = y;
        //traverse the board in the negative slope until the position hits a border
        while (xReplica < structure.length - 1 && yReplica > 0) {
            xReplica++;
            yReplica--;
        }
        for (int i = 0; i < structure.length; i++) {
            if (structure[xReplica][yReplica] != null && chip.getIdentifier() == structure[xReplica][yReplica].getIdentifier()) {
                counter++;
                xReplica--;
                yReplica++;
                if (counter >= connectionsRequired) {
                    return true;
                }
                //break the loop when it's out of bounds
                if (xReplica < 0 || yReplica < 0 || xReplica > structure.length - 1 || yReplica > structure.length - 1){
                    break;
                }
            } else {
                counter = 0;
            }

        }
        return false;
    }

    /**
     * prints out the board in the terminal (for debugging purposes)
     */
    public void printBoard() {
        for (int y = structure.length - 1; y >= 0; y--) {
            for (int x = 0; x < structure.length ; x++) {
                if (structure[x][y] == null) {
                    System.out.print(" | -");
                } else {
                    System.out.print(" | " + structure[x][y].getIdentifier());
                }
            }
            //create a new line after every row
            System.out.println();
        }
        System.out.println("==============");
    }

    /**
     * inserts a chip at a given column
     * @param columnPosition the position the user is inserting the chip at
     * @param player the player who's inserting the chip
     *
     */
    public void insertChip(int columnPosition, Player player) {
        //throws an exception if the input is not valid
        if (!isValidInput(columnPosition)){
            throw new IndexOutOfBoundsException("" + columnPosition);
        }
        //Create a new Chip that belongs a player
        Chip newChip = player.getChip();
        //insert a new chip a given column
        //the first part of the arrays is height which is given by the number of chips that are present in that column
        //the second part is simply the column in that the player puts the chip in
        structure[columnPosition][(positionForInsert[columnPosition])] = newChip;
        //increment the position for insert at that given column
        positionForInsert[columnPosition] = positionForInsert[columnPosition] + 1;
    }

    /**
     * Checks whether a given input is a valid insert
     * @param columnPosition the x position of the input
     * @return true if the input is valid, and false if it results in an error
     */
    private boolean isValidInput(int columnPosition){
        if (columnPosition < 0 || columnPosition >= structure.length){
            return false;
        }
        else if (positionForInsert[columnPosition] >= structure.length){
            return false;
        }
        return true;
    }

}






