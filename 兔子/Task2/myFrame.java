import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class myFrame extends JFrame {
    int r1 = 0;//The number of rabbits on the left
    int r2 = 0;//The number of rabbits on the right
    JPanel top = new JPanel();//Top panel
    JPanel but = new JPanel();//Button panel
    JPanel left = new JPanel();//Left panel
    JPanel right = new JPanel();//Right panel
    public int getR1() {
        return r1;
    }
    public int getR2() {
        return r2;
    }
    public void setR1(int r1) {
        this.r1 = r1;
    }
    public void setR2(int r2) {
        this.r2 = r2;
    }

    public myFrame() {
        initFrame();//Initialize frame

        //Assemble frame
        top.add(initTop("Enter two operands, result and click on 'Check!'"));
        left.add(initImageL());
        right.add(initImageR());
        but.add(initBut());
        this.getContentPane().add(BorderLayout.NORTH, top);
        this.getContentPane().add(BorderLayout.WEST, left);
        this.getContentPane().add(BorderLayout.CENTER, initPlus());
        this.getContentPane().add(BorderLayout.EAST, right);
        this.getContentPane().add(BorderLayout.SOUTH, but);

        this.setVisible(true);//Frame visualization
    }

    //Initialize frame
    public void initFrame() {
        this.setSize(700, 600);
        this.setTitle("Welcome to Sum It Up!");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //Top component
    public Component initTop(String text) {
        JLabel top = new JLabel(text, JLabel.CENTER);
        return top;
    }

    //Plus component
    public Component initPlus() {
        JLabel jLabel = new JLabel(new ImageIcon("image/plus.png"));
        return jLabel;
    }

    //Bottom component
    public Component initBut() {

        //Create and assemble the components
        JPanel panel = new JPanel(new FlowLayout());
        JTextField field1 = new JTextField(10);
        field1.setText("");
        JLabel input1 = new JLabel("+");
        JTextField field2 = new JTextField(10);
        field2.setText("");
        JLabel input2 = new JLabel("=");
        JTextField field3 = new JTextField(10);
        field3.setText("");
        JButton button = new JButton("Check!");
        panel.add(field1);
        panel.add(input1);
        panel.add(field2);
        panel.add(input2);
        panel.add(field3);
        panel.add(button);

        //Restrict the user to enter only numbers
        field1.setDocument(new NumberTextField());
        field2.setDocument(new NumberTextField());
        field3.setDocument(new NumberTextField());

        //Button action listener
        button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                //Compare the numbers entered by the user with the answers
                if (!("".equals(field1.getText())) && !("".equals(field2.getText())) && !("".equals(field3.getText()))){

                    //Gets the number entered by the user
                    String input1 = field1.getText();
                    String input2 = field2.getText();
                    String input3 = field3.getText();

                    if(input1.equals(String.valueOf(getR1())) && input2.equals(String.valueOf(getR2())) && input3.equals(String.valueOf(getR1()+getR2()))){

                        //Reset text box
                        field1.setText("");
                        field2.setText("");
                        field3.setText("");

                        //Reset top component
                        top.removeAll();
                        top.revalidate();
                        top.repaint();
                        top.add(initTop("Correct! Have another go?"));

                        //Reset left component
                        left.removeAll();
                        left.revalidate();
                        left.repaint();
                        left.add(initImageL());

                        //Reset right component
                        right.removeAll();
                        right.revalidate();
                        right.repaint();
                        right.add(initImageR());
                    } else if (!(input1.equals(String.valueOf(getR1()))) || !(input2.equals(String.valueOf(getR2()))) || !(input3.equals(String.valueOf(getR1()+getR2())))) {

                        //Reset top component
                        top.removeAll();
                        top.revalidate();
                        top.repaint();
                        top.add(initTop("Wrong! Try again!"));
                    }
                }
                setVisible(true);
            }
        });
        return panel;
    }

    //Left component
    public Component initImageL() {

        //Get random number
        Random random = new Random();
        int r = random.nextInt(10) + 1;
        setR1(r);

        //Place patterns so that there are up to 3 patterns per row
        int num = 0;
        JPanel panel = new JPanel(new GridLayout(0, 3));
        for (num = 0; num < r; num++) {
            JLabel rabbit = new JLabel(new ImageIcon("image/rabbit.jpg"));
            panel.add(rabbit);
        }
        return panel;
    }

    //Right component
    public Component initImageR() {

        //Get random number
        Random random = new Random();
        int r = random.nextInt(10) + 1;
        setR2(r);

        //Place patterns so that there are up to 3 patterns per row
        int num = 0;
        JPanel panel = new JPanel(new GridLayout(0, 3));
        for (num = 0; num < r; num++) {
            JLabel rabbit = new JLabel(new ImageIcon("image/rabbit.jpg"));
            panel.add(rabbit);
        }
        return panel;
    }
}
