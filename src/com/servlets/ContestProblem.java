package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Contest;
import com.beans.Groups;
import com.dao.DAO;

/**
 * Servlet implementation class ContestProblem
 */
@WebServlet("/ContestProblem")
public class ContestProblem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContestProblem() {
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

		// check if user is already registered or not
		if (request.getSession().getAttribute("userId") == null) {
			response.sendRedirect("/CodeMateMVC/SignUp");
		} else {

			// take user to particular problem
			DAO userDAO = new DAO();
			request.setAttribute("contestProblem", userDAO
					.getProblem(Integer.parseInt(request.getParameter("id"))));
			request.getRequestDispatcher("/contestProblem.jsp")
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

		// if the solution is accepted
		DAO userDAO = new DAO();
		if (request.getParameter("query").equals("solutionAccept")) {
			String problemId = request.getParameter("problemId");
			int contestId = (int) request.getSession()
					.getAttribute("contestId");

			Contest contest = userDAO.getContest(contestId);

			boolean accept = Boolean
					.parseBoolean(request.getParameter("solAccept"));
			String contestType = contest.getContestType();

			// check if the competition is individual or group
			if (contestType.equals("Group")) {

				// get the group
				Groups group = userDAO.getGroupDetails(
						(int) request.getSession().getAttribute("userId"),
						contestId, "userId");

				if (accept) {
					userDAO.insertGroupCompetitionSolution(contestId,
							problemId, true, "successful", group.getGroupId());

				} else {
					userDAO.insertGroupCompetitionSolution(contestId,
							problemId, false, "unsuccessful",
							group.getGroupId());

				}

			} else {
				if (accept) {
					userDAO.insertCompetitionSolution(contestId, problemId,
							true, "successful",
							(int) request.getSession().getAttribute("userId"));

				} else {
					userDAO.insertCompetitionSolution(contestId, problemId,
							false, "unsuccessful",
							(int) request.getSession().getAttribute("userId"));

				}
			}

		}
	}

}
