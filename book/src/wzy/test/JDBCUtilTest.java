package wzy.test;

import org.junit.Test;
import wzy.utils.JDBCUtils;

import java.sql.Connection;

public class JDBCUtilTest {
    @Test
    public void testJdbcUtils() {
        for (int i = 0; i<100; i++){
            Connection conn = JDBCUtils.getConnection();
            System.out.println(conn);
            //JDBCUtils.Close(conn);
        }



    }
}
