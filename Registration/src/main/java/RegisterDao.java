import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {
	private String dbUrl = "jdbc:mysql://localhost:3306/userdb";
	private String dbUname = "root";
	private String dbPassword = "";
	private String dbDriver = "com.mysql.cj.jdbc.Driver";
	
	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		Connection connect = null;
		try {
			connect = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}
	
	public String insert(User user) {
		
		loadDriver(dbDriver);
		Connection connect = getConnection();
		String result = "Success";
		String sql = "insert into user values(?,?,?,?)";
		PreparedStatement pstatement;
		try {
			pstatement = connect.prepareStatement(sql);
			pstatement.setString(1, user.getUsername());
			pstatement.setString(2, user.getPassword());
			pstatement.setString(3, user.getEmail());
			pstatement.setString(4, user.getPhonenumber());
			pstatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "Error!";
		}
		
		
		
		return result;
	}
}
