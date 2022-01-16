import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class Clcl {
    private JPanel panel1;
    private JButton a9Button;
    private JButton a4Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton aPlusButton;
    private JButton a6Button;
    private JButton a7Button;
    private JButton aMinusButton;
    private JButton aDotButton;
    private JButton a1Button;
    private JButton a5Button;
    private JButton aDivideButton;
    private JButton a8Button;
    private JButton aEqualButton;
    private JButton aMultButton;
    private JButton a0Button;
    private JLabel labelForShow;
    private JFormattedTextField textField;
    private JButton сlearButton;
    private JButton powerButton;
    private JButton varButton;
    private JButton brkButton;
    private JButton rootButton;

    boolean digitKey;
    boolean leftRightKey;
    boolean backSpKey;
    boolean markKey;
    boolean varKey;
    boolean dotKey;

    boolean brcktKey;
   // boolean brcktTurn = false;
    char targetChar = ' ';
    String []exection = {"+-","-+", "*+","+*", "*/","/*","/+","+/","-/",
                         "-*","√-","√*","√/","√+","√.",
                         ".-",".+","./",".*",".√","*.","/.","+.","-.",
                         "--","++","^^","..","//","**",
                         "*^","^*","-^","/^","^/","+^","^+",".^","^.","√^",
                         "(+","(.","(*","(^","(/","(√","-)","+)",".)",").","*)","^)","/)","√)",".("};

    char []marks = {'+','-','*','/','^','√'};
  //  String []extraMarks = {"^","√"};
    int execSize = exection.length;
    int extraExecSize = 0;
    boolean firstChar = false;
    String[] extraExec;


    double x = 0;
    double tmpDigit;
    double usingX = 0;
    String cue;
    boolean newAc = false;
    String tmpStr;
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    boolean notSolve = false;

    String removeCh(String s, int x){
        String left = s.substring(0, x);
        String right = s.substring(x+1, s.length());

        return s = left + right;
    }

//    void formExec (){
//        for (int i = 0; i < marks.length; i++){
//            for (int j = 0; j < marks.length; j++){
//                exection.
//            }
//
//            }
//    }



    public Clcl() {

        ActionListener listenerBtn = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + e.getActionCommand());
                if(newAc) {
                    labelForShow.setText("");
                    newAc = false;
                }
            }
        };
        a1Button.addActionListener(listenerBtn);
        a2Button.addActionListener(listenerBtn);
        a3Button.addActionListener(listenerBtn);
        a5Button.addActionListener(listenerBtn);
        a4Button.addActionListener(listenerBtn);
        a6Button.addActionListener(listenerBtn);
        a9Button.addActionListener(listenerBtn);
        a7Button.addActionListener(listenerBtn);
        a8Button.addActionListener(listenerBtn);
        a0Button.addActionListener(listenerBtn);
        aDotButton.addActionListener(listenerBtn);
        powerButton.addActionListener(listenerBtn);
        rootButton.addActionListener(listenerBtn);
        brkButton.addActionListener(listenerBtn);
        varButton.addActionListener(listenerBtn);
        aPlusButton.addActionListener(listenerBtn);
        aMinusButton.addActionListener(listenerBtn);
        aMultButton.addActionListener(listenerBtn);
        aDivideButton.addActionListener(listenerBtn);



//        ActionListener listenerAc = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                tmpDigit = Double.parseDouble(textField.getText());
//                labelForShow.setText(labelForShow.getText() + textField.getText() + e.getActionCommand());
//                textField.setText("");
//                if (usingX == 0) {
//                    x = tmpDigit;
//                    usingX ++;
//                }
//                else
//                    action(cue);
//                cue = e.getActionCommand();
//            }
//        };
//        aPlusButton.addActionListener(listenerAc);
//        aMinusButton.addActionListener(listenerAc);
//        aMultButton.addActionListener(listenerAc);
//        aDivideButton.addActionListener(listenerAc);

        aEqualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                tmpDigit = Double.parseDouble(textField.getText());
//
//
//                if (x - Math.round(x) == 0)
//                    tmpStr = String.valueOf(Math.round(x));
//                else
//                    tmpStr = String.valueOf(decimalFormat.format(x));
//                labelForShow.setText(labelForShow.getText() + textField.getText() + "=" + tmpStr);
//
//                textField.setText("");
//                x = 0;
//                newAc = true;
//                usingX = 0;
                String s = textField.getText();
                s = solve(s);
                if(!notSolve)
                    labelForShow.setText(s);
                notSolve = false;
            }
        });

        сlearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });

      //  ActionListener listenerBtn = new ActionListener() {
        KeyListener h = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String s = textField.getText();
                String tmp;
                int pos;
                if (!(leftRightKey || backSpKey)) {
                    tmp = checkForCorrect(s);
                    if(!tmp.equals(s)) {
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
               // varKey = e.getKeyChar() == targetChar;

//                brcktKey = false;
//                if( e.getKeyChar() == '(' && !brcktTurn){
//                    brcktTurn = true;
//                    brcktKey = true;
//                }
//                else if( e.getKeyChar() == ')' && brcktTurn){
//                    brcktTurn = false;
//                    brcktKey = true;
//                }
                brcktKey =  e.getKeyChar() == ')' ||  e.getKeyChar() == '(';

                    if (!( digitKey || leftRightKey || backSpKey || ((markKey || dotKey) && textField.getText().length() != 0)  || varKey || brcktKey))
                     e.setKeyChar((char) 0);
                //textField.setEnabled(false);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
               // textField.setEnabled(true);
                leftRightKey = e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT;
                backSpKey =  e.getKeyCode() == KeyEvent.VK_BACK_SPACE;
            }
        };
        textField.addKeyListener(h);
        panel1.addKeyListener(h);


        varButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(firstChar)
                firstCh('y');
            }
        });



        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = textField.getText();
                s = solve(s);
                if(!notSolve)
                    labelForShow.setText(s);
                notSolve = false;
            }
        });
    }

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
                labelForShow.setText("Недопустимая комбинация символов:" + exection[i]);
                notSolve = true;
            }
        }

        for(int i = 0; i < extraExecSize; i++) {
            posSpace = s.indexOf(extraExec[i]);
            if (posSpace != -1) {
                labelForShow.setText("Недопустимая комбинация символов:" + extraExec[i]);
                notSolve = true;
            }
        }

        key = true;
        while (key) {

            posSpace = s.indexOf(32);
            if(posSpace != -1) {
                labelForShow.setText("Присутствует пробел!!");
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
            labelForShow.setText("Неверное количество скобок");
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
                    labelForShow.setText("Пустые скобки!!");
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
                labelForShow.setText("Деление на ноль!!");
                return s;
            }
            tmpSolve = action(target, operandL, operandR);

            tmpString = String.valueOf(tmpSolve);
            if(tmpString.indexOf("inf") != -1){
                notSolve = true;
                labelForShow.setText("INNNFFFF...");
                return s;
            }
            else if (tmpString.indexOf("E") != -1){
                notSolve = true;
                labelForShow.setText("Числа ушли в Е... \n мне лень их обрабатывать");
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
                labelForShow.setText("Отрицательный корень!!!");
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
                labelForShow.setText("INNNFFFF...");
                return s;
            }
            else if (tmpString.indexOf("E") != -1){
                notSolve = true;
                labelForShow.setText("Числа ушли в Е... \n мне лень их обрабатывать");
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
    public void firstCh (char x) {
        targetChar = x;
        String c = String.valueOf(targetChar);
        varButton.setText(c);
        extraExec = new String[]{c+".",c+c, c+"1",c+"2",c+"3",c+"4",c+"5",c+"6",c+"7",c+"8",c+"9",c+"0", c+"^", "^"+c};
        firstChar = false;
        extraExecSize = extraExec.length;

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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clcl");
        frame.setContentPane(new Clcl().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
