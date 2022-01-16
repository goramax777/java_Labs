
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class mainPanel extends JPanel {

    JTextField textField = new JTextField(15);
    JButton submit = new JButton("build");
    JLabel lblforRangeX = new JLabel( ("rangeX:"));
    SpinnerNumberModel model1 = new SpinnerNumberModel(10, 10, 1000, 10);
    SpinnerNumberModel model2 = new SpinnerNumberModel(10, 10, 1000, 10);

    JSpinner spinforRangeX = new JSpinner(model1);
    JSpinner spinforRangeY = new JSpinner(model2);
    JLabel lblforRangeY = new JLabel( ("rangeY:"));



    boolean digitKey;
    boolean leftRightKey;
    boolean backSpKey;
    boolean markKey;
    boolean varKey;
    boolean dotKey;
    boolean firstChar = true;
    boolean brcktKey;
    boolean singleLine = false;
    int targetRange = 0;
    char targetChar = ' ';
    int extraExecSize = 0;
    String[] extraExec;
    ArrayList X = new ArrayList();
    ArrayList Y = new ArrayList();
    //    ArrayList<Integer> X = new ArrayList<Integer>();
//    ArrayList<Integer> Y = new ArrayList<Integer>();
    String []exection = {"+-","-+", "*+","+*", "*/","/*","/+","+/","-/",
            "-*","√-","√*","√/","√+","√.",
            ".-",".+","./",".*",".√","*.","/.","+.","-.",
            "--","++","^^","..","//","**",
            "*^","^*","-^","/^","^/","+^","^+",".^","^.","√^",
            "(+","(.","(*","(^","(/","(√","-)","+)",".)",").","*)","^)","/)","√)",".("};

    char []marks = {'+','-','*','/','^','√'};
    int execSize = exection.length;

    boolean notSolve = false;

    String removeCh(String s, int x){
        String left = s.substring(0, x);
        String right = s.substring(x+1, s.length());

        return s = left + right;
    }

    int unitX;
    int unitY;
    int rangeX = 10;
    int rangeY = 10;
    int indent = 3;
    int globYInd = 30;
    int hghtForUnit;
    int hghtForAsix;
    int stepX = 1;
    int stepY = 1;

    String[] petStrings = { "X=", "Y="};

    JComboBox petList = new JComboBox(petStrings);


    public mainPanel() {
        setLayout(null);
        unitX = getWidth() / 20;
        unitY = (getHeight() - globYInd) / 20;

        petList.setBounds(0, 0, 40, 20);
        textField.setBounds(45, 0, 100, 20);
        submit.setBounds(150, 0, 70, 20);
        lblforRangeX.setBounds(225, 0, 60, 20);
        spinforRangeX.setBounds(275, 0, 70, 20);
        lblforRangeY.setBounds(355, 0, 60, 20);
        spinforRangeY.setBounds(405, 0, 70, 20);

        add(petList);
        add(textField);
        add(submit);

        add(lblforRangeX);
        add(spinforRangeX);

        add(lblforRangeY);
        add(spinforRangeY);
        spinforRangeX.setEnabled(false);


        spinforRangeX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                String range = spinforRangeX.getValue().toString();
                rangeX = Integer.parseInt(range);
                if (rangeX % 10 <= 5)
                    rangeX = rangeX / 10 * 10;
                else
                    rangeX = rangeX / 10 * 10 + 10;
                stepX = rangeX*2 / 20;
                X.clear();
                Y.clear();
                updateUI();

            }
        });

        spinforRangeY.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                String range = spinforRangeY.getValue().toString();
                rangeY = Integer.parseInt(range);
                if (rangeY % 10 <= 5)
                    rangeY = rangeY / 10 * 10;
                else
                    rangeY = rangeY / 10 * 10 + 10;

                stepY = rangeY*2 / 20;
                X.clear();
                Y.clear();
                updateUI();
            }
        });

        petList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (petList.getSelectedIndex() == 0) {
                    spinforRangeX.setEnabled(false);
                    spinforRangeY.setEnabled(true);

                    targetRange = 0;
                } else {
                    spinforRangeY.setEnabled(false);
                    spinforRangeX.setEnabled(true);
                    targetRange = 1;
                }
                X.clear();
                Y.clear();
                rangeX = 10;
                rangeY = 10;
                updateUI();
                firstChar = true;

            }
        });


        KeyListener h = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String s = textField.getText();
                String tmp;
                int pos;
                if (!(leftRightKey || backSpKey)) {
                    tmp = checkForCorrect(s);
                    if (!tmp.equals(s)) {
                        pos = textField.getCaretPosition() - 1;
                        textField.setText(tmp);
                        textField.setCaretPosition(pos);
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                digitKey = e.getKeyChar() >= '0' && e.getKeyChar() <= '9';
                markKey = e.getKeyChar() == '+' || e.getKeyChar() == '-' || e.getKeyChar() == '*' || e.getKeyChar() == '/' || e.getKeyChar() == '^';
                dotKey = e.getKeyChar() == '.';

                if(firstChar && e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z'){
                    firstCh(e.getKeyChar());
                }
                varKey = e.getKeyChar() == targetChar;

                brcktKey = e.getKeyChar() == ')' || e.getKeyChar() == '(';

                if (!(digitKey || leftRightKey || backSpKey || ((markKey || dotKey) && textField.getText().length() != 0) || varKey || brcktKey))
                    e.setKeyChar((char) 0);

            }

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            leftRightKey = e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT;
            backSpKey =  e.getKeyCode() == KeyEvent.VK_BACK_SPACE;
        }
    };
        textField.addKeyListener(h);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = textField.getText();
                s = addingMltplcMark(s);
                String tmp;
                singleLine = false;
                X.clear();
                Y.clear();
                if(s.indexOf(targetChar) == -1)
                {
                    tmp = solve(s);
                    int t = (int) Double.parseDouble(tmp);
                    if(targetRange == 0){
                        Y.add(-rangeY);
                        Y.add(rangeY);
                        X.add(t);
                        X.add(t);
                    }
                    else{
                        X.add(-rangeX);
                        X.add(rangeX);
                        Y.add(t);
                        Y.add(t);
                    }
                    singleLine = true;

                }
                else {
                    if (targetRange == 1 ) {
                        if (rangeX < 100) {
                            for (int i = rangeX * (-1); i <= rangeX; i = i + stepX) {
                                tmp = s;
                                while (tmp.indexOf(targetChar) != -1) {
                                    int x = tmp.indexOf(targetChar);
                                    tmp = removeCh(tmp, x);
                                    tmp = insertStr(tmp, x, String.valueOf(i));
                                }
                                tmp = solve(tmp);
                                X.add(i);
                                Y.add((int) (Double.parseDouble(tmp)));
                            }
                        } else {
                            for (int i = rangeX * (-1); i <= rangeX; i = i + stepX / 4) {
                                tmp = s;
                                while (tmp.indexOf(targetChar) != -1) {
                                    int x = tmp.indexOf(targetChar);
                                    tmp = removeCh(tmp, x);
                                    tmp = insertStr(tmp, x, String.valueOf(i));
                                }
                                tmp = solve(tmp);
                                X.add(i);
                                Y.add((int) (Double.parseDouble(tmp)));
                            }
                        }
                    } else {
                        if (rangeY < 100) {
                            for (int i = rangeY * (-1); i <= rangeY; i = i + stepY) {
                                tmp = s;
                                while (tmp.indexOf(targetChar) != -1) {
                                    int x = tmp.indexOf(targetChar);
                                    tmp = removeCh(tmp, x);
                                    tmp = insertStr(tmp, x, String.valueOf(i));
                                }
                                tmp = solve(tmp);
                                Y.add(i);
                                X.add((int) (Double.parseDouble(tmp)));
                            }
                        } else {
                            for (int i = rangeY * (-1); i <= rangeY; i = i + stepY / 4) {
                                tmp = s;
                                while (tmp.indexOf(targetChar) != -1) {
                                    int x = tmp.indexOf(targetChar);
                                    tmp = removeCh(tmp, x);
                                    tmp = insertStr(tmp, x, String.valueOf(i));
                                }
                                tmp = solve(tmp);
                                Y.add(i);
                                X.add((int) (Double.parseDouble(tmp)));
                            }
                        }
                    }
                }
                updateUI();

                notSolve = false;
            }
        });
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = textField.getText();
                s = solve(s);
                if(!notSolve)
                    textField.setText(s);
                notSolve = false;
            }
        });
    }




    public static void main(String[] args)
    {
        JFrame frame = new JFrame("CalcSt");
        frame.add(new mainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
       // frame.setResizable(false);
        frame.setVisible(true);
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0,0, getWidth(), getHeight());
        Graphics2D drp  = (Graphics2D)g;
        hghtForUnit = getHeight() - globYInd;
        hghtForAsix = (getHeight() + globYInd - indent);

        drp.drawLine(getWidth()/2,30, getWidth()/2,getHeight());
        drp.drawLine(0,hghtForAsix / 2,getWidth(),hghtForAsix / 2);

        unitY = hghtForUnit / 20;
        unitX = getWidth() / 20;

        if(X.size() != 0){
            int max;
            int min;
            int scope;
            if(targetRange == 1){
                max = (int)Collections.max(Y);
                min = (int)Collections.min(Y);
            }
            else {
                max = (int)Collections.max(X);
                min = (int)Collections.min(X);
            }
            if(max < Math.abs(min))
                scope = Math.abs(min);
            else
                scope = max;

//            if (rangeY % 10 <= 5)
//                scope = scope / 10 * 10;
//            else
                scope = (scope / 10 + 1) * 10;

            if (targetRange == 1)
                rangeY = scope;
            else
                rangeX = scope;
        }

        stepX = rangeX*2 / 20;
        stepY = rangeY*2 / 20;


        for (int i = 0; i < 21; i++)
        {

            if(i<10)
                drp.drawString( String.valueOf(stepX*i - rangeX), unitX * i + indent ,hghtForAsix / 2 + 15);
            else if (i==10)
                drp.drawString( String.valueOf(0), unitX * i + indent ,hghtForAsix / 2 + 15);
            else
                drp.drawString( String.valueOf(Math.abs(stepX*i - rangeX)), unitX * i + indent ,hghtForAsix / 2 + 15);


            if (i == 10)
                continue;
            drp.drawString( String.valueOf(rangeY-stepY*i), getWidth()/2 + 7 ,unitY * i + 15 + globYInd);
            drp.drawLine(unitX * i + indent,hghtForAsix / 2 - 5, unitX * i + indent,hghtForAsix / 2 + 5);
            drp.drawLine(getWidth()/2 - 5, unitY * i + indent + globYInd,getWidth()/2 + 5, unitY * i + indent + globYInd);

        }



        if (X.size() != 0){

//            int []Xp = new int[X.size()];
//            int []Yp = new int[X.size()];

            if (targetRange == 1 && singleLine){
                X.set(0,-rangeX);
                X.set(1,rangeX);
            }
            else if(targetRange == 0 && singleLine){
                Y.set(0, -rangeY);
                Y.set(1, rangeY);
            }
            double x, y, x1, y1;
            for(int i = 0; i < X.size() -1; i++){
                x = (((int) X.get(i)) + rangeX)/(double)stepX * unitX + indent ;
                y = (((int) Y.get(i) + rangeY)/(double)stepY * unitY + globYInd + indent) * -1 + getHeight() + globYInd - indent*2;
                x1 = (((int) X.get(i+1)) + rangeX)/(double)stepX * unitX + indent ;
                y1 = (((int) Y.get(i+1) + rangeY)/(double)stepY * unitY + globYInd + indent) * -1 + getHeight()+globYInd - indent*2;
                drp.drawLine((int)x, (int)y, (int)x1, (int)y1);
//                Xp[i] = (((int) X.get(i)) + rangeX)/stepX * unitX + indent ;
//                Yp[i] = (((int) Y.get(i) + rangeY) * unitY + globYInd + indent) * -1 + getWidth();

            }
           // drp.drawPolygon(Xp, Yp, Xp.length);
        }
//        if(singleLine){
//            drp.drawLine((int)X.get(0), (int)Y.get(0), (int)X.get(1), (int)Y.get(1));
//
//        }




    }


























/////////////////////////////////////////// жалкий код с калькулятора...
    public String solve(String s)
    {
        if(!checkCountBrckt(s)){
            return s;
        }
        preWatching(s);
        if(!notSolve) {
            s = addingMltplcMark(s);
            s = openingBrkt(s);
            s = act(s);
        }

        return s;
    }

    public void preWatching(String s){
        boolean key;
        int posSpace;

        for(int i = 0; i < execSize; i++){
            posSpace = s.indexOf(exection[i]);
            if(posSpace != -1) {
                textField.setText("Недопустимая комбинация символов:" + exection[i]);
                notSolve = true;
            }
        }

        for(int i = 0; i < extraExecSize; i++) {
            posSpace = s.indexOf(extraExec[i]);
            if (posSpace != -1) {
                textField.setText("Недопустимая комбинация символов:" + extraExec[i]);
                notSolve = true;
            }
        }

        key = true;
        while (key) {

            posSpace = s.indexOf(32);
            if(posSpace != -1) {
                textField.setText("Присутствует пробел!!");
                notSolve = true;
                break;
            } else
                key = false;

        }
    }

    public boolean checkCountBrckt (String s){
        int leftBrk = 0;
        int rightBrk = 0;
        for(int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i) == '('){
                leftBrk++;
            }
            if(s.charAt(i) == ')'){
                rightBrk++;
            }
        }
        if (leftBrk != rightBrk) {
            textField.setText("Неверное количество скобок");
            notSolve = true;
            return false;
        }
        return true;
    }

    public String addingMltplcMark(String s){
        int size = s.length();
        boolean keyForAdding = true;

        for(int i = 0; i < size; i++){
            if(s.charAt(i) == '(' && i != 0){
                for(int j = 0; j < marks.length; j++) {
                    if(s.charAt(i-1) == marks[j] || s.charAt(i-1) == '('){
                        keyForAdding = false;
                        break;
                    }
                }
                if(keyForAdding){
                    s = insertStr(s, i, "*");
                    i++;
                }
            }
            keyForAdding = true;
            if(s.charAt(i) == ')' && i < s.length() - 1){
                for(int j = 0; j < marks.length -1; j++) {
                    if(s.charAt(i+1) == marks[j] || s.charAt(i+1) == ')'){
                        keyForAdding = false;
                        break;
                    }
                }
                if(keyForAdding){
                    s = insertStr(s, i+1, "*");
                    i++;
                }
            }
            keyForAdding = true;

            if(s.charAt(i) == '√' && i != 0){
                for(int j = 0; j < marks.length; j++) {
                    if(s.charAt(i-1) == marks[j]){
                        keyForAdding = false;
                        break;
                    }
                }
                if(keyForAdding){
                    s = insertStr(s, i, "*");
                    i++;
                }
            }

            keyForAdding = true;
            if(s.charAt(i) < '9' && s.charAt(i) > '0' ){
                if(i+1 < s.length() && s.charAt(i+1) == targetChar){
                    s = insertStr(s, i+1, "*");
                    i++;
                }
            }

        }

        return s;
    }
    public  String openingBrkt(String s){
        int posOBrkt;
        int posCBrkt = 0;
        String tmp = "";
        while (!notSolve){
            posOBrkt = s.lastIndexOf('(');
            if(posOBrkt == -1)
                break;
            else {
                posCBrkt = s.indexOf(')', posOBrkt);
                tmp =s.substring(posOBrkt+1, posCBrkt);
                if(tmp.equals("")) {
                    textField.setText("Пустые скобки!!");
                    notSolve = true;
                    return  s;
                }
                tmp = act(tmp);
                if(posOBrkt != 0 && s.charAt(posOBrkt - 1) == '-' && Double.parseDouble(tmp) < 0)
                    s = insertStr(s,posOBrkt-1, posCBrkt+1, tmp);
                else
                    s = insertStr(s,posOBrkt, posCBrkt+1, tmp);

            }
        }

        return s;

    }

    public String act(String s) {

        if(notSolve)
            return s;
        s = sqrtS(s);
        s = powS(s);
        s = arfmtS (s, '*', '/');
        s = arfmtS (s, '+', '-');

        return s;
    }

    public String arfmtS(String s, char c1, char c2){
        if(notSolve)
            return s;
        int pos = -1;
        int pos1 = s.indexOf(c1, 1);
        int pos2 = s.indexOf(c2, 1);
        char target;
        if(pos1 < pos2 && pos1 != -1 || pos2 == -1) {
            pos = pos1;
            target = c1;
        } else {
            pos = pos2;
            target = c2;
        }

        boolean leftKey = true;
        boolean rightKey = true;
        int indexerL;
        int indexerR;
        double operandL;
        double operandR;
        double tmpSolve;
        String tmpL;
        String tmpR;


        String tmpString;
        int posDot;
        if (pos != -1) {
            indexerL = pos - 1;
            indexerR = pos + 1;
            while (leftKey || rightKey) {
                if (leftKey && indexerL > 0) {
                    for (int j = 0; j < marks.length; j++) {
                        if (s.charAt(indexerL-1) == marks[j]) {
                            if(s.charAt(indexerL-1) == '-')
                                indexerL--;
                            leftKey = false;
                            break;
                        }
                    }
                    if(leftKey)
                        indexerL--;
                }
                else
                    leftKey = false;

                if (rightKey && indexerR < s.length() - 1) {
                    for (int j = 0; j < marks.length; j++) {
                        if (s.charAt(indexerR+1) == marks[j]) {
                            rightKey = false;
                            break;
                        }
                    }
                    if(rightKey)
                        indexerR++;
                }
                else
                    rightKey = false;
            }

            tmpL = s.substring(indexerL, pos);
            tmpR = s.substring(pos+1, indexerR+1);
            operandR = Double.parseDouble(tmpR);
            operandL = Double.parseDouble(tmpL);
            if(operandR == 0 && target == '/'){
                notSolve = true;
                textField.setText("Деление на ноль!!");
                return s;
            }
            tmpSolve = action(target, operandL, operandR);

            tmpString = String.valueOf(tmpSolve);
            if(tmpString.indexOf("inf") != -1){
                notSolve = true;
                textField.setText("INNNFFFF...");
                return s;
            }
            else if (tmpString.indexOf("E") != -1){
                notSolve = true;
                textField.setText("Числа ушли в Е... \n мне лень их обрабатывать");
                return s;
            }
            posDot = tmpString.indexOf('.');
            if(tmpString.length() - posDot > 6)
                tmpString = tmpString.substring(0, posDot + 6);

            if (operandL < 0 && tmpSolve >= 0)
                s = insertStr(s, indexerL, indexerR+1, "+" +  tmpString);
            else if(operandL >= 0 && tmpSolve < 0 && indexerL > 0)
                s = insertStr(s, indexerL - 1, indexerR+1, tmpString);
            else
                s = insertStr(s, indexerL, indexerR+1, tmpString);


        }


        pos1 = s.indexOf(c1, 1);
        pos2 = s.indexOf(c2, 1);
        if(pos1 != -1 || pos2 != -1)
            s = arfmtS(s, c1, c2);
        else
            return s;


        return s;

    }
    public  String sqrtS(String s){
        if(notSolve)
            return s;
        int pos;
        boolean rightKey = true;
        int indexerR;
        double operandR;
        double tmpSolve;
        String tmpR;


        String tmpString;
        int posDot;
        pos = s.lastIndexOf('√');
        if (pos != -1) {
            indexerR = pos + 1;
            while (rightKey) {
                if (rightKey && indexerR < s.length() - 1) {
                    for (int j = 0; j < marks.length; j++) {
                        if (s.charAt(indexerR+1) == marks[j]) {
                            rightKey = false;
                            break;
                        }
                    }
                    if(rightKey)
                        indexerR++;
                }
                else
                    rightKey = false;
            }

            tmpR = s.substring(pos+1, indexerR+1);
            operandR = Double.parseDouble(tmpR);
            if (operandR < 0) {
                textField.setText("Отрицательный корень!!!");
                notSolve = true;
                return s;
            }
            tmpSolve = Math.sqrt(operandR);

            tmpString = String.valueOf(tmpSolve);
            posDot = tmpString.indexOf('.');
            if(tmpString.length() - posDot > 6)
                tmpString = tmpString.substring(0, posDot + 6);

            s = insertStr(s, pos, indexerR+1, tmpString);


        }

        pos = s.lastIndexOf('√');
        if(pos == -1)
            return s;
        else
            s = sqrtS(s);
        return s;
    }
    public String powS (String s){
        if(notSolve)
            return s;
        int pos;
        boolean leftKey = true;
        boolean rightKey = true;
        int indexerL;
        int indexerR;
        double operandL;
        double operandR;
        double tmpSolve;
        String tmpL;
        String tmpR;


        String tmpString;

        int posDot;
        pos = s.lastIndexOf('^');
        if (pos != -1) {
            indexerL = pos - 1;
            indexerR = pos + 1;
            while (leftKey || rightKey) {
                if (leftKey && indexerL > 0) {
                    for (int j = 0; j < marks.length; j++) {
                        if (s.charAt(indexerL-1) == marks[j]) {
                            if(s.charAt(indexerL-1) == '-')
                                indexerL--;
                            leftKey = false;
                            break;
                        }
                    }
                    if(leftKey)
                        indexerL--;
                }
                else
                    leftKey = false;

                if (rightKey && indexerR < s.length() - 1) {
                    for (int j = 0; j < marks.length; j++) {
                        if (s.charAt(indexerR+1) == marks[j]) {
                            rightKey = false;
                            break;
                        }
                    }
                    if(rightKey)
                        indexerR++;
                }
                else
                    rightKey = false;
            }

            tmpL = s.substring(indexerL, pos);
            tmpR = s.substring(pos+1, indexerR+1);
            operandR = Double.parseDouble(tmpR);
            operandL = Double.parseDouble(tmpL);
            tmpSolve = Math.pow(operandL, operandR);

            tmpString = String.valueOf(tmpSolve);
            if(tmpString.indexOf("Inf") != -1){
                notSolve = true;
                textField.setText("INNNFFFF...");
                return s;
            }
            else if (tmpString.indexOf("E") != -1){
                notSolve = true;
                textField.setText("Числа ушли в Е... \n мне лень их обрабатывать");
                return s;
            }

            posDot = tmpString.indexOf('.');
            if(tmpString.length() - posDot > 6)
                tmpString = tmpString.substring(0, posDot + 6);

            if (operandL < 0 && tmpSolve >= 0)
                s = insertStr(s, indexerL, indexerR+1, "+" +  tmpString);
            else if(operandL >= 0 && tmpSolve < 0 && indexerL > 0)
                s = insertStr(s, indexerL - 1, indexerR+1, tmpString);
            else
                s = insertStr(s, indexerL, indexerR+1, tmpString);


        }

        pos = s.lastIndexOf('^');
        if(pos == -1)
            return s;
        else
            s = powS(s);


        return s;
    }

    public String insertStr (String s, int index, String it){
        String leftSTR;
        String rightSTR;

        leftSTR = s.substring(0, index);
        rightSTR = s.substring(index, s.length());
        s = leftSTR + it + rightSTR;
        return s;
    }

    public String insertStr (String s, int firstI, int lastI, String it){
        String leftSTR;
        String rightSTR;

        leftSTR = s.substring(0, firstI);
        rightSTR = s.substring(lastI, s.length());
        s = leftSTR + it + rightSTR;
        return s;
    }


    public String checkForCorrect (String s) {
        boolean key;
        int posSpace;

        for(int i = 0; i < execSize; i++){
            posSpace = s.indexOf(exection[i]);

            if(posSpace != -1) {
                s = removeCh(s,posSpace+1);
                i = 0;
            }
        }

        for(int i = 0; i < extraExecSize; i++) {
            posSpace = s.indexOf(extraExec[i]);

            if (posSpace != -1) {
                s = removeCh(s, posSpace + 1);
            }
        }

        key = true;
        while (key) {

            posSpace = s.indexOf(32);
            if(posSpace != -1)
                s = removeCh(s,posSpace);
            else
                key = false;

        }
        return s;
    }

    public double action (char s, double x, double y)
    {
        switch (s) {
            case '+':
                return x+y;
            case '-':
                return x-y;
            case '/':
                return x/y;
            case '*':
                return x*y;
        }
        return 0;
    }

    public void firstCh (char x) {
        targetChar = x;
        String c = String.valueOf(targetChar);
        extraExec = new String[]{c+".",c+c, c+"1",c+"2",c+"3",c+"4",c+"5",c+"6",c+"7",c+"8",c+"9",c+"0"};
        firstChar = false;
        extraExecSize = extraExec.length;

    }
}
