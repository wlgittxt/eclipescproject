package cn.tedu.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.tedu.jdbc.utils.JDBCUtils;


public class PreparedDemo1 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			//prepareStatement不需要创建statment的过程，直接书写sql
			//sql语句和参数是分别发送到数据库的
			ps = conn.prepareStatement("select * from user where name = ? and password = ?");
			//发送参数，所发送的参数是一段纯文本的内容
			ps.setString(1, "ls");
			ps.setString(2, "123");
			//写完sql语句主干和参数后，需要执行一句executeQuery executeUpdate，
			//代表sql语句和参数已经书写完毕，发送到数据库服务器执行
			rs = ps.executeQuery();
			if(rs.next()){
				String name = rs.getString("name");
				String password = rs.getString("password");
				System.out.println("name:"+name+">>>password:"+password);
			}else{
				System.out.println("没有查询");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//ps可以直接写在参数的位置进行关闭操作，是因为prepareStatement是Statement的子接口
			JDBCUtils.close(conn, ps, rs);
		}
	}

}
