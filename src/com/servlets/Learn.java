package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DAO;

/**
 * Servlet implementation class LearnController
 */
@WebServlet("/Learn")
public class Learn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Learn() {
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
			DAO dao_tutorial = new DAO();

			request.setAttribute("tutorialList",
					dao_tutorial.getTutorialList());
			request.setAttribute("resultUser",
					dao_tutorial.getUserDetails(
							(int) request.getSession().getAttribute("userId"))
							.get(0));
			request.getRequestDispatcher("/learn.jsp").forward(request,
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
