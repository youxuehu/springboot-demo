package org.example.beans.dao;
import org.example.beans.utils.TransactionUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    private TransactionUtil transactionUtil;

    public UserDAOImpl() {
        System.out.println("<<<<<<<<<< UserDAOImpl create ~~ >>>>>>>>>>> ");
    }

    public void init() {
        System.out.println("<<<<<<<<<< UserDAOImpl init ~~ >>>>>>>>>>> ");
    }

    public void destroy() {
        System.out.println("<<<<<<<<<< UserDAOImpl destroy ~~ >>>>>>>>>>> ");
    }

    @Override
    public void update() {
        /**
         * 事务控制，抛异常后回滚事务
         */
        transactionUtil.begin();
        try {

            jdbcTemplate.batchUpdate("create table if not exists my_tab(id bigint primary key auto_increment, test1 varchar(255));");

        /*
                "insert into my_tab(test1) values('test-name');select * from my_tab;
         */
            jdbcTemplate.batchUpdate("insert into my_tab(test1) values('test-name');");
//            int i = 1 /0;
            jdbcTemplate.batchUpdate("insert into my_tab(test1) values('test-name');");
            jdbcTemplate.batchUpdate("insert into my_tab(test1) values('test-name');");
            transactionUtil.commit();

        } catch (Exception e) {
            transactionUtil.rollback();
            throw new RuntimeException(e.toString());
        }
    }

    @Override
    public List<MyTab> queryAll() {
        List<MyTab> myTabList = jdbcTemplate.query("select * from my_tab;", new RowMapper<MyTab>() {
            @Override
            public MyTab mapRow(ResultSet resultSet, int i) throws SQLException {
                long id = resultSet.getLong("id");
                String test1 = resultSet.getString("test1");
                MyTab myTab = new MyTab();
                myTab.setId(id);
                myTab.setTest1(test1);
                return myTab;
            }
        });
        return myTabList;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setTransactionUtil(TransactionUtil transactionUtil) {
        this.transactionUtil = transactionUtil;
    }
}
