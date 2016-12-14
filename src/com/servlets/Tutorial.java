package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.User;
import com.dao.DAO;

/**
 * Servlet implementation class Tutorial
 */
@WebServlet("/Tutorial")
public class Tutorial extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Tutorial() {
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

		// check to see if the user is already signed in or not
		if (request.getSession().getAttribute("userId") == null) {
			response.sendRedirect("/CodeMateMVC/SignUp");
		} else {

			DAO tutorial_dao = new DAO();
			List<User> userList = tutorial_dao.getUserDetails(
					(int) request.getSession().getAttribute("userId"));
			int progressId = userList.get(0).getTutorial().getTutorialId();

			// when user tries to access the locked tutorial go to learn page
			if (progressId < Integer.parseInt(request.getParameter("id"))) {
				response.sendRedirect("/CodeMateMVC/Learn");
			}

			// set the tutorial content for tutorial page
			request.setAttribute("tutorial", tutorial_dao.getTutorialDetails(
					Integer.parseInt(request.getParameter("id"))));

			request.setAttribute("tutorialStatus",
					tutorial_dao.checkTutorialCount(
							Integer.parseInt(request.getParameter("id"))));
			System.out.println(
					"tutorial status is " + tutorial_dao.checkTutorialCount(
							Integer.parseInt(request.getParameter("id"))));

			request.setAttribute("tutorialProblem",
					tutorial_dao.getProblemForTutorial(
							Integer.parseInt(request.getParameter("id"))));

			request.getRequestDispatcher("/Tutorial.jsp").forward(request,
					response);

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
		doGet(request, response);
	}

}
