package com.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Forumquestion;
import com.dao.DAO;

/**
 * Servlet implementation class Forum
 */
@WebServlet("/Forum")
public class Forum extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Forum() {
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

		// get list of questions and send to forum page
		DAO forumDao = new DAO();

		request.setAttribute("questionList", forumDao.getQuestionList());
		request.getRequestDispatcher("/forum.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		DAO questionDao = new DAO();

		// when user asks a new question
		if (request.getParameter("query").equals("newQuestion")) {
			questionDao.insertQuestion(
					(int) request.getSession().getAttribute("userId"),
					request.getParameter("question"));
			response.sendRedirect("/CodeMateMVC/Forum");
		} else if (request.getParameter("query").equals("searchQuestion")) {

			// when user looks up for the question
			List<Forumquestion> questions = questionDao
					.searchQuestionList(request.getParameter("term"));
			List<String> questionStrings = new ArrayList<String>();
			// Gson gson = new Gson();
			String json = "{\"questions\":[";
			boolean first = true;
			for (Forumquestion q : questions) {
				if (!first)
					json += ",";
				else
					first = false;
				json += "{\"value\":\"" + q.getQuestionId() + "\",\"label\":\""
						+ q.getQuestion().trim() + "\"}";
			}
			json += "]}";
			// String json = gson.toJson(questionStrings.toArray());
			System.out.println(json);
			response.getWriter().print(json);
		}
	}

}
