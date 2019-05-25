import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import org.springframework.jdbc.core.JdbcTemplate;

public class Database {

    public BasicDataSource connectionString() throws SQLException {
        
        SQLServerDriver driver = new SQLServerDriver();
        
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriver(driver);
        dataSource.setUrl("jdbc:sqlserver://safetybd.database.windows.net:1433;database=safety;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        dataSource.setUsername("bandtec");
        dataSource.setPassword("Projeto@safety");
        
        return dataSource;
    }
    
        public JdbcTemplate getConnection() throws SQLException{
        BasicDataSource dataSource = connectionString();
        JdbcTemplate connection  = new JdbcTemplate(dataSource);
        
        return connection;
    }
}
