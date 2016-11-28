package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DAO;

/**
 * Servlet implementation class UserServlet. This class is responsible for
 * performing all the user related actions in the database.
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/hibernateCodeMate";
	public static String username;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		// this block is responsible for getting queries from request
		// and then perform appropriate actions.
		if (request.getParameter("query").equals("checkUser")) {
			String usernameValue = request.getParameter("userName");
			String exist = checkDataExist(usernameValue, "username");
			response.getWriter().write(exist);
		} else if (request.getParameter("query").equals("checkUserEmail")) {
			String userEmailValue = request.getParameter("userEmail");
			String exist = checkDataExist(userEmailValue, "email");
			response.getWriter().write(exist);
		} else if (request.getParameter("query").equals("editUserEmail")) {
			String userEmailValue = request.getParameter("userEmail");
			String userHandleValue = request.getParameter("userHandle");
			DAO userDao = new DAO();
			if (userDao.checkEmailValid(userEmailValue, userHandleValue)) {
				response.getWriter().write("valid");
			} else {
				response.getWriter().write("invalid");
			}

		} else if (request.getParameter("query").equals("CreateUserQuery")) {
			String fname = request.getParameter("first_name");
			String lname = request.getParameter("last_name");
			String countryID = request.getParameter("countryID");
			String email = request.getParameter("email");
			String schoolID = request.getParameter("schoolID");
			String uname = request.getParameter("username");
			String upwd = request.getParameter("userPassword");
			String schoolCity = request.getParameter("schoolCity");
			String schoolName = request.getParameter("schoolName");
			createUser(fname, lname, email, countryID, schoolID, uname, upwd,
					schoolCity, schoolName);
			int userID = loginUser(uname, upwd);
			if (userID != -1) {
				HttpSession session = request.getSession();

				// setting the attribute customers to the customers object
				session.setAttribute("userHandle", uname);
				session.setAttribute("userId", userID);
				response.getWriter().write("true");

			} else {
				System.out.println("Some problem in create user");
			}
		} else if (request.getParameter("query").equals("UpdateUserQuery")) {
			String fname = request.getParameter("first_name");
			String lname = request.getParameter("last_name");
			String countryID = request.getParameter("countryID");
			String email = request.getParameter("email");
			String schoolID = request.getParameter("schoolID");
			String upwd = request.getParameter("userPassword");
			String schoolCity = request.getParameter("schoolCity");
			String schoolName = request.getParameter("schoolName");
			DAO updateUserDao = new DAO();

			updateUserDao.updateUserProfile(fname, lname, email,
					Integer.parseInt(countryID), schoolID, upwd, schoolCity,
					schoolName,
					(int) request.getSession().getAttribute("userId"));

		} else if (request.getParameter("query").equals("logout")) {
			request.getSession().invalidate();
		}

		else if (request.getParameter("query").equals("LoginUserQuery")) {
			String uname = request.getParameter("username");
			String upwd = request.getParameter("userPassword");
			int userID = loginUser(uname, upwd);
			if (userID != -1) {
				HttpSession session = request.getSession();

				// setting the attribute customers to the customers object
				session.setAttribute("userHandle", uname);
				session.setAttribute("userId", userID);
				response.getWriter().println("success");
			} else {
				response.getWriter()
						.println("Username/Password does not exist");
			}
		} else if (request.getParameter("query").equals("goToNextLesson")) {
			int currentTutorial = Integer
					.parseInt(request.getParameter("currentTutorial"));
			updateTutorialProgress(currentTutorial, request);

		} else if (request.getParameter("query").equals("markAsComplete")) {

			setTutorialComplete(request);

		} else if (request.getParameter("query").equals("solutionAccept")) {
			String problemId = request.getParameter("problemId");
			boolean accept = Boolean
					.parseBoolean(request.getParameter("solAccept"));
			DAO userDAO = new DAO();
			if (accept) {
				userDAO.insertSolutionSubmit(problemId, true, "successful",
						(int) request.getSession().getAttribute("userId"));
			} else {
				userDAO.insertSolutionSubmit(problemId, false, "unsuccessful",
						(int) request.getSession().getAttribute("userId"));
			}

		}

	}

	/**
	 * Sets all the tutorial as complete in the database.
	 * 
	 * @param request
	 *            http servlet request
	 */
	protected void setTutorialComplete(HttpServletRequest request) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, "root", "1234");
			Statement stmt = conn.createStatement();
			HttpSession session = request.getSession();
			String sql = "";
			sql = "UPDATE user SET TutorialCompleted=1 WHERE UserHandle='"
					+ session.getAttribute("userHandle") + "'";
			stmt.execute(sql);

			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	/**
	 * Sets tutorial progress id of the user in the database.
	 * 
	 * @param currentTutorial
	 *            current tutorial id
	 * @param request
	 *            http servlet request
	 * @return if the current tutorial is matching user tutorial
	 */
	protected boolean updateTutorialProgress(int currentTutorial,
			HttpServletRequest request) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, "root", "1234");
			Statement stmt = conn.createStatement();
			HttpSession session = request.getSession();
			String sql = "SELECT TutorialProgressID FROM user WHERE UserHandle='"
					+ session.getAttribute("userHandle") + "'";
			stmt.execute(sql);

			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int userTutorial = rs.getInt("TutorialProgressID");
			rs.close();

			if (currentTutorial == userTutorial) {
				userTutorial += 1;
				sql = "UPDATE user SET TutorialProgressID=" + userTutorial
						+ " WHERE UserHandle='"
						+ session.getAttribute("userHandle") + "'";
				boolean result = stmt.execute(sql);

				stmt.close();
				conn.close();
				return result;
			} else {
				stmt.close();
				conn.close();
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}

	}

	/**
	 * Creates user in the database after user sign up.
	 * 
	 * @param fname
	 *            first name of user
	 * @param lname
	 *            last name of user
	 * @param email
	 *            user email id
	 * @param countryID
	 *            user country id
	 * @param schoolID
	 *            user school id
	 * @param uname
	 *            user handle name
	 * @param upwd
	 *            user password
	 * @param schoolCity
	 *            city of users school
	 * @param schoolName
	 *            school name of user
	 * @return user successfully created or not
	 */
	protected boolean createUser(String fname, String lname, String email,
			String countryID, String schoolID, String uname, String upwd,
			String schoolCity, String schoolName) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, "root", "1234");
			Statement stmt = conn.createStatement();
			String sql = "";

			// check if school not exist in dropdown
			if (schoolID == null) {

				// insert into school table
				sql = "INSERT INTO school(SchoolName,SchoolCity,CountryID) VALUES ('"
						+ schoolName + "','" + schoolCity + "'," + countryID
						+ ")";
				stmt.execute(sql);

				sql = "SELECT SchoolID FROM `school` WHERE `SchoolID`= LAST_INSERT_ID()";
				ResultSet rs = stmt.executeQuery(sql);
				rs.next();
				schoolID = rs.getString("SchoolID");
				rs.close();
			}

			// org.myorg.services.PasswordService.getInstance().encrypt(request.getParameter("password"))
			// String
			// encrypted_pwd=org.myorg.services.PasswordService.getInstance().encrypt(upwd);
			sql = "INSERT INTO user(FirstName,LastName,Email,SchoolID,UserHandle,Password) VALUES ('"
					+ fname + "','" + lname + "','" + email + "'," + schoolID
					+ ",'" + uname + "','" + upwd + "')";
			stmt.execute(sql);

			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}

		return true;
	}

	/**
	 * Check if users email id or user handle already exists in the database.
	 * 
	 * @param data
	 *            actual data to match against
	 * @param dataType
	 *            type of column to match
	 * @return if the details exist or not
	 */
	protected String checkDataExist(String data, String dataType) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, "root", "1234");
			Statement stmt = conn.createStatement();
			String sql = "";

			// org.myorg.services.PasswordService.getInstance().encrypt(request.getParameter("password"))
			// String
			// encrypted_pwd=org.myorg.services.PasswordService.getInstance().encrypt(upwd);
			if (dataType.equals("username")) {
				sql = "SELECT * from user WHERE UserHandle='" + data + "'";

			} else if (dataType.equals("email")) {
				sql = "SELECT * from user WHERE Email='" + data + "'";
			}
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.isBeforeFirst()) {
				return "false";
			} else {
				return "exist";
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "";
		}
	}

	/**
	 * Check if the user handle and password matches with the database when user
	 * login
	 * 
	 * @param uname
	 *            user handle of a user
	 * @param upwd
	 *            password of a user
	 * @return login operation status
	 */
	protected int loginUser(String uname, String upwd) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, "root", "1234");
			Statement stmt = conn.createStatement();
			String sql = "";

			// org.myorg.services.PasswordService.getInstance().encrypt(request.getParameter("password"))
			// String
			// encrypted_pwd=org.myorg.services.PasswordService.getInstance().encrypt(upwd);
			sql = "SELECT * from user WHERE Password='" + upwd
					+ "' AND UserHandle='" + uname + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				int userID = rs.getInt("UserID");
				stmt.close();
				conn.close();
				return userID;
			} else {
				stmt.close();
				conn.close();
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return -1;
		}

	}
}
