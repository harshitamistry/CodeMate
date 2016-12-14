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
import com.dao.DAO;

/**
 * Servlet implementation class Competitions
 */
@WebServlet("/Competitions")
public class Competitions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Competitions() {
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
		if (request.getSession().getAttribute("userId") == null) {
			response.sendRedirect("/CodeMateMVC/SignUp");
		} else {
			DAO daoCompetitions = new DAO();

			if (request.getParameterMap().containsKey("query")
					&& request.getParameter("query").equals("register")) {
				daoCompetitions.insertIndividualUser(
						(int) request.getSession().getAttribute("userId"),
						Integer.parseInt(request.getParameter("contestId")));
				response.sendRedirect("/CodeMateMVC/Competitions");
				return;
			} else if (request.getParameterMap().containsKey("query")
					&& request.getParameter("query").equals("registerGroup")) {
			}
			List<Contest> contestList = daoCompetitions.getAllContests();
			request.setAttribute("contestList", contestList);
			List<Integer> contestPoints = new ArrayList<Integer>();
			for (Contest c : contestList) {
				contestPoints.add(
						daoCompetitions.getContestPoints(c.getContestId()));
			}
			request.setAttribute("contestPoints", contestPoints);
			List<Boolean> contestRegistrations = new ArrayList<Boolean>();
			for (Contest c : contestList) {
				contestRegistrations.add(daoCompetitions.checkIfRegistered(
						(int) request.getSession().getAttribute("userId"),
						c.getContestId(), c.getContestType()));
			}
			request.setAttribute("contestRegistrations", contestRegistrations);

			request.getRequestDispatcher("/competitions.jsp").forward(request,
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
		System.out.println("in post");

		DAO competitionsDao = new DAO();
		if (request.getParameter("query").equals("addGroup")) {
			System.out.println("in check group");

			String groupName = request.getParameter("groupName");
			String user = (String) request.getSession()
					.getAttribute("userHandle");
			String otherUser = request.getParameter("user");
			String contestId = request.getParameter("contestId");
			String status = competitionsDao.addGroup(groupName, user,
					otherUser, contestId);
			System.out.println("in check group and group is " + status);
			response.getWriter().write(status);
		}

	}

}
