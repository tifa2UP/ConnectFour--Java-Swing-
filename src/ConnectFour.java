package src;

/**
 * Created by Abdellatif.
 */

/**
 * This is the "brain" behind the connect four game
 */
public class ConnectFour {

    //the structure of the board
    Chip[][] structure;
    //the available position of the next insert (since the chip has to be above another one unless the coloumn is empty)
    int[] positionForInsert;

    public ConnectFour(int boardSize) {
        //create a new empty board of Chips
        structure = new Chip[boardSize][boardSize];
        positionForInsert = new int[boardSize];
        for (int i = 0; i < boardSize; i++) {
            positionForInsert[i] = 0;
        }

    }


}
