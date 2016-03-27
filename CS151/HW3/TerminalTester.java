package CS151.HW3;

/**
 * Created by Abdellatif on 3/20/2016.
 * This class is created to test the Game through without the GUI
 */
public class TerminalTester {
    public static void main(String[] args) {
        Chip chip1 = new Chip("red", 1);
        Chip chip2 = new Chip("green", 2);
        Player abdellatif = new Player("Abdellatif", chip1);
        Player mohammad = new Player("Mohammad", chip2);

        ConnectFour board = new ConnectFour(4, abdellatif, mohammad,3);
        board.insertChip(1, abdellatif);
        System.out.println(board.didWin(1, abdellatif));
        board.insertChip(0, mohammad);
        board.printBoard();
        board.insertChip(2, abdellatif);
        board.printBoard();
        board.insertChip(0, mohammad);
        board.printBoard();
        board.insertChip(2, abdellatif);
        board.printBoard();
        board.insertChip(0, mohammad);
        board.printBoard();
        System.out.println(board.didWin(0, mohammad));

        //TODO: fix the bug in the orientation
//        System.out.println(board.didWin(0, chip2));
        //TODO: fix the bug for the -1 index
//        board.insertChip(1, abdellatif);
//        System.out.println(board.didWin(1, chip1));
//        board.insertChip(0, mohammad);


//        board.insertChip(, mohammad);
//        board.insertChip(1, abdellatif);
        board.printBoard();
    }
}
