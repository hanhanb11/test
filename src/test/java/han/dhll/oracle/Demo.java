package han.dhll.oracle;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import org.junit.Test;

import java.sql.*;

public class Demo {

    @Test
    public void test() throws Exception {
        //连接驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //建立连接
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.80.128:1521:orcl","han","han");
        //得到预编译对象
        PreparedStatement pstm = connection.prepareStatement("select * from emp where empno = ?");
        //给参数赋值
        pstm.setObject(1,7788);
        //执行sql
        ResultSet rs = pstm.executeQuery();
        //输出结果
        while(rs.next()) {
            System.out.println(rs.getString("ename"));
            //释放资源
            rs.close();
            pstm.close();
            connection.close();
        }

    }

    /**
     * 测试存储过程
     *   {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}
     *    {call <procedure-name>[(<arg1>,<arg2>, ...)]}
     * @throws Exception
     *
     */

    @Test
    public void testProdecure() throws Exception {
        //连接驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //建立连接
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.80.128:1521:orcl","han","han");
        //得到预编译对象
        CallableStatement pstm = connection.prepareCall(" {call pp_yearsal(?,?)");
        //给参数赋值
        pstm.setObject(1,7788);
        pstm.registerOutParameter(2, OracleTypes.NUMBER);

        //执行sql
       pstm.execute();
        //输出结果
        System.out.println(pstm.getObject(2));
            //释放资源
            pstm.close();
            connection.close();
        }

    /**
     * 测试存储过程
     *   {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}
     *    {call <procedure-name>[(<arg1>,<arg2>, ...)]}
     * @throws Exception
     *
     */

    @Test
    public void testFunction() throws Exception {
        //连接驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //建立连接
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.80.128:1521:orcl","han","han");
        //得到预编译对象
        CallableStatement pstm = connection.prepareCall("  {?= call f_yearsal(?)}");
        //给参数赋值
        pstm.registerOutParameter(1, OracleTypes.NUMBER);
        pstm.setObject(2,7788);
        //执行sql
        pstm.execute();
        //输出结果
        System.out.println(pstm.getObject(1));
        //释放资源
        pstm.close();
        connection.close();
    }




    @Test
    public void test3(){

        System.out.println("hai");
    }


}

