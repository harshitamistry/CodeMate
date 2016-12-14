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
 * Servlet implementation class LeaderBoard
 */
@WebServlet("/LeaderBoard")
public class LeaderBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LeaderBoard() {
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

		// check to see if user is already signed in or not
		if (request.getSession().getAttribute("userId") == null) {
			response.sendRedirect("/CodeMateMVC/SignUp");
		} else {

			// get flags for all the countries
			DAO LBDao = new DAO();

			if (request.getParameterMap().containsKey("query")
					&& request.getParameter("query").equals("getFlag")) {
				byte[] flag = LBDao.getUserFlag(
						Integer.parseInt(request.getParameter("userId")));
				response.getOutputStream().write(flag);
			} else {

				// get top ten users
				List<User> users = LBDao.getTopTen();
				request.setAttribute("topTen", users);
				request.setAttribute("countryList", LBDao.getCountryList());

				request.getRequestDispatcher("/leaderboard.jsp")
						.forward(request, response);
			}
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
		if (request.getParameterMap().containsKey("query")
				&& request.getParameter("query").equals("topTenCountry")) {
			DAO LBDao = new DAO();
			int countryId = Integer
					.parseInt(request.getParameter("countryId"));
			List<User> users = LBDao.getTopTenCountry(countryId);
			String json = "[";
			boolean first = true;
			int cnt = 1;
			for (User u : users) {
				if (first)
					first = false;
				else
					json += ",";
				json += "{\"rank\":\"" + cnt++ + "\",\"handle\":\""
						+ u.getUserHandle() + "\",\"userId\":\""
						+ u.getUserId() + "\",\"points\":\"" + u.getPoints()
						+ "\"}";
			}
			json += "]";
			response.getWriter().print(json);
		} else if (request.getParameterMap().containsKey("query")
				&& request.getParameter("query").equals("topTenSchool")) {
			DAO LBDao = new DAO();
			int schoolId = Integer.parseInt(request.getParameter("schoolId"));
			List<User> users = LBDao.getTopTenSchool(schoolId);
			String json = "[";
			boolean first = true;
			int cnt = 1;
			for (User u : users) {
				if (first)
					first = false;
				else
					json += ",";
				json += "{\"rank\":\"" + cnt++ + "\",\"handle\":\""
						+ u.getUserHandle() + "\",\"userId\":\""
						+ u.getUserId() + "\",\"points\":\"" + u.getPoints()
						+ "\"}";
			}
			json += "]";
			response.getWriter().print(json);
		} else {
			doGet(request, response);
		}
	}

}
