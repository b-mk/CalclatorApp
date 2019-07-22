import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MyFrame extends Frame implements ActionListener{
    HashMap<String, Button>button = new HashMap<>();
    //Button numButton[] = new Button[10];
    Label clickedNum;

    public MyFrame() {
        ArrayList<String> symbols = new ArrayList<String>(Arrays.asList("+", "-", "*", "/", "="));

        setTitle("CalculatorApp");
        setSize(800, 600);
        addWindowListener(new MyWindowAdapter());
        setLayout(new FlowLayout());
        clickedNum = new Label("0");
        add(clickedNum);
        for (int i = 0; i < 10; i++) {
            String buttonName = String.valueOf(i);
            button.put(buttonName, new Button(buttonName));
            button.get(buttonName).addActionListener(this);
            add(button.get(buttonName));
        }
        for (String buttonName : symbols) {
            button.put(buttonName, new Button(buttonName));
            button.get(buttonName).addActionListener(this);
            add(button.get(buttonName));
        }

    }
    public void actionPerformed(ActionEvent ae) {
        for (String key: button.keySet()) {
            if (ae.getSource() == button.get(key)) {
                System.out.println("Button" + key + "was clicked.");
                clickedNum.setText(key);
            }
        }
        // for (int i = 0; i < 10; i++) {
        //     if (ae.getSource() == numButton[i]) {
        //         System.out.println("Button" + i + " was clicked.");
        //         clickedNum.setText(String.valueOf(i));
        //         break;
        //     }
        // }
    }
}