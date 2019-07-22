import java.awt.*;
import java.awt.event.*;

public class MyFrame extends Frame implements ActionListener{
    Button b1;
    Label l1;

    public MyFrame() {
        setTitle("Test Window");
        setSize(800, 600);
        addWindowListener(new MyWindowAdapter());
        setLayout(new FlowLayout());
        l1 = new Label("0");
        add(l1);
        b1 = new Button("Button1");
        b1.addActionListener(this);
        add(b1);
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            System.out.println("Button1 was clicked");
            l1.setText("test");
        }
    }
}