package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/jdbcEX5")

public class jdbcEX5 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public jdbcEX5() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String DBURL = "jdbc:mysql://localhost:3306/study";
		String DBID = "root";
		String DBPW = "12345678";
		
		try {
				Class.forName(DRIVER);
				Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
				System.out.println("con"+con);
				
				String sql = "select * from student where id=?";
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1, "2");
				ResultSet result = preparedStatement.executeQuery();
			
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
			
				while(result.next()) {
					out.print(result.getString(1)+" / ");
					out.print(result.getString(2)+" / ");
					out.print(result.getString(3));
				}
				out.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("JDBC 드라이버 오류");
			} catch (SQLException e) {
				System.out.println("DB 연결 오류");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}