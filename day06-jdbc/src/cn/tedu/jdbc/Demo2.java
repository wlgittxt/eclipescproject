package cn.tedu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//JDBC6步开发
public class Demo2 {
	public static void main(String[] args){
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try{
			//1.注册数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.获取数据库连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1", "root", "root");
			//3.获取传输器
			stat = conn.createStatement();
			//4.利用传输器传输sql。并返回结果
			rs = stat.executeQuery("select * from emp");
			//5.遍历结果
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString("name");
				System.out.println("id:"+id+">>>name:"+name);
			}
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			//6.关闭资源
			//先创建的后关闭
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					rs = null;
				}
			}
			if(stat != null){
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					stat = null;
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					conn = null;
				}
			}
		}
	}
}
