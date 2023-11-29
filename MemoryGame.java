import javax.swing.JButton;
import javax.swing.JFrame;
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
    
    public MemoryGame() {
        super("Memory Game");

        GridLayout gridLayout = new GridLayout(ROWS, COLUMNS);

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

    private void ButtonClicked(ActionEvent actionEvent){
        JButton button = (JButton)actionEvent.getSource();

        int index = buttonList.indexOf(button);
        Color color = colorsList.get(index);
        button.setBackground(color);

        //int index = buttonList.indexOf(button);
        //Color color = colorList.get(index);
        //button.setBackground(color);
        //button.setText("" + index);
    }

    private void InitColorsList() {
        Collections.addAll(colorsList, Color.CYAN, Color.CYAN, Color.MAGENTA, Color.MAGENTA, Color.PINK, Color.PINK, Color.BLACK, Color.BLACK,Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW );
        Collections.shuffle(colorsList);
    }
}