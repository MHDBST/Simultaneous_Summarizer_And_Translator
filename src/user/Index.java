package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import summarization.Test;

import com.sun.xml.internal.bind.v2.runtime.Location;

import dataBase.DataBase;
import finalSystem.TranslateSummarize;

/**
 * Servlet implementation class Index
 */
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Index() {
		super();
		// new URLConnection();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String newsN = request.getParameter("name");
		String percent = request.getParameter("percent");
		String id = request.getParameter("id");
		System.out.println(id);
		DataBase db = new DataBase();
		ResultSet rs = db.select("comment", " 1=1 ");
		try {
			while (rs.next()) {

				request.setAttribute("comment0", rs.getString("com"));

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			TranslateSummarize ts = new TranslateSummarize(newsN,
					Float.parseFloat(percent));
			ts.getSummary();
			ts.getTrans();
			request.setAttribute("summary", ts.getSummary());
			request.setAttribute("trans", ts.getTrans());
			request.getRequestDispatcher("")
					.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// PrintWriter out = response.getWriter();
		// out.println("<html><body>Hellllloooooo " + newsN + "</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<html><body>Hellllloooooo</body></html>");
	}

}
