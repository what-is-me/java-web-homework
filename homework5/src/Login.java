import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginS...")
public class Login extends HttpServlet {
    private final static String template = """
            <html lang="en">
                        
            <head>
                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>
                    body {
                        font-family: 'JetBrains Mono', "Hiragino Sans GB", Simsun, "Microsoft Yahei", Arial, sans-serif;
                        margin: 0;
                        background-image: url(https://ak.hypergryph.com/assets/index/images/ak/pc/bk.jpg);
                        background-attachment: fixed;
                        font-size: 18px;
                    }
                        
                    h1,
                    h2 {
                        font-family: SimHei;
                    }
                        
                    div.header {
                        text-align: center;
                        margin: 0 auto;
                        width: 100%;
                        position: absolute;
                        top: 0%;
                        left: 0%;
                        height: 7%;
                        margin-top: 5%;
                        margin-bottom: 3%;
                    }
                        
                    img.logo {
                        height: 60px;
                        width: 60px;
                        padding-bottom: -10px;
                        vertical-align: -15px;
                    }
                        
                    @font-face {
                        font-family: 'JetBrains Mono';
                        src: local('JetBrains Mono'), url('https://what-is-me.github.io/font/JetBrainsMono-Regular.woff2') format('woff2');
                    }
                        
                    @font-face {
                        font-family: 'Pfeffer Mediæval';
                        src: local('Pfeffer Mediæval'), url('https://what-is-me.github.io/font/PfefferMediaeval.otf') format('otf');
                    }
                        
                        
                    div.box {
                        display: block;
                        border-radius: 10px;
                        border: 1px #ddd solid;
                        background-color: #fff;
                        padding: 20px 26px !important;
                        margin: 0 auto;
                    }
                        
                    input[type=text],
                    input[type=number],
                    input[type=tel],
                    input[type=password],
                    input[type=date],
                    input[type=email],
                    input[type=search],
                    textarea,
                    select {
                        background-color: #fff;
                        height: 24px;
                        padding: 3px 7px;
                        line-height: normal;
                        border: 1px solid #a6a6a6;
                        border-top-color: #949494;
                        border-radius: 3px;
                        box-shadow: 0 1px 0 rgb(255 255 255 / 50%), 0 1px 0 rgb(0 0 0 / 7%) inset;
                        margin: 0 auto;
                        font-size: 16px;
                        vertical-align: middle;
                        width: 100%;
                    }
                        
                    label {
                        display: block;
                        padding-left: 2px;
                        padding-bottom: 2px;
                        font-weight: 700;
                        font-family: SimHei, "黑体";
                    }
                        
                    input[type=radio],
                    input[type=checkbox] {
                        display: inline-block;
                        height: 16px;
                        width: 16px;
                        border: 1px solid #000;
                        overflow: hidden;
                        vertical-align: -13px;
                        text-align: center;
                        -webkit-appearance: none;
                        font: normal normal normal 14px/1 FontAwesome;
                        outline: 0;
                    }
                        
                    input[type=radio]:checked:after,
                    input[type=checkbox]:checked:after {
                        content: '\\221A';
                        font-size: 15px;
                        text-align: center;
                        line-height: 17px;
                        color: #000;
                    }
                        
                    .submit-button {
                        text-align: center;
                        width: 100%;
                        height: 30px;
                        background-color: #66ccff;
                        color: #000;
                        border-width: 0;
                        border-radius: 5px;
                    }
                        
                    input[type=text],
                    input[type=number],
                    input[type=tel],
                    input[type=password],
                    input[type=date],
                    input[type=email],
                    input[type=search],
                    select,
                    textarea,
                    .submit-button,
                    input[type=radio],
                    input[type=checkbox],
                    label {
                        box-sizing: border-box;
                        margin-top: 10px;
                        margin-bottom: 10px;
                    }
                        
                    time {
                        font-family: BenderLight;
                    }
                        
                    code,
                    pre {
                        font-family: "JetBrains Mono";
                    }
                        
                    code {
                        margin: 0 !important;
                        background-color: rgba(255, 255, 255, 0.1);
                        padding: 0 3px !important;
                    }
                        
                    pre {
                        margin: 6px 0;
                        background-color: transparent;
                    }
                        
                    table {
                        font-size: 20px;
                        border: 0;
                        border-collapse: collapse;
                    }
                        
                    table th {
                        border: #2bf solid 1px;
                        color: #222222;
                        background-color: #cfcfcf;
                        padding: 0 10px;
                    }
                        
                    table td {
                        padding: 0px 8px;
                        border: #2bf solid 1px;
                        color: #c4c4c4;
                    }
                        
                    table td:hover {
                        background-color: rgba(34, 187, 255, 0.5);
                    }
                        
                    html,
                    body {
                        height: 100%;
                        cursor: url(https://what-is-me.github.io/cursor-inner.png) 3 3, default;
                        position: static;
                    }
                </style>
                <title>登录</title>
            </head>
                        
            <body>
            <div class="box"
                 style="position:absolute;width: 320px;left:50%;top:50%;transform: translate(-50%,-50%);">
                <h1 style="text-align:center">登入</h1>
                <form method="post" action="LoginS">
                    <label>
                        姓名:
                        <input type="text" name="name" value="{s}" style="width:70%;height:30px">
                    </label>
                    <label>
                        密码:
                        <input type="password" name="password" value="{s}" style="width:70%;height:30px">
                    </label>
                    <label>
                        <input type="checkbox" name="check" checked="checked" value="checked"/>
                        自动登录
                    </label>
                    <input type="submit" class="submit-button">
                </form>
            </div>
            </body>
                        
            </html>
            """.replace("%", "%%").replace("{s}", "%s");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String name = "", password = "";
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                String id = c.getName();
                if ("name".equals(id)) {
                    name = c.getValue();
                } else if ("password".equals(id)) {
                    password = c.getValue();
                }
            }
        }
        resp.getWriter().printf(template, name, password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("欢迎"+req.getParameter("name")+"<br>");
        if(req.getParameter("check")!=null) {
            for (var id : new String[]{"name", "password"}) {
                Cookie cookie = new Cookie(id, req.getParameter(id));
                resp.addCookie(cookie);
            }
            resp.getWriter().println("登录信息已经保存");
        }else{
            for (var id : new String[]{"name", "password"}) {
                Cookie cookie = new Cookie(id, "");
                resp.addCookie(cookie);
            }
            resp.getWriter().println("登录信息已经清空");
        }
    }
}