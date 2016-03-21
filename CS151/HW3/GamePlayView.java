package CS151.HW3;

import java.awt.*;
import javax.swing.*;

/**
 * Created by Mohammad on 3/20/16.
 * This class contains the user interface that will carry out the game functions as well as display the game
 */
public class GamePlayView extends JFrame{


    public GamePlayView(){
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("2 Player Connect Four");
        this.setLayout(null);





        this.setVisible(true);

    }
}
