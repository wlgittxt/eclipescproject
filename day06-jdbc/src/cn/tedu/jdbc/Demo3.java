package cn.tedu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import cn.tedu.jdbc.utils.JDBCUtils;

public class Demo3 {
	@Test
	public void add(){
		Connection conn = null;
		Statement stat =null;
		ResultSet rs = null;
		try {
			//1.注册数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.获取数据库连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1", "root", "root");
			//3.获取传输器
			stat = conn.createStatement();
			//4.利用传输器传输数据，并返回结果
			int count = stat.executeUpdate("insert into emp values(5,'李帅',4)");
			//5.判断是否执行成功。返回值大于0.
			if(count>0){
				System.out.println("添加成功，插入的行数为："+count+"行");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//6.关闭资源
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
	@Test
	public void update(){
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			/*Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1", "root", "root");*/
			conn = JDBCUtils.getConnection();
			stat = conn.createStatement();
			int count = stat.executeUpdate("update emp set name = '田七' where id = 5");
			if(count>0){
				System.out.println("更新成功，更新的行数为："+count+"行");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, stat, rs);
/*
			//6.关闭资源
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
			}*/
		}
		
	}
}
