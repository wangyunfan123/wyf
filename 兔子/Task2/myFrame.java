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
        //在方法内部，首先创建了一个JPanel对象panel，并设置了流式布局。
        // 然后创建了三个文本框field1、field2和field3，两个标签input1和input2，以及一个按钮button。
        // 这些组件被添加到面板中，并使用了流式布局使它们水平排列。
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
        //接下来，对文本框进行了限制，只允许用户输入数字。这是通过将每个文本框的文档设置为NumberTextField来实现的，可能是一个自定义的类。
        field1.setDocument(new NumberTextField());
        field2.setDocument(new NumberTextField());
        field3.setDocument(new NumberTextField());

        //Button action listener
        //然后，对按钮添加了动作监听器。当按钮被点击时，将执行监听器中的actionPerformed方法。
        // 在这个方法中，首先检查用户是否已经在所有文本框中输入了内容。如果是，那么获取用户输入的数字，并与预先设置的答案进行比较。
        // 如果答案正确，文本框被清空，并且顶部、左侧和右侧的组件也被重新设置。如果答案不正确，只会重新设置顶部的组件。
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
        //最后，将面板返回作为方法的结果。
        return panel;
    }

    //Left component
    public Component initImageL() {

        //Get random number
        //在方法内部，首先创建了一个Random对象random，用于生成随机数。
        // 然后使用nextInt(10) + 1生成一个介于1和10之间的随机数，并将其保存在变量r中，然后通过调用setR1(r)方法将其设置为类中的属性。
        Random random = new Random();
        int r = random.nextInt(10) + 1;
        setR1(r);

        //Place patterns so that there are up to 3 patterns per row
        //接着，创建了一个JPanel对象panel，并设置了网格布局，
        // 网格的行数设置为0，列数设置为3，这样可以让面板根据添加的组件动态调整行数，每行最多放置3个组件。
        //然后使用for循环向面板中添加图像标签。循环从0开始，直到达到随机生成的数r为止。
        // 在每次循环中，创建一个JLabel对象rabbit，将一个名为"rabbit.jpg"的图像作为标签的内容，并将其添加到面板中。
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
