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
			//1.ע�����ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			//2.��ȡ���ݿ�����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1", "root", "root");
			//3.��ȡ������
			stat = conn.createStatement();
			//4.���ô������������ݣ������ؽ��
			int count = stat.executeUpdate("insert into emp values(5,'��˧',4)");
			//5.�ж��Ƿ�ִ�гɹ�������ֵ����0.
			if(count>0){
				System.out.println("��ӳɹ������������Ϊ��"+count+"��");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//6.�ر���Դ
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
			int count = stat.executeUpdate("update emp set name = '����' where id = 5");
			if(count>0){
				System.out.println("���³ɹ������µ�����Ϊ��"+count+"��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, stat, rs);
/*
			//6.�ر���Դ
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
