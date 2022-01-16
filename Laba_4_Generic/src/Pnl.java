import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Pnl {

    public Pnl() {

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n, m;
                String s = "";

                n = (int)rowSpinner.getValue();
                m = (int)colSpinner.getValue();
                for (int i = 0; i < n*m; i++){
                    s += "0 ";
                    if ((i+1) % m == 0 && i != 0)
                        s += "\n";
                }
                table.setText(s);
            }
        });

        CREEEEATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = table.getText();
                String tmp = "";

                int lastIndex = 0;
                int count = 0;
                int n, m;

                while(lastIndex != -1){

                    lastIndex = s.indexOf("\n",lastIndex);

                    if(lastIndex != -1){
                        count ++;
                        lastIndex ++;
                    }
                }

                n = count;

                count = 0;
                lastIndex = 0;
                while(lastIndex != -1){

                    lastIndex = s.indexOf(" ",lastIndex);

                    if(lastIndex != -1){
                        count ++;
                        lastIndex ++;
                    }
                    if(lastIndex == s.indexOf('\n'))
                        break;
                }

                m = count;

                int firstIndex = 0;
                lastIndex = 0;
                int type = s.indexOf(".");
                int curntEl = mainList.size();
                if (type != -1) {
                    mainList.add(new Matrix<Double>(n, m));
                    mainList.get(curntEl).typeOfData = 0;
                }
                else {
                    mainList.add(new Matrix<Integer>(n, m));
                    mainList.get(curntEl).typeOfData = 1;
                }


                while(lastIndex != -1) {
                        lastIndex = s.indexOf(" ", lastIndex);
                        if (type != -1)
                            mainList.get(curntEl).El.add(Double.parseDouble(s.substring(firstIndex, lastIndex)));
                        else
                            mainList.get(curntEl).El.add(Integer.parseInt(s.substring(firstIndex, lastIndex)));
                        lastIndex++;
                        if (s.charAt(lastIndex) == '\n')
                            lastIndex++;

                        firstIndex = lastIndex;
                        lastIndex = s.indexOf(" ", lastIndex);
                        if (lastIndex == -1) {
                            break;
                        }
                }
//                    for (int i = 0; i < mainList.get(curntEl).El.size(); i++)
//                        tmp += String.valueOf(mainList.get(curntEl).El.get(i)) + " ";
//                    errors.setText(tmp);

                showMtElBox.addItem("Matrix " + String.valueOf(curntEl+1));
                changeMtElBox.addItem("Matrix " + String.valueOf(curntEl+1));
                firstMtBox.addItem("Matrix " + String.valueOf(curntEl+1));
                secondMtBox.addItem("Matrix " + String.valueOf(curntEl+1));




            }
        });


        showMtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int curntEl = showMtElBox.getSelectedIndex();
                String tmp = "";
                for (int i = 0; i < mainList.get(curntEl).El.size(); i++) {
                    tmp += String.valueOf(mainList.get(curntEl).El.get(i)) + " ";
                    if ((i+1) % mainList.get(curntEl).col == 0 && i != 0)
                        tmp += "\n";
                }
                    errors.setText(tmp);
            }
        });

        changeMtElBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                int curntEl = cb.getSelectedIndex();
                changingElBox.removeAllItems();
                for (int i = 0; i < mainList.get(curntEl).row; i++) {
                    for (int j = 0; j < mainList.get(curntEl).col; j++) {
                        changingElBox.addItem(String.valueOf(i) + "." + String.valueOf(j));

                    }
                }

                }
        });


        changeElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(chaingingElTextField.getText() != ""){
                    int curntEl = changeMtElBox.getSelectedIndex();
                    int left = 0, right = 0, dotPos = 0;
                    String tmp = (String) changingElBox.getSelectedItem();
                    dotPos = tmp.indexOf(".");
                    left = Integer.parseInt(tmp.substring(0, dotPos));
                    right = Integer.parseInt(tmp.substring(dotPos + 1, tmp.length()));
                    if (mainList.get(curntEl).typeOfData == 0) {
                        mainList.get(curntEl).El.set(left * mainList.get(curntEl).row + right, Double.parseDouble(chaingingElTextField.getText()));
                    }
                    else {
                        mainList.get(curntEl).El.set(left * mainList.get(curntEl).row + right,Integer.parseInt(chaingingElTextField.getText()));
                    }

                }
            }
        });

        performButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int curntFirst = firstMtBox.getSelectedIndex();
                int curntSecond = secondMtBox.getSelectedIndex();
                int typeFunc;
                if (mainList.get(curntFirst).typeOfData == 1 && mainList.get(curntSecond).typeOfData == 1) {
                    typeFunc = 1;
                } else
                    typeFunc = 0;
                int acType = actionTypeBox.getSelectedIndex();
                Matrix Mt;

                switch (acType){
                    case 0:
                        Mt = Cntrlr.adding(mainList.get(curntFirst), mainList.get(curntSecond),typeFunc);
                        break;
                    case 1:
                        Mt = Cntrlr.subtraction(mainList.get(curntFirst), mainList.get(curntSecond),typeFunc);
                        break;
                    case 2:
                        Mt = Cntrlr.multiplication(mainList.get(curntFirst), mainList.get(curntSecond),typeFunc);
                        break;
                        default:
                            Mt = Cntrlr.adding(mainList.get(curntFirst), mainList.get(curntSecond),typeFunc);
                            break;
                }

                mainList.add(Mt);
                int curntEl = mainList.size();
                showMtElBox.addItem("Matrix " + String.valueOf(curntEl));
                changeMtElBox.addItem("Matrix " + String.valueOf(curntEl));
                firstMtBox.addItem("Matrix " + String.valueOf(curntEl));
                secondMtBox.addItem("Matrix " + String.valueOf(curntEl));

            }
        });
    }

    public JPanel getMainPnl() {
        return mainPnl;
    }

    ArrayList<Matrix> mainList = new ArrayList<Matrix>();
    private JPanel mainPnl;
    private JSpinner rowSpinner;
    private JSpinner colSpinner;
    private JButton createButton;
    private JComboBox actionTypeBox;
    private JButton showMtButton;
    private JButton changeElementButton;
    private JButton performButton;
    private JComboBox showMtElBox;
    private JComboBox changeMtElBox;
    private JComboBox changingElBox;
    private JComboBox firstMtBox;
    private JComboBox secondMtBox;
    private JTextArea errors;
    private JTextField chaingingElTextField;
    private JTextArea table;
    private JButton CREEEEATEButton;

}
