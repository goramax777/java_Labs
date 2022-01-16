import java.awt.*;

import javax.swing.*;

public class Avt extends JPanel{

    Dimension sizeWindow = Toolkit.getDefaultToolkit().getScreenSize();
    Image bg_launch = new ImageIcon("images/style/launch.jpg").getImage();

    JLabel loginLabel = new JLabel("Login: ");
    JTextField loginText = new JTextField(10);
    JLabel passwordLabel = new JLabel("Password: ");
    JPasswordField password = new JPasswordField(10);

    JButton submit = new JButton("Войти");

    public Avt() {
        //setLayout(null);
        loginLabel.setBounds(85, 10, 130, 18);
        loginText.setBounds(160, 10, 120, 20);
        passwordLabel.setBounds(85, 40, 120, 18);
        password.setBounds(160, 40, 120, 20);
        submit.setBounds(160, 70, 80, 30);

        add(loginLabel);
        add(loginText);
        add(passwordLabel);
        add(password);
        add(submit);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

            g.drawLine(30,30,30,30);

    }

    public static void main(String[] args) {

    }
}