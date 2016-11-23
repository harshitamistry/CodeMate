package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DAO;

/**
 * Servlet implementation class Forumquestion
 */
@WebServlet("/Forumquestion")
public class Forumquestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Forumquestion() {
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

		DAO forumQuestionDao = new DAO();
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("questionHeading",
				forumQuestionDao.getQuestion(id));
		request.setAttribute("answerList", forumQuestionDao.getAnswerList(id));
		request.getRequestDispatcher("/forumquestion.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		DAO questionDao = new DAO();
		if (request.getParameter("query").equals("addAnswer")) {
			questionDao.insertAnswer(
					(int) request.getSession().getAttribute("userId"),
					request.getParameter("answer"),
					Integer.parseInt(request.getParameter("questionId")));
			response.sendRedirect("/CodeMateMVC/Forumquestion?id="
					+ request.getParameter("questionId"));
		}
	}

}
