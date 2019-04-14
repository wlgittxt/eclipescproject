package cn.tedu.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


import cn.tedu.jdbc.utils.JDBCUtils;

/**
 * ��½����ʵ��
 * @author Administrator
 *
 */
public class Login {
	public static void main(String[] args) {
		System.out.println("�������û�����");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		System.out.println("���������룺");
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
				System.out.println("��¼�ɹ�");
			}else{
				System.out.println("��¼ʧ��");
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
			//ִ�в�ѯ�Ӳ������ж��û�������û����������ܷ������ݿ��б���������
			//�����������֤��������û����û��������붼��ȷ��
			//������Ϊ�û��������벻����
			rs = stat.executeQuery("select * from user where name='"+name+"' and password='"+password+"'");
			if(rs.next()){
				System.out.println("��½�ɹ�");
			}else{
				System.out.println("��½ʧ��");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			JDBCUtils.close(conn, stat, rs);
		}
		
	}

}
