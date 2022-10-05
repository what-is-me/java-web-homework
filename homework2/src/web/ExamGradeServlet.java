package web;

import tools.AnsProcess;
import tools.SaveAndLoad;
import tools.Student;
import tools.HTML;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

/*
 * 判题，出分
 */
@WebServlet("/check")
public class ExamGradeServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        var ans = request.getParameterMap();
        PrintWriter out = response.getWriter();
        var stu = new Student(ans.get("name")[0], ans.get("num")[0],
                4 * AnsProcess.correctNumbers(ans, SaveAndLoad.load()));
        Student.students.remove(stu);
        Student.students.add(stu);
        String body = "<h1>考试成绩</h1>学号:%s<br>姓名:%s<br>成绩:%d<br><a href=index.html>返回主页</a>".formatted(stu.num, stu.name,
                stu.grade);
        body = HTML.div(body, "box", "", "margin:20% 33% auto;padding:20%;width:30%;");
        out.println(HTML.html("成绩", body));
    }
}