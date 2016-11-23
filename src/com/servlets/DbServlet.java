package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class DbServlet
 */
@WebServlet("/DbServlet")
public class DbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/hibernateCodeMate";
	public static String username;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DbServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (request.getParameter("query").equals("load_schools")) {

			String country = request.getParameter("countryname");
			String get_schools = getSchools(country);
			System.out.println("working!country is " + country);
			response.setContentType("application/json");
			response.getWriter().write(get_schools);
		} else {
			System.out.println("not working");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * Get schools from the database.
	 * 
	 * @param countryName
	 *            Name of a country to get schools within that country
	 * @return school list as json string.
	 */
	// database connection for getting schools
	private String getSchools(String countryName) {
		List<String> school_list = new ArrayList<String>();
		String json = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, "root",
					"1234");
			Statement stmt = conn.createStatement();
			String sql = "Select * from school s INNER JOIN country c ON "
					+ "c.CountryID = s.CountryID WHERE c.CountryName='"
					+ countryName + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				school_list.add(rs.getString("SchoolID"));
				school_list.add(rs.getString("SchoolName"));
			}
			json = new Gson().toJson(school_list);
			System.out.print(json);
			rs.close();
			stmt.close();
			conn.close();
			return json;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
