package web;

import tools.Student;
import tools.HTML;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * 学生成绩表
 */
@WebServlet("/grade_table")
public class GradeTable extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        var out = resp.getWriter();
        List<Object[]> table = new ArrayList<>();
        Student.students.forEach(stu -> table.add(stu.toArray()));
        out.println(HTML.html("成绩总表", HTML.div("<h1 style='border-bottom: #89898966 solid 1px;'>成绩总表</h1>" +
                HTML.toTable(new Object[] { "学号", "姓名", "成绩" }, table, ""), "box", "",
                "margin-top:10%;text-align:center;padding:20%;width:90%;background-color:rgba(20, 21, 22, 0.8);color:white;")));
    }
}
