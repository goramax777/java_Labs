import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calc {
    private JTextPane XtextPane1;
    private JTextPane textPane1;
    private JButton button1;
    private JTextPane textPane3;
    private JPanel CalcView;
    private JTextPane textPane2;
    private JTextPane textPane4;
    private JTextField textField1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Калькулятор квадратных уравнений");
        frame.setContentPane(new calc().CalcView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


    public void kvadratnoeUravnenie () {
        /*String str = textPane4.getText();
          if (str.indexOf(char x))==1;
        { double a = 1;
         String[] subStr = str.split("x|=");
        }
        else {
            String[] subStr = str.split("x|=");
         double a = Double.parseDouble(subStr[0]);
        }

        // второй аргумент

        StringBuffer sb = new StringBuffer(subStr[1]);
        sb.delete(0,2);
        String store = sb.toString();
        double b = Double.parseDouble(store);


        //третий аргумент
        double c = Double.parseDouble(subStr[2]);
*/
        //textPane3.setText(Double.toString(c));
double a=2;
double b=-14;
double c=9;

        double d = Math.pow(b, 2) - (4 * a * c);
        if (d>0){
            double x1 = ((-1) * b + Math.sqrt(d)) / 2 * a;
            double x2 = ((-1) * b - Math.sqrt(d)) / 2 * a;
           textPane1.setText("D>0");
           textPane2.setText("X1="+x1);
           textPane3.setText("X2="+x2);
        }
         else if (d == 0){
            double x1 = (-1) * b;
          textPane1.setText("D=0");
          textPane2.setText("X1=X2="+x1);
        }
        else
            textPane1.setText("Ошибка. D меньше нуля");
    }

        public calc(){
        button1.setText("Решить квадратное уравнение");
        textPane1.setText("D=");
        textPane2.setText("X1=");
        textPane3.setText("X2=");


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kvadratnoeUravnenie();
            }
        });
    }

}
