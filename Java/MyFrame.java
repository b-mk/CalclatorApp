import java.awt.*;
import java.awt.event.*;

public class MyFrame extends Frame implements ActionListener{
    Button numButton[] = new Button[10];
    Label clickedNum;

    public MyFrame() {
        setTitle("CalculatorApp");
        setSize(800, 600);
        addWindowListener(new MyWindowAdapter());
        setLayout(new FlowLayout());
        clickedNum = new Label("0");
        add(clickedNum);
        for (int i = 0; i < 10; i++) {
            numButton[i] = new Button(String.valueOf(i));
            numButton[i].addActionListener(this);
            add(numButton[i]);
        }
    }
    public void actionPerformed(ActionEvent ae) {
        for (int i = 0; i < 10; i++) {
            if (ae.getSource() == numButton[i]) {
                System.out.println("Button" + i + " was clicked.");
                clickedNum.setText(String.valueOf(i));
                break;
            }
        }
    }
}