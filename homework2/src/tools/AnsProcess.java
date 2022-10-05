package tools;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * 处理答案输入，包括录入的答案和学生的回答
 */
public class AnsProcess {
    static String modify(String s) {
        return s.replaceAll(" ", "")
                .replaceAll("\r", "")
                .replaceAll("\n", "")
                .replaceAll("\t", "");
    }

    /*
     * 两集合相等
     */
    public static boolean setEquals(Set<String> set1, Set<String> set2) {
        if (set1 == null || set2 == null)
            return false;
        if (set1.size() != set2.size())
            return false;
        return set1.containsAll(set2);
    }

    public static Map<String, Object> modifyBeforeSave(Map<String, String[]> mp) {
        Map<String, Object> ans = new HashMap<>();
        mp.forEach((key, val) -> {
            var kind = key.split("_")[0];
            switch (kind) {
                case "single-section", "judgement" -> ans.put(key, val[0]);
                case "multi-section" -> ans.put(key, new HashSet<>(Arrays.asList(val)));
                case "blank" -> ans.put(key, modify(val[0]));
                case "quiz" -> ans.put(key, modify(val[0]).split(";"));
            }
        });
        return ans;
    }

    /// 做对的题数
    public static int correctNumbers(Map<String, String[]> mp, Map<String, Object> ans) {
        AtomicInteger ret = new AtomicInteger();
        for (Map.Entry<String, String[]> entry : mp.entrySet()) {
            String key = entry.getKey();
            String[] val = entry.getValue();
            var kind = key.split("_")[0];
            switch (kind) {
                case "single-section", "judgement" -> {
                    if (ans.get(key).equals(val[0])) {
                        ret.addAndGet(1);
                        // System.out.println(key);
                    }
                }
                case "multi-section" -> {
                    if (setEquals(new HashSet<>(Arrays.asList(val)), (Set<String>) ans.get(key))) {
                        ret.addAndGet(1);
                        // System.out.println(key);
                    }
                }
                case "blank" -> {
                    if (ans.get(key).equals(modify(val[0]))) {
                        ret.addAndGet(1);
                        // System.out.println(key);
                    }
                }
                case "quiz" -> {
                    int flag = 1;
                    for (String keyword : (String[]) ans.get(key)) {
                        if (!val[0].contains(keyword)) {
                            flag = 0;
                            break;
                        }
                    }
                    if (flag == 1) {
                        // System.out.println(key);
                    }
                    ret.addAndGet(flag);
                }
            }
        }
        return ret.get();
    }
}
