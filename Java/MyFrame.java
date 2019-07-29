import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MyFrame extends Frame implements ActionListener{
    HashMap<String, Button>button = new HashMap<>();

    Label clickedNum;
    int result, tmp;
    char operand;
    ArrayList<String> symbols;

    public MyFrame() {
        symbols = new ArrayList<String>(Arrays.asList("+", "-", "*", "/", "="));

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
        String pressedButton = ae.getSource();
        boolean flag;
        if (pressedButton == "=") {
            calculationResult();
            clickedNum.setText(String.valueOf(result));
            flag = false;
        }
        if (flag) {
            for (String operand : symbols) {
                if (pressedButton == operand) {
                    this.operand = operand;
                    clickedNum.setText("");
                    flag = false;
                }
            }  
        }
        if (flag) {
            tmp = tmp * 10 + pressedButton;
        }
    }

    private boolean calculationResult() {
        try {
            switch(operand) {
                case '+':
                    result += tmp;
                    break;
                case '-':
                    result -= tmp;
                    break;
                case '*':
                    result *= tmp;
                    break;
                case '/':
                    result /= tmp;
                    break;
            }
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}