package masawamor.h2db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
	private final static String url = "jdbc:h2:mem:test;MODE=MYSQL;DB_CLOSE_DELAY=-1";
	private final static String user = "sa";
	private final static String password = "";

	public static void main(String[] args) throws Exception {

		Class.forName("org.h2.Driver");
		
		try (Connection conn = DriverManager.getConnection(url, user, password)) {

			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE test (id int, name varchar);");

			String sql = "INSERT INTO test (id, name) VALUES (?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, 1);
			pstmt.setString(2, "masawa");
			pstmt.execute();

			pstmt.setInt(1, 2);
			pstmt.setString(2, "aosan");
			pstmt.execute();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM test;");
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				System.out.println(String.format("id=%d name=%s", id, name));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("finish");
	}
}
