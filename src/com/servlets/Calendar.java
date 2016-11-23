package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Contest;
import com.dao.DAO;

/**
 * Servlet implementation class Calendar
 */
@WebServlet("/Calendar")
public class Calendar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Calendar() {
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
		if (request.getSession().getAttribute("userId") == null) {
			response.sendRedirect("/CodeMateMVC/SignUp");
		} else {
			DAO daoCompetitions = new DAO();
			List<Contest> contestList = daoCompetitions.getAllContests();
			String json = "[";
			boolean first = true;
			for (Contest c : contestList) {
				if (first)
					first = false;
				else
					json += ",";
				json += "{id:'" + c.getContestId() + "',title:'"
						+ c.getContestName() + " (" + c.getContestType()
						+ ")',start:'" + c.getContestStartDate().toString()
						+ "',end:'" + c.getContestEndDate().toString()
						+ "',url:'/CodeMateMVC/CompetitionProblems?contestId="
						+ c.getContestId() + "'}";
			}
			json += "]";
			System.out.println(json);
			request.setAttribute("eventsJson", json);
			request.getRequestDispatcher("/calendar.jsp").forward(request,
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
