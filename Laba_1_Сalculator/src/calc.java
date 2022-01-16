import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calc {
    private JTextPane textPane1;
    private JTextPane textPane2;
    private JButton pmButton;
    private JButton pButton;
    private JButton a0Button;
    private JButton roButton;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton mButton;
    private JButton a4Button;
    private JButton divButton;
    private JButton a6Button;
    private JButton a5Button;
    private JButton a9Button;
    private JButton molButton;
    private JButton a8Button;
    private JButton a7Button;
    private JPanel CalcView;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Калькулятор");
        frame.setContentPane(new calc().CalcView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
             }
    double   num ,ans;
    int calculation;

    public void arifmeticalOperation () {
        switch(calculation) {
            case 1:   // Сложение
                ans = num + Double.parseDouble(textPane2.getText());
                textPane1.setText(Double.toString(ans));
                break;
            case 2:   // Вычитание
                ans = num - Double.parseDouble(textPane2.getText());
                textPane1.setText(Double.toString(ans));
                break;
            case 3:   // Умножение
                ans = num * Double.parseDouble(textPane2.getText());
                textPane1.setText(Double.toString(ans));
                break;
            case 4:   //Деление
                ans = num / Double.parseDouble(textPane2.getText());
                textPane1.setText(Double.toString(ans));
                break;
        }
    }
    public calc() {
pmButton.setText("\u21A9");
pButton.setText("\u002b");
mButton.setText("\u2212");
molButton.setText("\u2715");
divButton.setText("\u00f7");
roButton.setText("\u003d");





        a1Button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textPane2.setText(textPane2.getText()+"1");
            }
        });
        a2Button.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                textPane2.setText(textPane2.getText()+"2");
            }
        });
        a3Button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textPane2.setText(textPane2.getText()+"3");
            }
        });
        a4Button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textPane2.setText(textPane2.getText()+"4");
            }
        });
        a5Button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textPane2.setText(textPane2.getText()+"5");
            }
        });
        a6Button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textPane2.setText(textPane2.getText()+"6");
            }
        });
        a7Button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textPane2.setText(textPane2.getText()+"7");
            }
        });
        a8Button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textPane2.setText(textPane2.getText()+"8");
            }
        });
        a9Button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textPane2.setText(textPane2.getText()+"9");
            }
        });
        a0Button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textPane2.setText(textPane2.getText()+"0");
            }
        });
        pmButton.addActionListener(new ActionListener() {
                      public void actionPerformed(ActionEvent e) {
                //back button
                int length = textPane2.getText().length();
                int number = textPane2.getText().length()-1;
                        String store;

                if (length > 0){
                    StringBuilder back = new StringBuilder(textPane2.getText());
                    back.deleteCharAt(number);
                    store = back.toString();
                    textPane2.setText(store);
                }
            }
        });
        molButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });
       //Кнопка сложения
        pButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                num = Double.parseDouble(textPane2.getText());
                calculation = 1;
                textPane1.setText(num+"+");
                textPane2.setText("");



            }
        });
        roButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                arifmeticalOperation();
            }
        });//кнопска вычетания
        mButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num = Double.parseDouble(textPane2.getText());
                calculation = 2;
                textPane1.setText(num+"-");
                textPane2.setText("");
            }
        });
        //кнопка деления
        divButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num = Double.parseDouble(textPane2.getText());
                calculation = 4;
                textPane1.setText(num+"÷");
                textPane2.setText(" ");
            }
        });
        //кнопка умножения
        molButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num = Double.parseDouble(textPane2.getText());
                calculation = 3;
                textPane1.setText(num +"✕");
                textPane2.setText("");
            }
        });
    }
}

