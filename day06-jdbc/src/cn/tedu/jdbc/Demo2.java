package cn.tedu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//JDBC6������
public class Demo2 {
	public static void main(String[] args){
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try{
			//1.ע�����ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			//2.��ȡ���ݿ�����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1", "root", "root");
			//3.��ȡ������
			stat = conn.createStatement();
			//4.���ô���������sql�������ؽ��
			rs = stat.executeQuery("select * from emp");
			//5.�������
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString("name");
				System.out.println("id:"+id+">>>name:"+name);
			}
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			//6.�ر���Դ
			//�ȴ����ĺ�ر�
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
