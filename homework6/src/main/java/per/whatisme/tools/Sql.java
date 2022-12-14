package per.whatisme.tools;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sql {
    public static Statement stmt;
    public static Connection conn;

    static {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String url = "jdbc:mysql://127.0.0.1:3306/stuqa?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
            String username = "root";
            String password = "root";
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void execute(String sql) throws SQLException {
        stmt.execute(sql);
    }

    public static List<HashMap<String, String>> select(String sql) throws SQLException {
        System.out.println(sql);
        ResultSet res = stmt.executeQuery(sql);
        List<HashMap<String, String>> ret = new ArrayList<>();
        var rsmd = res.getMetaData();
        int colCount = rsmd.getColumnCount();
        while (res.next()) {
            HashMap<String, String> row = new HashMap<>();
            for (int i = 1; i <= colCount; i++) {
                row.put(rsmd.getColumnName(i), res.getString(i));
            }
            ret.add(row);
        }
        return ret;
    }

    public static List<HashMap<String, String>> select(String tablename, Map<String, String> que) throws SQLException {
        StringBuilder sql = new StringBuilder(String.format("select * from %s where 1=1", tablename));
        que.forEach((k, v) -> {
            sql.append(String.format(" and concat(%s) like '%s'", k, v));
        });
        return select(sql.toString() + ";");
    }

    public static void execute(String sql, Object... params) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++)
            pstmt.setString(i + 1, String.valueOf(params[i].toString()));
        pstmt.execute();
    }
}
