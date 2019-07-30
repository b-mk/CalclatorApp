import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MyFrame extends Frame implements ActionListener{
    HashMap<String, Button>button = new HashMap<>();

    Label displayContent;
    double result, tmp, digitNumber = 0.1;
    char operator = 'n';
    boolean isTmpDemical = false, isOperatorClicked = false;
    ArrayList<Character> operators;

    public MyFrame() {
        setLayout();

        operators = new ArrayList<Character>(Arrays.asList('+', '-', '*', '/', '=', '.', 'C'));
        makeButton(10);
        makeButton(operators);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Button b = (Button)ae.getSource();
        Character pressedButton = b.getLabel().toCharArray()[0];
        System.out.println(pressedButton + "was clicked.");
        
        if (pressedButton == '=') {
            displayContent.setText(calculationResult());
            resetTmp();
            operator = ' ';
            isOperatorClicked = false;
        }
        else if (pressedButton == 'C') {
            displayContent.setText("");
            resetTmp();
            result = 0;
            operator = 'n';
            isOperatorClicked = false;
        }
        else if (pressedButton == '.') {
            isTmpDemical = true;
            isOperatorClicked = false;
        }
        else {
            boolean isNumber = true;
            for (Character operator : operators) {
                if (pressedButton == operator) {
                    if (!isOperatorClicked) displayContent.setText(calculationResult());
                    this.operator = operator;
                    isOperatorClicked = true;
                    isNumber = false;
                    resetTmp();
                    break;
                }
            }
            if (isNumber) {
                if (isTmpDemical) {
                    tmp = tmp + Double.valueOf(Character.toString(pressedButton)) * digitNumber;
                    digitNumber *= 0.1;
                } else {
                    tmp = tmp * 10 + Double.valueOf(Character.toString(pressedButton));
                }
                displayContent.setText(String.valueOf(tmp));
                isOperatorClicked = false;
    
            }
        }
        System.out.println("tmp = " + tmp + ", result = " + result + ", operator = " + operator);
    }

    private void resetTmp() {
        tmp = 0;
        isTmpDemical = false;
        digitNumber = 0.1;
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
                    if(tmp == 0) return ("Do not divide by 0. Press 'C'.");
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

    private void setLayout() {
        setTitle("CalculatorApp");
        setSize(400, 300);
        addWindowListener(new MyWindowAdapter());
        setLayout(new FlowLayout());
        displayContent = new Label("0");
        add(displayContent);
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