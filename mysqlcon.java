package firstlook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*This code connects to the mysql database*/
public class mysqlcon {
	private Connection con = null;
	private Statement statement = null;
	private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private String username, password;
	private String dbname = "jdbc:mysql://83.212.109.125:3306/logs1";

	public mysqlcon(String userd, String passd) {
		this.password = passd;
		this.username = userd;
	}

	public Statement Connect() {
		try {
			Class.forName(JDBC_DRIVER).newInstance();
			;
			con = DriverManager.getConnection(dbname, username, password);
			statement = con.createStatement();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"not connected" + e.getMessage());
		}
		return statement;
	}

	public Connection connectup() {
		try {
			Class.forName(JDBC_DRIVER).newInstance();
			;
			con = DriverManager.getConnection(dbname, username, password);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"not connected" + e.getMessage());
		}
		return con;
	}

}