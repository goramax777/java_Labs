import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cntrlr {

    public JPanel getMainPnl() {
        return mainPnl;
    }

    private JPanel mainPnl = new Pnl().getMainPnl();



    public static void main(String[] args) {
        JFrame frame = new JFrame("Pnl");
        frame.setContentPane(new Cntrlr().mainPnl);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);
    }

    static public Matrix adding (Matrix m1, Matrix m2, int type){

            if (m1.row == m2.row && m1.col == m2.col){
                if (type == 0){
                    Matrix <Double> C = new Matrix<Double>(m1.row, m1.col);
                    C.typeOfData = 0;
                    double p1, p2;
                    for (int i = 0; i < m1.actualNumberEl; i++){
                        p1 = (double)m1.El.get(i);
                        p2 = (double)m2.El.get(i);
                        C.El.add(p1 + p2);
                    }
                    return C;
                }
                else {
                    Matrix <Integer> C = new Matrix<Integer>(m1.row, m1.col);
                    C.typeOfData = 1;
                    int p1, p2;
                    for (int i = 0; i < m1.actualNumberEl; i++){
                        p1 = (int)m1.El.get(i);
                        p2 = (int)m2.El.get(i);
                        C.El.add(p1 + p2);
                    }
                    return C;
                }

            }
            else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"diferent sizes...");
            }

        return m1;
    }

    static public Matrix subtraction (Matrix m1, Matrix m2, int type){

            if (m1.row == m2.row && m1.col == m2.col){
                if (type == 0){
                    Matrix <Double> C = new Matrix<Double>(m1.row, m1.col);
                    C.typeOfData = 0;
                    double p1, p2;
                    for (int i = 0; i < m1.actualNumberEl; i++){
                        p1 = (double)m1.El.get(i);
                        p2 = (double)m2.El.get(i);
                        C.El.add(p1 - p2);
                    }
                    return C;
                }
                else {
                    Matrix <Integer> C = new Matrix<Integer>(m1.row, m1.col);
                    C.typeOfData = 1;
                    int p1, p2;
                    for (int i = 0; i < m1.actualNumberEl; i++){
                        p1 = (int)m1.El.get(i);
                        p2 = (int)m2.El.get(i);
                        C.El.add(p1 - p2);
                    }
                    return C;
                }

            }
            else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"diferent sizes...");
            }

        return m1;
    }




    static public Matrix multiplication (Matrix m1, Matrix m2, int type){

            if (m1.col == m2.row){
                if (type == 0){
                    Matrix <Double> C = new Matrix<Double>(m1.row, m2.col);
                    C.typeOfData = 0;
                    double p1, p2;
                    double sum = 0;
                    for (int i = 0; i < m1.row * m2.col; i++){
                        for (int j = 0; j < m1.col; j++){
                            p1 = (double)m1.El.get(j + (i % m1.row));
                            p2 = (double)m2.El.get(j * m2.col + (i % m2.col));
                            sum += p1 * p2;
                        }

                        C.El.add(sum);
                        sum = 0;
                    }
                    return C;
                }
                else {
                    Matrix <Integer> C = new Matrix<Integer>(m1.row, m1.col);
                    C.typeOfData = 1;
                    int p1, p2, k=0;
                    int sum = 0;
                    for (int i = 0; i < m1.row * m2.col; i++) {
                        for (int j = 0; j < m1.col; j++) {
                            p1 = (int) m1.El.get(j + k);
                            p2 = (int) m2.El.get(j * m2.col + (i % m2.col));
                            sum += p1 * p2;
                        }
                        if ((i+1) % m1.row == 0 && i != 0){
                            k += m1.row;
                        }

                        C.El.add(sum);
                        sum = 0;
                    }
                    return C;
                }

            }
            else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"diferent sizes...");
            }

        return m1;
    }


}
