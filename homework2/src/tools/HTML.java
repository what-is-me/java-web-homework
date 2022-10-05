package tools;

import java.util.List;

/*工具包，主要用于快速生成html字符串 */
public class HTML {
    private static String toRows(List<Object[]> lst) {
        StringBuilder ret = new StringBuilder();
        for (Object[] row : lst) {
            ret.append("<tr>");
            for (Object obj : row) {
                ret.append("<td>").append(obj.toString()).append("</td>");
            }
            ret.append("</tr>");
        }
        ret.append("</table>");
        return ret.toString();
    }

    static public String toTable(Object[] head, List<Object[]> lst, String class_) {
        StringBuilder ret = new StringBuilder("<table class='" + class_ + "' style='margin:auto;width:100%;'>");
        ret.append("<tr>");
        for (Object obj : head) {
            ret.append("<th>").append(obj.toString()).append("</th>");
        }
        ret.append("</tr>");
        return ret + toRows(lst);
    }

    static public String toTable(List<Object[]> lst, String class_) {
        String ret = "<table class='" + class_ + "'>";
        return ret + toRows(lst);
    }

    static public String html(String title, String body) {
        return String.format(
                "<html><head><link rel='stylesheet' type='text/css' href='css.css'><title>%s</title></head><body>%s</body></html>",
                title, body);
    }

    static public String div(String content, String class_, String id, String style) {
        return String.format("<div class='%s' id='%s' style='%s'>%s</div>", class_, id, style, content);
    }
}