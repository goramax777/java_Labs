import java.util.ArrayList;

public class Matrix <E extends Number> extends ArrayList {
    int row, col;
    int actualNumberEl;
    boolean fullKey = true;
    int typeOfData;
    static boolean errKey = false;
    ArrayList <E> El = new ArrayList<E>();
   // ArrayList <E> Mt = new ArrayList<E>();
    static String err = "";

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        actualNumberEl = col * row;
    }

    public void addEl (E x) {
        if (!fullKey) {
            El.add(x);
            actualNumberEl++;
            if (actualNumberEl == row * col) {
                fullKey = true;
            }
        }
    }

    public void changeEl (int index, E x){
        if(index < actualNumberEl){
            El.set(index, x);
        }
    }


}
