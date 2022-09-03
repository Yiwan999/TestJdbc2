package com.test;

import java.sql.*;
/*qw*/
public class TestJdbc4 {

    public static void main(String[] args) {

        try {
            //加载oracle驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //创建一个连接
            String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
            String uname="C##test";
            String pwd="test";
            Connection conn= DriverManager.getConnection(url,uname,pwd);
            //创建语句命令对象  传递sql
            //执行sql
            //sql注入
            String username="aaaa";
            String sql="select * from users where name=?";
            PreparedStatement statement= conn.prepareStatement(sql);
            statement.setObject(1,username);
            //返回结果集
            ResultSet resultSet= statement.executeQuery();
            //读取表头
            ResultSetMetaData resultSetMetaData= resultSet.getMetaData();
            for(int i=1;i<=resultSetMetaData.getColumnCount();i++)
                System.out.println(resultSetMetaData.getColumnName(i));
            //读取数据
            while(resultSet.next())
            {
                int id=resultSet.getInt("ID");
                String name=resultSet.getString(2);
                int age=resultSet.getInt(3);
                System.out.println(id+","+name+","+age);
            }
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
