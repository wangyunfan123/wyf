import javax.swing.*;
import java.awt.*;

public class Income extends JFrame {

    public Income() {
        setTitle("Income and Expense");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建按钮
        JButton incomeButton = new JButton("收入");
        JButton expenseButton = new JButton("支出");

        // 创建面板，并设置流式布局
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(incomeButton);
        buttonPanel.add(expenseButton);

        // 添加面板到窗口中
        getContentPane().add(buttonPanel);

        // 调整窗口大小，使其适应内容
        pack();
        setLocationRelativeTo(null); // 将窗口居中显示
    }

    public static void main(String[] args) {
        // 在事件调度线程中创建和显示GUI
        SwingUtilities.invokeLater(() -> {
            Income gui = new Income();
            gui.setVisible(true);
        });
    }
}
