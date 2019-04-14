package cn.tedu.jdbc.pool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class MyPool implements DataSource{
	private static List<Connection> pool = new LinkedList<Connection>();
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			for(int i=0;i<5;i++){
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1","root","root");
				pool.add(conn);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//获取连接
	@Override
	public Connection getConnection() throws SQLException {
		if(pool.size()==0){
			for(int i=0;i<3;i++){
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1","root","root");
				pool.add(conn);
			}
		}
		Connection conn1 = pool.remove(0);
		System.out.println("取出一个连接，池中还剩："+pool.size()+"个连接");
		return conn1;
	}
	//归还连接
	public void returnConnection(Connection conn){
		try {
			if(conn !=null&&!conn.isClosed()){
				pool.add(conn);
				System.out.println("归还一个连接，池中还剩："+pool.size()+"个连接");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
