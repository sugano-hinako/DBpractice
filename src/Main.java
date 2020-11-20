import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			// データベースの接続
			con = DriverManager.getConnection("jdbc:h2:~/mydb", "sugano", "");

			// SQLの準備
			pstmt = con.prepareStatement("SELECT * FROM TEST");
			//更新するメソッド
			//pstmt.executeUpdate();
			//検索系SQL文の送信
			rs = pstmt.executeQuery();
			// 結果の処理
			while(rs.next()) {
				System.out.println("名前：" + rs.getString("MONSTERS")+ " " + "HP：" + rs.getString("HP"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//３、データベース接続の切断
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
