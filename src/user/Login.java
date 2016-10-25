package user;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataBase.DataBase;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	DataBase model;
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
model = new DataBase();
		String userN = request.getParameter("user");
		
		String pass = request.getParameter("pass");
		System.out.println(userN+ " " + pass);
		session.setAttribute("name", userN);
		ResultSet rs = model.select("users", "pass = '" + pass
				+ "' and userName='" + userN + "'");
		try {
			if (rs != null) {
				System.out.println(request.getAttribute("session"));

				System.out.println("Welcome");
				rs.next();
				rs.getString("userName");
				response.sendRedirect("");
				session.setAttribute("userN", userN);
			} else {
				System.out.println("Not Found");
				response.sendRedirect("");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
