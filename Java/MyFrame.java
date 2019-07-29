import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MyFrame extends Frame implements ActionListener{
    HashMap<String, Button>button = new HashMap<>();

    Label clickedNum;
    float result, tmp, digitNumber = 0.1f;
    String operand = "";
    boolean isTmpDemical = false;
    ArrayList<String> symbols;

    public MyFrame() {
        symbols = new ArrayList<String>(Arrays.asList("+", "-", "*", "/", "=", "C", "."));

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
            dispResult(calculationResult());
            resetTmp();
            flag = false;
        }
        else if (pressedButton.equals("C")) {
            dispResult("");
            resetTmp();
            result = 0;
            operand = "";
            flag = false;
        }
        else if (pressedButton.equals(".")) {
            isTmpDemical = true;
            flag = false;
        }
        if (flag) {
            for (String operand : symbols) {
                if (pressedButton.equals(operand)) {
                    dispResult(calculationResult());
                    this.operand = operand;
                    flag = false;
                    resetTmp();
                }
            }  
        }
        if (flag) {
            if (isTmpDemical) {
                tmp = tmp + Integer.valueOf(pressedButton) * digitNumber;
                digitNumber *= 0.1;
            } else {
                tmp = tmp * 10 + Integer.valueOf(pressedButton);
            }
            dispResult(String.valueOf(tmp));
        }
        System.out.println("tmp = " + tmp + ", result = " + result + ", operand = " + operand);
    }

    private void resetTmp() {
        tmp = 0;
        isTmpDemical = false;
        digitNumber = 0.1f;
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

    private void dispResult(String message) {
        clickedNum.setText(message);
    }
}