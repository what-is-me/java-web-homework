package per.whatisme.mapper;

import per.whatisme.beans.Customer;
import per.whatisme.tools.Sql;
import per.whatisme.tools.Transfer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerMapper {
    public List<Customer> select() {
        List<HashMap<String, String>> retsrc;
        try {
            retsrc = Sql.select("test.customer", new HashMap<>());
        } catch (Exception e) {
            return null;
        }
        var ret = new ArrayList<Customer>();
        for (var mp : retsrc) {
            ret.add(Transfer.toObj(Transfer.toJson(mp), Customer.class));
        }
        return ret;
    }

    public List<Customer> select(String id) {
        List<HashMap<String, String>> retsrc;
        try {
            HashMap<String, String> ask = new HashMap<>();
            ask.put("id", id);
            retsrc = Sql.select("test.customer", ask);
        } catch (Exception e) {
            return null;
        }
        var ret = new ArrayList<Customer>();
        for (var mp : retsrc) {
            ret.add(Transfer.toObj(Transfer.toJson(mp), Customer.class));
        }
        return ret;
    }

    public void delete(String id) throws SQLException {
        String sql = "delete FROM test.customer WHERE id = ?";
        Sql.execute(sql, id);
    }

    public void add(Customer customer) throws SQLException {
        String sql = "insert into test.customer (id, name, money, email) VALUES (?,?,?,?);";
        Sql.execute(sql, customer.getId(), customer.getName(), customer.getMoney(), customer.getEmail());
    }
}
