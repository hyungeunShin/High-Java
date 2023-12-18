package basicJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class JdbcTest08 implements Runnable {
    private static final int MAX_RETRIES = 3;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new ttt4());
            thread.start();
        }
    }

    @Override
    public void run() {
        executeWithRetries();
    }

    public static void execute() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String userid = "tester2";
        String passwd = "1234";

        String sql = "INSERT INTO 고객(고객번호, 고객명) select max(to_number(고객번호)) + 1, '홍길동' from 고객";

        try (Connection con = DriverManager.getConnection(url, userid, passwd);
             PreparedStatement st = con.prepareStatement(sql)) {

            st.executeUpdate();

        } catch (SQLException e) {
        	if(e instanceof SQLIntegrityConstraintViolationException) {
        		throw e;
        	} else {
        		System.err.println("에러 : " + e.getMessage());
        	}
        }
    }

    public static void executeWithRetries() {
        int retryCount = 0;
        boolean success = false;

        while (!success && retryCount < MAX_RETRIES) {
            try {
                execute();
                success = true;
            } catch (SQLException e) {
            	if(e instanceof SQLIntegrityConstraintViolationException) {
            		retryCount++;
            	} else {
            		System.err.println("에러 : " + e.getMessage());
            	}
            }
        }
    }
}
