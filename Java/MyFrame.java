import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MyFrame extends Frame implements ActionListener{
    HashMap<String, Button>button = new HashMap<>();

    Label resultLabel;
    double result, tmp, digitNumber = 0.1;
    char operator = 'n';
    boolean isTmpDemical = false, isOperatorClicked = false;
    ArrayList<Character> operators;

    public MyFrame() {
        setTitle("CalculatorApp");
        addWindowListener(new MyWindowAdapter());

        resultLabel = new Label("0", Label.CENTER);
        operators = new ArrayList<Character>(Arrays.asList('+', '-', '*', '/', '=', '.'));
        
        setLayout(new GridLayout(6, 5));

        final int panelNum = 4; 
        Panel panels[] = new Panel[panelNum];
        int coordinate[][] = {{1, 1}, {1, 3}, {4, 3}, {4, 4}};

        /*
        panels[0]:resultArea
        panels[1]:numbersArea
        panels[2]:clearArea
        panels[3]:operatorsArea
        */
        for (int i = 0; i < panelNum; i++) {
            panels[i] = new Panel();
            add(panels[i]);
            panels[i].setLayout(new GridLayout(coordinate[i][0], coordinate[i][1]));
        }
        panels[0].add(resultLabel);
        button.put("C", new Button("C"));
        button.get("C").addActionListener(this);
        panels[2].add(button.get("C"));

        for (int i = 0; i < 10; i++) {
            String buttonName = String.valueOf(i);
            button.put(buttonName, new Button(buttonName));
            button.get(buttonName).addActionListener(this);
            panels[1].add(button.get(buttonName));
        }
        for (Character name : operators) {
            String buttonName = String.valueOf(name);
            button.put(buttonName, new Button(buttonName));
            button.get(buttonName).addActionListener(this);
            panels[3].add(button.get(buttonName));
        }
        //setBackground(Color.MAGENTA);

        setSize(400, 300);
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