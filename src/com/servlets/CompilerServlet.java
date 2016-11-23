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
import javax.servlet.http.HttpSession;

import com.beans.Testcases;
import com.dao.DAO;
import com.google.gson.Gson;

/**
 * This class is responsible for matching the output after running a python
 * program.
 */
@WebServlet("/CompilerServlet")
public class CompilerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/codemate";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompilerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Matches different queries and perform actions accordingly.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		if (request.getParameter("query").equals("check_output")) {
			String output = request.getParameter("output");
			String tutorial_id = request.getParameter("tutorial_id");
			String result = Boolean
					.toString(checkProgramOutput(output, tutorial_id));
			if (result.equals("true")) {
				increasePoints(request, tutorial_id);
			}

			response.getWriter().println(result);
		} else if (request.getParameter("query").equals("testcase")) {
			String problemId = request.getParameter("problemId");
			String testcaseType = request.getParameter("testcaseType");
			String inputStack = getTestcaseInput(testcaseType, problemId);
			response.getWriter().print(inputStack);
		} else if (request.getParameter("query")
				.equals("checkTestcaseOutput")) {
			String testcaseId = request.getParameter("testcaseID");
			String testcaseOutput = request.getParameter("testcaseOP");
			Boolean checkResult = checkTestcaseResult(testcaseId,
					testcaseOutput);
			response.getWriter().print(checkResult);

		}

	}

	/**
	 * Returns a boolean stating if the program output matches with the output
	 * stored in the database.
	 * 
	 * @param testcaseId
	 *            id of a testcase
	 * @param testcaseOutput
	 *            user program output
	 * @return true if output matches, false otherwise
	 */
	protected Boolean checkTestcaseResult(String testcaseId,
			String testcaseOutput) {
		try {
			DAO compilerDao = new DAO();
			Testcases testcase = compilerDao
					.getTestcaseData(Integer.parseInt(testcaseId));
			String dbTestcaseOp = testcase.getOutput();

			if (dbTestcaseOp.equals(testcaseOutput)) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Give input to a Python program from database.This function is used when a
	 * program requires input, that testcase input is given to the program to
	 * test user program.
	 * 
	 * @param testcaseType
	 *            check if testcase type is 'submit' or 'test'
	 * @param problemID
	 *            ID of a problem
	 * @return input stored in the database
	 */
	protected String getTestcaseInput(String testcaseType, String problemId) {
		try {

			DAO testcaseInput = new DAO();
			List<Testcases> testcases = testcaseInput.getTestcaseInputData(
					testcaseType, Integer.parseInt(problemId));
			ArrayList<String> inputList = new ArrayList<String>();
			ArrayList<Integer> testcaseIdList = new ArrayList<Integer>();
			for (Testcases cases : testcases) {
				inputList.add(cases.getInput());
				testcaseIdList.add(cases.getTestcaseId());
			}

			Gson gson = new Gson();
			String result = gson.toJson(inputList.toArray());
			String testcaseID = gson.toJson(testcaseIdList.toArray());
			String testcaseDict = "{\"testcases\":" + result
					+ ",\"testcaseIDs\":" + testcaseID + "}";

			return testcaseDict;

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * Increase points of current user in the database if user has successfully
	 * solved the problem.
	 * 
	 * @param request
	 *            http request object
	 * @param tutorialId
	 *            id of a tutorial
	 */
	protected void increasePoints(HttpServletRequest request,
			String tutorialId) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, "root",
					"1234");
			Statement stmt = conn.createStatement();
			HttpSession session = request.getSession();
			String sql = "";
			sql = "SELECT ProblemPoints FROM problems p INNER JOIN exerciseproblem"
					+ " e ON p.ProblemID=e.problemID WHERE TutorialID="
					+ tutorialId;
			ResultSet rs = stmt.executeQuery(sql);
			int op = 0;
			int user_points = 0;
			int tut_progress_id = 1;
			if (rs.next()) {
				op = rs.getInt("ProblemPoints");
			}
			rs.close();
			// get current points from user
			sql = "SELECT Points,TutorialProgressID FROM user WHERE UserHandle = '"
					+ session.getAttribute("userHandle") + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				user_points = rs.getInt("Points");
				tut_progress_id = rs.getInt("TutorialProgressID");
			}
			rs.close();
			if (Integer.parseInt(tutorialId) >= tut_progress_id) {
				user_points = op + user_points;
				sql = "UPDATE user SET Points=" + user_points
						+ " WHERE UserHandle='"
						+ session.getAttribute("userHandle") + "'";
				stmt.execute(sql);
			}
			stmt.close();
			conn.close();

		} catch (

		Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * Tests whether user has performed tutorial exercise successfully by
	 * matching output of a python program with the output stored in the
	 * database.
	 * 
	 * @param output
	 *            output of a python program.
	 * @param tutorialId
	 *            id of a tutorial
	 * @return true if the output matches, false otherwise
	 */
	protected boolean checkProgramOutput(String output, String tutorialId) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, "root",
					"1234");
			Statement stmt = conn.createStatement();
			String sql = "";

			// org.myorg.services.PasswordService.getInstance().encrypt(request.getParameter("password"))
			// String
			// encrypted_pwd=org.myorg.services.PasswordService.getInstance().encrypt(upwd);
			sql = "SELECT Solution from problems,exerciseproblem WHERE problems.ProblemID=exerciseproblem.ProblemID and exerciseproblem.TutorialID="
					+ tutorialId;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String op = rs.getString("Solution").trim();
				stmt.close();
				conn.close();
				System.out.println(output);
				if (op.equals(output.trim())) {
					return true;
				} else {
					return false;
				}
			} else {
				stmt.close();
				conn.close();
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
