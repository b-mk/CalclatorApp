import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MyFrame extends Frame implements ActionListener{
    ArrayList<Button>button = new ArrayList<>();

    Label resultLabel;
    double result, tmp, digitNumber = 0.1;
    char operator = 'n';
    boolean isTmpDemical = false, isOperatorClicked = false;
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
        int i;
        for (i = 0; i < 10; i++) {
            String buttonName = String.valueOf(i);
            button.add(new Button(buttonName));
            button.get(i).addActionListener(this);
            numbersArea.add(button.get(i));
        }

        operatorsArea.setLayout(new GridLayout(2, 1));
        Panel clearArea = new Panel();
        Panel otherArea = new Panel();
        operatorsArea.add(clearArea);
        operatorsArea.add(otherArea);

        button.add(new Button("C"));
        button.get(i).addActionListener(this);
        clearArea.setLayout(new GridLayout(1, 1));
        clearArea.add(button.get(i));
        i++;

        otherArea.setLayout(new GridLayout(3, 2));
        for (Character name : operators) {
            String buttonName = String.valueOf(name);
            button.add(new Button(buttonName));
            button.get(i).addActionListener(this);
            otherArea.add(button.get(i));
            i++;
        }
        //setBackground(Color.MAGENTA);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Button b = (Button)ae.getSource();
        Character pressedButton = b.getLabel().toCharArray()[0];
        System.out.println(pressedButton + "was clicked.");
        
        if (pressedButton == '=') {
            resultLabel.setText(calculationResult());
            resetTmp();
            operator = ' ';
            isOperatorClicked = false;
        }
        else if (pressedButton == 'C') {
            resultLabel.setText("");
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

}