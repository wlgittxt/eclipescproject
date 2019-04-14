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
			//prepareStatement����Ҫ����statment�Ĺ��̣�ֱ����дsql
			//sql���Ͳ����Ƿֱ��͵����ݿ��
			ps = conn.prepareStatement("select * from user where name = ? and password = ?");
			//���Ͳ����������͵Ĳ�����һ�δ��ı�������
			ps.setString(1, "ls");
			ps.setString(2, "123");
			//д��sql������ɺͲ�������Ҫִ��һ��executeQuery executeUpdate��
			//����sql���Ͳ����Ѿ���д��ϣ����͵����ݿ������ִ��
			rs = ps.executeQuery();
			if(rs.next()){
				String name = rs.getString("name");
				String password = rs.getString("password");
				System.out.println("name:"+name+">>>password:"+password);
			}else{
				System.out.println("û�в�ѯ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//ps����ֱ��д�ڲ�����λ�ý��йرղ���������ΪprepareStatement��Statement���ӽӿ�
			JDBCUtils.close(conn, ps, rs);
		}
	}

}
