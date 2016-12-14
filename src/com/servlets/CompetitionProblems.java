package com.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Contest;
import com.beans.Groups;
import com.beans.Problems;
import com.dao.DAO;

/**
 * Servlet implementation class CompetitionProblems
 */
@WebServlet("/CompetitionProblems")
public class CompetitionProblems extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompetitionProblems() {
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

		// check if user is signed in or not
		if (request.getSession().getAttribute("userId") == null) {
			response.sendRedirect("/CodeMateMVC/SignUp");
		} else {

			// get all the problems related to that competitions and their
			// details
			DAO competitionProblemsDao = new DAO();
			Contest c = competitionProblemsDao.getContest(
					Integer.parseInt(request.getParameter("contestId")));
			if (!competitionProblemsDao.checkIfRegistered(
					(int) request.getSession().getAttribute("userId"),
					c.getContestId(), c.getContestType())) {
				response.sendRedirect("/CodeMateMVC/Competitions");
				return;
			}
			List<Problems> contestProblems = new ArrayList<Problems>();
			Contest contest = competitionProblemsDao.getContest(
					Integer.parseInt(request.getParameter("contestId")));

			request.getSession().setAttribute("contestId",
					Integer.parseInt(request.getParameter("contestId")));

			request.setAttribute("problemList",
					competitionProblemsDao.getContestProblems(Integer
							.parseInt(request.getParameter("contestId"))));

			if (contest.getContestType().equals("Individual")) {
				request.setAttribute("userSubmission",
						competitionProblemsDao.checkUserSolution((int) request
								.getSession().getAttribute("userId")));

			} else {
				Groups group = competitionProblemsDao.getUserGroup(
						(int) request.getSession().getAttribute("userId"),
						Integer.parseInt(request.getParameter("contestId")));
				request.setAttribute("userSubmission", competitionProblemsDao
						.checkGroupSolution(group.getGroupId()));

			}
			request.getRequestDispatcher("/competitionProblems.jsp")
					.forward(request, response);
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
