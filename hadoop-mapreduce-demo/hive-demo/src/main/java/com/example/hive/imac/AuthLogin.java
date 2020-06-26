package com.example.hive.imac;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hive.service.auth.PasswdAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.sasl.AuthenticationException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author youxuehu
 */
public class AuthLogin implements PasswdAuthenticationProvider, Configurable {
    private Configuration conf = null;
    private static Logger LOG = LoggerFactory.getLogger(AuthLogin.class);
    private String url;
    private String user;
    private String pwd;
    private final static String driver = "com.mysql.jdbc.Driver";
    private Connection conn;
    private String query = "SELECT * FROM user";

    public AuthLogin() {
        //从hive-site.xml 获取dw 的数据库信息
        LOG.info("AuthLogin init");
        url = getConf().get("hive.dw.mysql.url");
        user = getConf().get("hive.dw.mysql.user");
        pwd = getConf().get("hive.dw.mysql.pwd");
        LOG.info(String.format("hive dw url:%s, user:%s, pwd:%s", url, user, pwd));
    }

    @Override
    public Configuration getConf() {
        if (this.conf == null) {
            HiveConf conf = new HiveConf();
            this.conf = new Configuration(conf);
        }
        return this.conf;
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(driver);
            if (conn == null) {
                conn = DriverManager.getConnection(url, user, pwd);
                conn.setAutoCommit(false);
            }
            return conn;
        } catch (Exception e) {
            LOG.error("get dw mysql connection failed", e);
            throw new SQLException("Connect error!");
        }
    }

    private Map<String, String> queryUser() {
        Map<String, String> userPass = new HashMap<String, String>(16);
        try {
            getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");
                userPass.put(name, password);
            }
            st.close();
        } catch (Exception e) {
            //如果出现异常，设置为null，下次重新获取连接
            LOG.error("get dw mysql user failed", e);
            conn = null;
        }
        return userPass;
    }

    @Override
    public void setConf(Configuration arg0) {
        this.conf = arg0;
    }

    @Override
    public void Authenticate(String username, String password) throws AuthenticationException {
        if (username == null || password == null) {
            throw new AuthenticationException("error.");
        }
        LOG.info("user: " + username + " try login.");
        Map<String, String> userPass = queryUser();
        if (!userPass.containsKey(username)) {
            String message = "user name not exist:";
            throw new AuthenticationException(message);
        } else {
            if (!password.equals(userPass.get(username))) {
                String message = "user name and password is mismatch. user:" + username;
                throw new AuthenticationException(message);
            }
        }
        LOG.info("user " + username + " login system successfully.");
    }
}