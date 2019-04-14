package cn.tedu.jdbc.pool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class dbcpDemo1 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		/*BasicDataSource source = new BasicDataSource();
		source.setDriverClassName("com.mysql.jdbc.Driver");
		source.setUrl("jdbc:mysql://localhost:3306/mydb1");
		source.setUsername("root");
		source.setPassword("root");*/
		try {
			//第二种处理方式，读取配置文件
			Properties prop = new Properties();
			prop.load(new FileInputStream(new File(dbcpDemo1.class.getClassLoader().getResource("dbcp.properties").getPath())));
			BasicDataSourceFactory factory = new BasicDataSourceFactory();
			DataSource source = factory.createDataSource(prop);
			conn = source.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from emp");
			while(rs.next()){
				String name = rs.getString("name");
				System.out.println("name:"+name);
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
				//归还连接
				try {
					//虽然调用的是close方法，但是底层调用的是还连接操作
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
