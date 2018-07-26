package code.Exception;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;

public class Exception extends Throwable {
    public Exception(String s) {
    }

    public static void main(String[] args) throws DataAccessException, Exception, SQLException {
        boolean val = true;
        if(val){
            throw new SQLException("something is wrong");
        }

    }
}
