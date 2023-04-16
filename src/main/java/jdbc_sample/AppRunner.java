package jdbc_sample;

import java.sql.*;


public class AppRunner {
	public static void main(String[] args) throws SQLException {
		System.out.println("Hello ---------jdbc-------mysql------");
		String databaseUrl = "jdbc:mysql://localhost:3306/jdbc-test";
		String databaseUser = "root";
		String password = "Password123";
		String driverDb = "com.mysql.cj.jdbc.Driver";
		Connection connection = null;
		try {
			Class.forName(driverDb);
			 connection = DriverManager.getConnection(databaseUrl, databaseUser, password);
			 insert(connection);
			 
			print(connection);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connection.close();
		}
	}

	public static void insert(Connection con) {
		String sql = "insert into student(Name,Age,Address) values (?,?,?)";
		try {
			for (int i = 1; i <= 5; i++) {
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, "Hung" + i);
				ps.setInt(2, 28 + i);
				ps.setString(3, "Ha noi" + i);
				ps.execute();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void print(Connection con) {
		String sql = "select * from student";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Id = " + rs.getInt("Id") + " & Name =" + rs.getString(2) + " & Age =" + rs.getInt(3)
						+ " & Address=" + rs.getString("Address"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
