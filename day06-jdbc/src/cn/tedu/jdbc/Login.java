package cn.tedu.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


import cn.tedu.jdbc.utils.JDBCUtils;

/**
 * 登陆功能实现
 * @author Administrator
 *
 */
public class Login {
	public static void main(String[] args) {
		System.out.println("请输入用户名：");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		System.out.println("请输入密码：");
		String password = sc.nextLine();
//		logintest(name,password);
		PreLogin(name,password);
	}

	private static void PreLogin(String name, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("select * from user where name = ? and password = ?");
			ps.setString(1, name);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				System.out.println("登录成功");
			}else{
				System.out.println("登录失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, ps, rs);
		}
	}

	private static void logintest(String name, String password) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stat = conn.createStatement();
			//执行查询从操作，判断用户输入的用户名和密码能否在数据库中被检索到，
			//如果检索到则证明有这个用户且用户名和密码都正确。
			//否则认为用户名和密码不存在
			rs = stat.executeQuery("select * from user where name='"+name+"' and password='"+password+"'");
			if(rs.next()){
				System.out.println("登陆成功");
			}else{
				System.out.println("登陆失败");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			JDBCUtils.close(conn, stat, rs);
		}
		
	}

}
