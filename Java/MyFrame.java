import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MyFrame extends Frame implements ActionListener{

    Label resultLabel;
    double result, tmp, digitNumber = 0.1;
    char operator = 'n';
    boolean isTmpDemical = false, isOperatorClicked = false;
    boolean inputAllowed = true;
    ArrayList<Character> operators;
    final int width = 500, height = 400;

    public MyFrame() {
        setTitle("CalculatorApp");
        addWindowListener(new MyWindowAdapter());

        resultLabel = new Label("0", Label.CENTER);
        operators = new ArrayList<Character>(Arrays.asList('+', '-', '*', '/', '=', '.'));
        
        setLayout(new GridLayout(2, 1));
        setSize(width, height);
        Panel resultArea = new Panel();
        Panel buttonArea = new Panel();
        add(resultArea);
        add(buttonArea);

        resultArea.setLayout(new GridLayout(1, 1));
        resultArea.add(resultLabel);

        buttonArea.setLayout(new GridLayout(1, 2));
        Panel numbersArea = new Panel();
        Panel operatorsArea = new Panel();
        buttonArea.add(numbersArea);
        buttonArea.add(operatorsArea);

        numbersArea.setLayout(new GridLayout(4, 3));
        for (int i = 0; i < 10; i++) addButton(String.valueOf(i), numbersArea);

        operatorsArea.setLayout(new GridLayout(2, 1));
        Panel clearArea = new Panel();
        Panel otherArea = new Panel();
        operatorsArea.add(clearArea);
        operatorsArea.add(otherArea);

        clearArea.setLayout(new GridLayout(1, 1));
        addButton("C", clearArea);

        otherArea.setLayout(new GridLayout(3, 2));
        for (Character name : operators) addButton(String.valueOf(name), otherArea);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Button b = (Button)ae.getSource();
        Character pressedButton = b.getLabel().toCharArray()[0];
        System.out.println(pressedButton + "was clicked.");

        if (pressedButton == 'C') {
            inputAllowed = true;
            resultLabel.setText("0");
            resetTmp();
            result = 0;
            operator = 'n';
            isOperatorClicked = false;
            return;
        }
        
        if (!inputAllowed) return;
        if (pressedButton == '=') {
            resultLabel.setText(calculationResult());
            resetTmp();
            operator = ' ';
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
                    if (!isOperatorClicked) resultLabel.setText(calculationResult());
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
                resultLabel.setText(String.valueOf(tmp));
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
                    if(tmp == 0) {
                        inputAllowed = false;
                        return ("Do not divide by 0. Press 'C'.");
                    }
                    result /= tmp;
                    break;
                case 'n':
                    result = tmp;
                    break;
            }
            return String.valueOf(result);
        } catch(Exception e) {
            inputAllowed = false;
            System.out.println(String.valueOf(e));
            return("An error has occurred. Press 'C'.");
        }
    }

    private void addButton(String label, Panel p) {
        String buttonName = label;
        Button button = new Button(buttonName);
        button.addActionListener(this);
        p.add(button);      
    }
}