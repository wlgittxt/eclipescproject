package cn.tedu.jdbc.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Demo1 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		//����Ҫ���������ļ���c3p0���Զ����������ļ�����������ļ����������srcĿ¼�£�����Ϊc3p0-config.xml
		ComboPooledDataSource source = new ComboPooledDataSource();
		/*������Ȼ����ͨ���ڴ������������ӳز����ķ�ʽ��������������
		 source.setDriverClass("com.mysql.jdbc.Driver");         
		 source.setJdbcUrl("jdbc:mysql://localhost:3306/mydb1");
		 source.setUser("root");                                  
		 source.setPassword("root"); 
		 */
		try {
			//�������ӳض��󴴽�һ�����ӡ�
			conn = source.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from emp");
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				System.out.println("id:"+id+">>>name:"+name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				conn = null;
			}
	
			}
		}
		
	}

}
