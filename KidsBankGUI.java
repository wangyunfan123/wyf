import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KidsBankGUI {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    KidsBankGUI window = new KidsBankGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public KidsBankGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("Kids Bank");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(120, 30, 200, 50);
        frame.getContentPane().add(lblTitle);

        JButton btnLogin = new JButton("登录");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLoginPage();
            }
        });
        btnLogin.setBounds(60, 100, 100, 30);
        frame.getContentPane().add(btnLogin);

        JButton btnRegister = new JButton("注册");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRegisterPage();
            }
        });
        btnRegister.setBounds(220, 100, 100, 30);
        frame.getContentPane().add(btnRegister);
    }

    private void showLoginPage() {
        frame.getContentPane().removeAll();

        JLabel lblUserId = new JLabel("用户ID:");
        lblUserId.setBounds(60, 50, 80, 30);
        frame.getContentPane().add(lblUserId);

        JTextField txtUserId = new JTextField();
        txtUserId.setBounds(140, 50, 160, 30);
        frame.getContentPane().add(txtUserId);

        JLabel lblPassword = new JLabel("密码:");
        lblPassword.setBounds(60, 100, 80, 30);
        frame.getContentPane().add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(140, 100, 160, 30);
        frame.getContentPane().add(txtPassword);

        JButton btnSubmit = new JButton("提交");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userId = txtUserId.getText();
                String password = new String(txtPassword.getPassword());
                if (userId.equals("001") && password.equals("111")) {
                    showHomePage(userId);
                } else {
                    JOptionPane.showMessageDialog(frame, "不正确，请重新输入。", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnSubmit.setBounds(140, 150, 80, 30);
        frame.getContentPane().add(btnSubmit);

        JButton btnBack = new JButton("返回");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                initialize();
            }
        });
        btnBack.setBounds(140, 200, 80, 30);
        frame.getContentPane().add(btnBack);

        frame.repaint();
    }

    private void showRegisterPage() {
        frame.getContentPane().removeAll();

        JLabel lblName = new JLabel("姓名:");
        lblName.setBounds(60, 50, 80, 30);
        frame.getContentPane().add(lblName);

        JTextField txtName = new JTextField();
        txtName.setBounds(140, 50, 160, 30);
        frame.getContentPane().add(txtName);

        JLabel lblPassword = new JLabel("密码:");
        lblPassword.setBounds(60, 100, 80, 30);
        frame.getContentPane().add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(140, 100, 160, 30);
        frame.getContentPane().add(txtPassword);

        JLabel lblConfirmPassword = new JLabel("确认密码:");
        lblConfirmPassword.setBounds(40, 150, 100, 30);
        frame.getContentPane().add(lblConfirmPassword);

        JPasswordField txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(140, 150, 160, 30);
        frame.getContentPane().add(txtConfirmPassword);

        JButton btnSubmit = new JButton("提交");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String password = new String(txtPassword.getPassword());
                String confirmPassword = new String(txtConfirmPassword.getPassword());
                if (password.equals(confirmPassword)) {
                    // 注册成功，可以将用户信息保存到数据库或其他数据结构中
                    showHomePage(name);
                } else {
                    JOptionPane.showMessageDialog(frame, "两次密码不相同", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnSubmit.setBounds(140, 200, 80, 30);
        frame.getContentPane().add(btnSubmit);

        JButton btnBack = new JButton("返回");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                initialize();
            }
        });
        btnBack.setBounds(140, 250, 80, 30);
        frame.getContentPane().add(btnBack);

        frame.repaint();
    }

    private void showHomePage(String userId) {
        frame.getContentPane().removeAll();

        JLabel lblUserId = new JLabel("用户ID: " + userId);
        lblUserId.setBounds(140, 100, 160, 30);
        frame.getContentPane().add(lblUserId);

        frame.repaint();
    }
}