package cn.tedu.jdbc.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.tedu.jdbc.utils.JDBCUtils;
/**
 * PreparedStatement������
 * �ŵ㣺
 * 	1.��Ԥ���빦��
 *  2.���Խ�sql���������������ݿ��У���sql�����ڴ���ʱ�򣬿��ٵ��á�
 *  3.ִ��Ч�ʽϸߡ�
 * ȱ�㣺
 * 	1.���ܴ��䲻ͬ�����sql
 * @author Administrator
 *
 */
public class PreparedBatchDemo {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("insert into student values(?,?)");
			for(int i=0;i<10001;i++){
				//���ò���1��2
				ps.setInt(1,i);
				ps.setString(2, "name"+i);
				//�������֮����ӵ�����
				ps.addBatch();
				if(i%100 == 0){//ÿ100������ִ��һ����
					ps.executeBatch();
					System.out.println("��ǰ��"+i/100+"��");
					//ִ����һ�������������ݿ������
					ps.clearBatch();
				}
				
			}
			//�˾䲻���٣������д�Ļ�����һЩ����ѭ��100�������޷�ִ��
			ps.executeBatch();
			System.out.println("PreparedStatement���������");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, ps, rs);
		}
	}

}
