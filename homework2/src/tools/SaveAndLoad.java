package tools;

import java.io.*;
import java.util.Map;

/*
 * 录入答案的保存与载入
 */
public class SaveAndLoad {
    public static boolean save(Map<String, String[]> e) {
        try {
            FileOutputStream fileOut = new FileOutputStream("ans.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(AnsProcess.modifyBeforeSave(e));
            out.close();
            fileOut.close();
            return true;
        } catch (IOException i) {
            return false;
        }
    }

    public static Map<String, Object> load() {
        try {
            FileInputStream fileIn = new FileInputStream("ans.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Map<String, Object> e = (Map<String, Object>) in.readObject();
            in.close();
            fileIn.close();
            return e;
        } catch (Exception e) {
            return null;
        }
    }
}
