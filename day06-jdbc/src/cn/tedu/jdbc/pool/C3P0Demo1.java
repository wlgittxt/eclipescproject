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
		//不需要加载配置文件，c3p0会自动加载配置文件。这个配置文件必须存在于src目录下，且名为c3p0-config.xml
		ComboPooledDataSource source = new ComboPooledDataSource();
		/*我们依然可以通过在代码中配置连接池参数的方式来配置连接属性
		 source.setDriverClass("com.mysql.jdbc.Driver");         
		 source.setJdbcUrl("jdbc:mysql://localhost:3306/mydb1");
		 source.setUser("root");                                  
		 source.setPassword("root"); 
		 */
		try {
			//利用连接池对象创建一个链接。
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
