import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;

public class MemoryGame extends JFrame {
    final int COLUMNS = 4;
    final int ROWS = 3;
    ArrayList<JButton> buttonList = new ArrayList<JButton>();
    ArrayList<Color> colorsList = new ArrayList<Color>();

    int match = 0;
    JButton initialSelection;
    int score = 0;
    JMenu menu;
    
    public MemoryGame() {
        super("Memory Game");

        GridLayout gridLayout = new GridLayout(ROWS, COLUMNS);

        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu("Score: " + score);
        JMenuItem jMenuItem = new JMenuItem("Restart Game");
        jMenuItem.addActionListener(e -> RestartGame());
        menu.add(jMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        

        for(int i = 0; i < ROWS* COLUMNS; i++ ){
            JButton button = new JButton();
            button.addActionListener(this::ButtonClicked);
            buttonList.add(button);
            add(button);
        }
        InitColorsList();
        setLayout(gridLayout);
        setSize(900, 900);
        setLocation(500, 0);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void RestartGame() {
        for(int i = 0; i < buttonList.size(); i++ ){
            JButton button = buttonList.get(i);
            button.setBackground(null);
            button.setEnabled(true);
        }

        score = 0;
        menu.setText("Score: " + score);
        match = 0;
        Collections.shuffle(colorsList);
    }

    private void ButtonClicked(ActionEvent actionEvent){
        JButton button = (JButton)actionEvent.getSource();

        int index = buttonList.indexOf(button);
        Color color = colorsList.get(index);
        button.setBackground(color);

        if(initialSelection == null){
            // then we know this is the first button click
            initialSelection = button;
            button.setEnabled(false);
        } else {
            if(initialSelection.getBackground().equals(button.getBackground())){
                // let user know of match
                button.setEnabled(false);
                match++;
                score += 10;

                if(match == 6){
                    JOptionPane.showMessageDialog(this, "Winner Winner");
                    RestartGame();
                }
            } else {
                // let user know there wasnt a match
                JOptionPane.showMessageDialog(this, "the colors dont match");

                // reset the buttons
                button.setEnabled(true);
                button.setBackground(null);
                initialSelection.setEnabled(true);
                initialSelection.setBackground(null);

                int newScore = score - 1;
                score = newScore < 0 ? 0 : newScore;
            }

            menu.setText("Score: " + score);

            // reset the first selection to null
            initialSelection = null;

        }
        



    }

    private void InitColorsList() {
        Collections.addAll(colorsList, Color.CYAN, Color.CYAN, Color.MAGENTA, Color.MAGENTA, Color.PINK, Color.PINK, Color.BLACK, Color.BLACK,Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW );
        Collections.shuffle(colorsList);
    }
}