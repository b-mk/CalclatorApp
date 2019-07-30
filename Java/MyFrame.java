import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MyFrame extends Frame implements ActionListener{
    HashMap<String, Button>button = new HashMap<>();

    Label clickedNum;
    float result, tmp, digitNumber = 0.1f;
    char operator = 'n';
    boolean isTmpDemical = false, isoperatorClicked;
    ArrayList<Character> operators;

    public MyFrame() {
        operators = new ArrayList<Character>(Arrays.asList('+', '-', '*', '/', '=', '.', 'C'));

        setTitle("CalculatorApp");
        setSize(800, 600);
        addWindowListener(new MyWindowAdapter());
        setLayout(new FlowLayout());
        clickedNum = new Label("0");
        add(clickedNum);

        makeButton(10);
        makeButton(operators);
    }

    public void actionPerformed(ActionEvent ae) {
        Button a = (Button)ae.getSource();
        String pressedButton = a.getLabel();
        System.out.println(pressedButton + "was clicked.");
        boolean flag = true;
        
        if (pressedButton.equals("=")) {
            dispResult(calculationResult());
            resetTmp();
            this.operator = ' ';
            flag = false;
        }
        else if (pressedButton.equals("C")) {
            dispResult("");
            resetTmp();
            result = 0;
            operator = 'n';
            flag = false;
        }
        else if (pressedButton.equals(".")) {
            isTmpDemical = true;
            flag = false;
        }
        if (flag) {
            for (Character operator : operators) {
                //TODO: fix later
                if (pressedButton.toCharArray()[0] == operator) {
                    dispResult(calculationResult());
                    this.operator = operator;
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
        System.out.println("tmp = " + tmp + ", result = " + result + ", operator = " + operator);
    }

    private void resetTmp() {
        tmp = 0;
        isTmpDemical = false;
        digitNumber = 0.1f;
    }

    private String calculationResult() {
        try {
            switch(operator) {
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
                case 'n':
                    result = tmp;
                    break;
            }
            return String.valueOf(result);
        } catch(Exception e) {
            return String.valueOf(e);
        }
    }

    private void dispResult(String message) {
        clickedNum.setText(message);
    }

    private void makeButton(int n) {
        for (int i = 0; i < n; i++) {
            String buttonName = String.valueOf(i);
            button.put(buttonName, new Button(buttonName));
            button.get(buttonName).addActionListener(this);
            add(button.get(buttonName));
        }
    }
    private void makeButton(ArrayList<Character> c) {
        for (Character name : c) {
            String buttonName = String.valueOf(name);
            button.put(buttonName, new Button(buttonName));
            button.get(buttonName).addActionListener(this);
            add(button.get(buttonName));
        }
    }
}