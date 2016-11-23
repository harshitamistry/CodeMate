package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DAO;

/**
 * Servlet implementation class Practice
 */
@WebServlet("/Practice")
public class Practice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Practice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public enum tutorialComplexity {
		easy, medium, hard
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
			request.setAttribute("tutorialComplexity",
					tutorialComplexity.values());
			DAO practiceDao = new DAO();
			request.setAttribute("problemList",
					practiceDao.getAllProblems("Practice"));
			request.setAttribute("userSubmission",
					practiceDao.checkUserSolution((int) request.getSession()
							.getAttribute("userId")));
			request.getRequestDispatcher("/practice.jsp").forward(request,
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
