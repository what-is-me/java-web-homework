import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String[] tags = {"usst", "nusst"};
        if (new Random().nextBoolean()) {
            var tmp = tags[0];
            tags[0] = tags[1];
            tags[1] = tmp;
        }
        StringBuilder radio = new StringBuilder(new String());
        for (var tag : tags) {
            radio.append(String.format("""
                            <label><input type="radio" name="is_usst" value="%s"><img src="picture?val=%s" style="height: 60px;width: 100px" alt="%s"></label>
                            """,
                    tag, tag, tag));
        }
        var out = resp.getWriter();
        out.println(HTML.html("login",
                HTML.div("""
                                <h1>Login</h1>
                                <form method="post" action="login">
                                  <label>
                                    用户名:
                                    <input type="text" name="username">
                                  </label>
                                  <label>
                                    密码:
                                    <input type="password" name="password">
                                  </label>
                                  <label>验证码（选择上海理工）</label>
                                """ +
                                radio +
                                """
                                  <input type="submit" class="submit-button">
                                </form>""",
                        "box", "login", "width:300px;position:absolute;top:50%;left:50%;transform:translate(-50%,-50%);")));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        var out = resp.getWriter();
        var msg = "";
        if ("usst".equals(req.getParameter("is_usst"))) {
            if ("admin".equals(req.getParameter("username")) &&
                    "admin".equals(req.getParameter("password")))
                msg = "登录成功";
            else msg = "用户名或密码错误";
        } else {
            msg = "验证码错误";
        }
        out.println(HTML.html("login",
                HTML.div(msg, "box", "login", "text-align:center;width:300px;position:absolute;top:50%;left:50%;transform:translate(-50%,-50%);")));
    }
}


/*工具包，主要用于快速生成html字符串 */
class HTML {
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