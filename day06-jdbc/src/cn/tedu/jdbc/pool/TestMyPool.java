package cn.tedu.jdbc.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TestMyPool {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		//创建一个手写连接池对象，使用这个连接池对象创建连接
		MyPool pool = new MyPool();
		try {
			conn = pool.getConnection();
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
					//归还连接
					//pool对象中包含一个归还连接的方法
					pool.returnConnection(conn);
				}finally{
					conn = null;
				}
			}
		}
	}

}
