package pack01.repository.db;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class ConnectionManager {

    public static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/pocruit");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }
}