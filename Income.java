import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        incomePanel = new JPanel(new GridLayout(4, 3));
        // 添加按钮
        JButton houseworkButton = new JButton("做家务");
        JButton progressButton = new JButton("进步");
        JButton otherButton = new JButton("其他");
        // 添加按钮到收入面板
        incomePanel.add(houseworkButton);
        incomePanel.add(progressButton);
        incomePanel.add(otherButton);

        // 添加文本和输入框到收入面板
        JLabel depositLabel = new JLabel("存款:");
        JTextField depositTextField = new JTextField(10);
        JPanel depositPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        depositPanel.add(depositLabel);
        depositPanel.add(depositTextField);
        incomePanel.add(depositPanel);

        // 添加确认按钮到收入面板
        JButton confirmIncomeButton = new JButton("确认");
        incomePanel.add(confirmIncomeButton);

        // 添加确认按钮点击事件
        confirmIncomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String depositAmount = depositTextField.getText();
                // 在此处添加保存存款金额的逻辑
                System.out.println("收入确认: 存款金额为 " + depositAmount);
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
                // 在此处添加提款的逻辑
                System.out.println("支出确认: 提款金额为 " + withdrawalAmount);
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
