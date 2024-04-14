import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 用于保存总金额的类
class Account {
    static double totalAmount = 0; // 静态变量，所有实例共享
}

public class Income extends JFrame {
    private JPanel incomePanel;
    private JPanel expensePanel;
    private JPanel totalAmountPanel;
    private JLabel totalAmountLabel;
    private CardLayout cardLayout;

    public Income() {
        try {
            // 设置Nimbus Look and Feel以提供更现代的外观
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // 如果Nimbus不可用，设置为系统默认的Look and Feel
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                // 如果出错，则忽略错误，使用默认的Look and Feel
            }
        }

        setTitle("Income and Expense Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton incomeButton = new JButton("收入");
        JButton expenseButton = new JButton("支出");

        cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        createIncomePanel();
        createExpensePanel();
        createTotalAmountPanel();

        mainPanel.add(incomePanel, "Income");
        mainPanel.add(expensePanel, "Expense");

        getContentPane().add(mainPanel);
        getContentPane().add(totalAmountPanel, BorderLayout.SOUTH);

        incomeButton.addActionListener(e -> cardLayout.show(mainPanel, "Income"));
        expenseButton.addActionListener(e -> cardLayout.show(mainPanel, "Expense"));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(incomeButton);
        buttonPanel.add(expenseButton);

        getContentPane().add(buttonPanel, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null); // 将窗口居中显示
    }

    private void createTotalAmountPanel() {
        totalAmountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        totalAmountLabel = new JLabel("总金额: " + Account.totalAmount + "元");
        totalAmountLabel.setFont(new Font("宋体", Font.BOLD, 16));
        totalAmountPanel.add(totalAmountLabel);
    }

    private void updateTotalAmountDisplay() {
        totalAmountLabel.setText("总金额: " + Account.totalAmount + "元");
    }

    private void createIncomePanel() {
        incomePanel = new JPanel(new GridLayout(3, 1, 10, 10)); // 增加行间距和列间距
        incomePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 增加边距
        incomePanel.setBackground(Color.BLUE); // 设置背景颜色为蓝色

        JPanel depositPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        depositPanel.setBackground(Color.GREEN); // 设置背景颜色为绿色
        JLabel depositLabel = new JLabel("存款:");
        JTextField depositTextField = new JTextField(10);
        depositPanel.add(depositLabel);
        depositPanel.add(depositTextField);
        incomePanel.add(depositPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.RED); // 设置背景颜色为红色
        JButton houseworkButton = new JButton("做家务");
        JButton progressButton = new JButton("进步");
        JButton otherButton = new JButton("其他");
        buttonPanel.add(houseworkButton);
        buttonPanel.add(progressButton);
        buttonPanel.add(otherButton);
        incomePanel.add(buttonPanel);

        // 设置字体
        depositLabel.setFont(new Font("宋体", Font.BOLD, 14));
        depositTextField.setFont(new Font("宋体", Font.PLAIN, 14));
        houseworkButton.setFont(new Font("宋体", Font.BOLD, 14));
        progressButton.setFont(new Font("宋体", Font.BOLD, 14));
        otherButton.setFont(new Font("宋体", Font.BOLD, 14));

        // 添加事件处理器，更新总金额显示
        ActionListener incomeActionListener = e -> {
            try {
                String depositAmount = depositTextField.getText();
                double deposit = Double.parseDouble(depositAmount);
                String incomeSource = e.getActionCommand(); // 收入来源
                Account.totalAmount += deposit; // 更新总金额
                updateTotalAmountDisplay(); // 更新总金额显示
                JOptionPane.showMessageDialog(null, "您本次存款" + depositAmount + "元，收入来源是" + incomeSource + "，账户总金额为" + Account.totalAmount + "元");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "请输入有效的金额数值");
            }
        };

        houseworkButton.addActionListener(incomeActionListener);
        progressButton.addActionListener(incomeActionListener);
        otherButton.addActionListener(incomeActionListener);
    }

    private void createExpensePanel() {
        expensePanel = new JPanel(new GridLayout(3, 1, 10, 10));
        expensePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 增加边距
        expensePanel.setBackground(Color.LIGHT_GRAY); // 设置背景颜色为灰色

        JPanel withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        withdrawalPanel.setBackground(Color.YELLOW); // 设置背景颜色为黄色
        JLabel withdrawalLabel = new JLabel("提款:");
        JTextField withdrawalTextField = new JTextField(10);
        withdrawalPanel.add(withdrawalLabel);
        withdrawalPanel.add(withdrawalTextField);
        expensePanel.add(withdrawalPanel);

        JButton confirmExpenseButton = new JButton("确认");
        confirmExpenseButton.setBackground(Color.YELLOW); // 设置按钮背景颜色为黄色
        expensePanel.add(confirmExpenseButton);

        // 设置字体
        withdrawalLabel.setFont(new Font("宋体", Font.BOLD, 14));
        withdrawalTextField.setFont(new Font("宋体", Font.PLAIN, 14));
        confirmExpenseButton.setFont(new Font("宋体", Font.BOLD, 14));

        // 添加确认按钮的事件处理
        confirmExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String withdrawalAmount = withdrawalTextField.getText();
                    double withdrawal = Double.parseDouble(withdrawalAmount);
                    if (withdrawal > Account.totalAmount) {
                        JOptionPane.showMessageDialog(null, "账户余额不足，无法完成提款！");
                    } else {
                        Account.totalAmount -= withdrawal; // 更新总金额
                        updateTotalAmountDisplay(); // 更新总金额显示
                        JOptionPane.showMessageDialog(null, "您已取款" + withdrawalAmount + "元，账户总金额为" + Account.totalAmount + "元");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "请输入有效的金额数值");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Income gui = new Income();
            gui.setVisible(true);
        });
    }
}
