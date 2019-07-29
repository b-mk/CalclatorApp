import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MyFrame extends Frame implements ActionListener{
    HashMap<String, Button>button = new HashMap<>();

    Label clickedNum;
    float result, tmp;
    String operand = "";
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
        Button a = (Button)ae.getSource();
        String pressedButton = a.getLabel();
        System.out.println(pressedButton + "was clicked.");
        boolean flag = true;
        if (pressedButton.equals("=")) {
            clickedNum.setText(calculationResult());
            tmp = 0;
            flag = false;
        }
        if (flag) {
            for (String operand : symbols) {
                if (pressedButton.equals(operand)) {
                    if (this.operand.equals("")) {
                        calculationResult();
                    }
                    this.operand = operand;
                    clickedNum.setText(String.valueOf(result));
                    flag = false;
                    tmp = 0;
                }
            }  
        }
        if (flag) {
            tmp = tmp * 10 + Integer.valueOf(pressedButton);
        }
        System.out.println("tmp = " + tmp + ", result = " + result);
    }

    private String calculationResult() {
        try {
            switch(operand) {
                case "+":
                    result += tmp;
                    break;
                case "-":
                    result -= tmp;
                    break;
                case "*":
                    result *= tmp;
                    break;
                case "/":
                    result /= tmp;
                    break;
                default:
                    result = tmp;
            }
            return String.valueOf(result);
        } catch(Exception e) {
            return String.valueOf(e);
        }
    }
}