import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class Katalog {

    static TreeMap<String, String> tree = new TreeMap<String, String>();
      static String mainPath = "C:\\Users\\Максим\\Downloads\\kali";
      static String subPath = "C:\\Users\\Максим\\Downloads\\";

    static File dir1 = new File(mainPath);
    private static String[] s = dir1.list();



    public static void main(String[] args) {
        tree.put("0", "kali");
            for (int i = 0; i < s.length; i++){
                String pathCode = "0." + String.valueOf(i);
                tree.put(pathCode, s[i]);
                File tmp = new File(mainPath + "\\" + s[i]);
                if (tmp.isFile())
                    continue;
                String[] tmpS = tmp.list();

                int j = 0;

                while (true){

                    int indx = tmp.getName().lastIndexOf('\\');
                    String nm = tmp.getName().substring(indx+1, tmp.getName().length());
                    if (nm.equals(tree.get("0")))
                        break;

                    if (j >= tmpS.length){
                        String name = nm;
                        tmp = tmp.getParentFile();
                        tmpS = tmp.list();
                        pathCode = pathCode.substring(0, pathCode.lastIndexOf('.'));
                        int k = 0;
                        while (true){
                            if (tmpS[k].equals(name)){
                                j = k + 1;
                                break;
                            }
                            k++;
                        }
                        continue;

                    }

                        if(tmpS[j].indexOf(".") == -1){
                            pathCode += "."+ String.valueOf(j);
                            tree.put(pathCode, tmpS[j]);
                            String lastPathDigit = tree.lastKey();
                            String localPath = subPath;
                            int start = 0;
                            int idx = 0;
                            while (true) {
                                idx = lastPathDigit.indexOf(".", start);
                                if (idx == -1) {
                                    localPath += tree.get(lastPathDigit);
                                    break;
                                }
                                start = idx + 1;
                                localPath += tree.get(lastPathDigit.substring(0, idx)) + "\\";
                            }
                            tmp = new File(localPath);
                            tmpS = tmp.list();
                            j = 0;
                            continue;
                        }
                        else {
                            tree.put(pathCode +"."+ String.valueOf(j), tmpS[j]);

                        }


                        j++;

                }


            }
            for (Map.Entry<String, String> item : tree.entrySet()){
                int count = 0;
                for (char el : item.getKey().toCharArray())
                    if (el == '.')
                        count++;
                String str = "";
                for (int k = 0; k < count; k++)
                    str += "\t";
                str += item.getValue() + "\n";
                System.out.print(str);
            }
        }
}


