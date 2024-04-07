import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 新建一个类来保存总金额
class Account {
    static double totalAmount = 0; // 静态变量，所有实例共享
}

public class Income extends JFrame {
    private JPanel incomePanel;
    private JPanel expensePanel;
    private CardLayout cardLayout;

    public Income() {
        setTitle("Income and Expense");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建按钮
        JButton incomeButton = new JButton("收入");
        JButton expenseButton = new JButton("支出");

        // 创建面板，并设置卡片布局
        cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // 创建收入页面
        createIncomePanel();
        // 创建支出页面
        createExpensePanel();

        // 将收入和支出页面添加到主面板中
        mainPanel.add(incomePanel, "Income");
        mainPanel.add(expensePanel, "Expense");

        // 添加主面板到窗口中
        getContentPane().add(mainPanel);

        // 绑定按钮点击事件
        incomeButton.addActionListener(e -> cardLayout.show(mainPanel, "Income"));
        expenseButton.addActionListener(e -> cardLayout.show(mainPanel, "Expense"));

        // 创建按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(incomeButton);
        buttonPanel.add(expenseButton);

        // 添加按钮面板到窗口中
        getContentPane().add(buttonPanel, BorderLayout.NORTH);

        // 调整窗口大小，使其适应内容
        pack();
        setLocationRelativeTo(null); // 将窗口居中显示
    }

    private void createIncomePanel() {
        incomePanel = new JPanel(new GridLayout(3, 1)); // 更改为GridLayout，3行1列

        // 创建按钮面板，使得"做家务", "进步", "其他"按钮在同一行
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // 添加按钮
        JButton houseworkButton = new JButton("做家务");
        JButton progressButton = new JButton("进步");
        JButton otherButton = new JButton("其他");
        // 添加按钮到按钮面板
        buttonPanel.add(houseworkButton);
        buttonPanel.add(progressButton);
        buttonPanel.add(otherButton);
        // 将按钮面板添加到收入面板
        incomePanel.add(buttonPanel);

        // 创建存款面板，存款单独一行
        JPanel depositPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // 添加文本和输入框到存款面板
        JLabel depositLabel = new JLabel("存款:");
        JTextField depositTextField = new JTextField(10);
        depositPanel.add(depositLabel);
        depositPanel.add(depositTextField);
        // 将存款面板添加到收入面板
        incomePanel.add(depositPanel);

        // 创建确认按钮面板，确认按钮单独一行
        JPanel confirmPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // 添加确认按钮到确认按钮面板
        JButton confirmIncomeButton = new JButton("确认");
        confirmPanel.add(confirmIncomeButton);
        // 将确认按钮面板添加到收入面板
        incomePanel.add(confirmPanel);

        // 添加确认按钮点击事件
        confirmIncomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String depositAmount = depositTextField.getText();
                // 在此处添加保存存款金额的逻辑
                double deposit = Double.parseDouble(depositAmount);
                Account.totalAmount += deposit; // 更新总金额
                JOptionPane.showMessageDialog(null, "您本次存款" + depositAmount + "元，收入来源是做家务，账户总金额为" + Account.totalAmount + "元");
            }
        });
    }

    private void createExpensePanel() {
        expensePanel = new JPanel(new GridLayout(3, 1));
        // 添加文本和输入框到支出面板
        JLabel withdrawalLabel = new JLabel("提款:");
        JTextField withdrawalTextField = new JTextField(10);
        JPanel withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        withdrawalPanel.add(withdrawalLabel);
        withdrawalPanel.add(withdrawalTextField);
        expensePanel.add(withdrawalPanel);

        // 添加确认按钮到支出面板
        JButton confirmExpenseButton = new JButton("确认");
        expensePanel.add(confirmExpenseButton);

        // 添加确认按钮点击事件
        confirmExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String withdrawalAmount = withdrawalTextField.getText();
                double withdrawal = Double.parseDouble(withdrawalAmount);
                if (withdrawal > Account.totalAmount) {
                    JOptionPane.showMessageDialog(null, "账户余额不足，无法完成提款！");
                } else {
                    Account.totalAmount -= withdrawal; // 更新总金额
                    JOptionPane.showMessageDialog(null, "您已取款" + withdrawalAmount + "元，账户总金额为" + Account.totalAmount + "元");
                }
            }
        });
    }

    public static void main(String[] args) {
        // 在事件调度线程中创建和显示GUI
        SwingUtilities.invokeLater(() -> {
            Income gui = new Income();
            gui.setVisible(true);
        });
    }
}
