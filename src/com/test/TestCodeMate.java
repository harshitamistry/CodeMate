package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.beans.Contest;
import com.beans.Country;
import com.beans.Forumanswer;
import com.beans.Forumquestion;
import com.beans.Groups;
import com.beans.Problems;
import com.beans.School;
import com.beans.Submission;
import com.beans.Testcases;
import com.beans.Tutorial;
import com.beans.User;
import com.dao.DAO;

public class TestCodeMate {
	DAO dao = new DAO();

	@Test
	public void testCountryList() {
		List<Country> countryList = dao.getCountryList();
		assertNotNull(countryList);
	}

	@Test
	public void testTopTen() {
		List<User> topUserList = dao.getTopTen();
		assertNotNull(topUserList);
	}

	@Test
	public void testTopTenCountry() {

		// getting country with invalid country id
		List<User> topUserList = dao.getTopTenCountry(2000);
		assertNotNull(topUserList);
		assertTrue(topUserList.isEmpty());

		topUserList = dao.getTopTenCountry(44);
		System.out.println(topUserList);
		assertNotNull(topUserList);
		assertFalse(topUserList.isEmpty());

	}

	@Test
	public void testTopTenSchool() {

		// getting country with invalid country id
		List<User> topUserSchoolList = dao.getTopTenSchool(3000);
		assertNotNull(topUserSchoolList);
		assertTrue(topUserSchoolList.isEmpty());

		topUserSchoolList = dao.getTopTenSchool(2);
		assertNotNull(topUserSchoolList);
		assertFalse(topUserSchoolList.isEmpty());

	}

	@Test
	public void testCountryFlag() {

		// getting flag with existing user
		byte[] flag = dao.getUserFlag(4);
		assertNotNull(flag);

		flag = dao.getUserFlag(900);
		assertNull(flag);

	}

	@Test
	public void testSchoolList() {
		List<School> schoolList = dao.getSchoolList();
		assertNotNull(schoolList);
	}

	@Test
	public void testQuestionList() {
		List<Forumquestion> questionList = dao.getQuestionList();
		assertNotNull(questionList);
	}

	@Test
	public void testQuestionsAnswerList() {

		// getting answer with a valid question id and it has some answers
		List<Forumanswer> answerList = dao.getAnswerList(2);
		assertNotNull(answerList);

		assertFalse(answerList.isEmpty());

		// getting answer with an invalid question id
		answerList = dao.getAnswerList(290);
		assertNotNull(answerList);
		assertTrue(answerList.isEmpty());

	}

	@Test
	public void testSearchQuestionList() {

		// getting question with empty value should give all the questions
		List<Forumquestion> questionList = dao.searchQuestionList("");

		assertEquals(questionList.size(), dao.getQuestionList().size());
		questionList = dao.searchQuestionList("some keyword xyz");
		assertNotNull(questionList);

	}

	@Test
	public void testTutorialsList() {
		List<Tutorial> tutorialList = dao.getTutorialList();
		assertNotNull(tutorialList);
	}

	@Test
	public void testTutorialsDetails() {

		// give valid tutorial id
		Tutorial tutorial = dao.getTutorialDetails(1);
		assertNotNull(tutorial);
		assertEquals(tutorial.getTutorialId(), (Integer) 1);

		// if tutorial with that tutorial id does not match
		tutorial = dao.getTutorialDetails(1000);
		assertNull(tutorial);
	}

	@Test
	public void testEmailValid() {

		// if user handle exists but email does not
		boolean emailValid = dao.checkEmailValid("somedummyemail@gmail.com",
				"Parker");
		assertTrue(emailValid);

		// if email exists
		emailValid = dao.checkEmailValid("pu@gmail.com", "Parker");
		assertFalse(emailValid);

		// if email exists but userhandle does not emailValid =
		dao.checkEmailValid("pu@gmail.com", "Parker22");
		assertFalse(emailValid);

	}

	@Test
	public void testProblemForTutorial() {

		// get problem with valid tutorial id
		Problems problem = dao.getProblemForTutorial(2);
		assertNotNull(problem);

		// get problem with invalid tutorial id
		problem = dao.getProblemForTutorial(2000);
		assertNull(problem);

	}

	@Test
	public void testTutorialCount() {

		// check tutorial is finish
		assertEquals("finish", dao.checkTutorialCount(5));
		assertEquals("incomplete", dao.checkTutorialCount(1));

		assertEquals("incomplete", dao.checkTutorialCount(1000));

	}

	@Test
	public void testGetProblems() {

		List<Problems> problems = dao.getAllProblems("Easy");
		assertNotNull(problems);
		problems = dao.getAllProblems("Medium");
		assertNotNull(problems);
		problems = dao.getAllProblems("Hard");
		assertNotNull(problems);
		problems = dao.getAllProblems("");
		assertNotNull(problems);

		// when the tutorial type is unknown problems =
		dao.getAllProblems("Unknown");
		assertTrue(problems.isEmpty());

	}

	@Test
	public void testGetContestProblems() {

		List<Problems> problems = dao.getContestProblems(1);
		assertNotNull(problems);

		// when put invalid contest id
		problems = dao.getContestProblems(100);
		assertNotNull(problems);
		assertTrue(problems.isEmpty());

	}

	@Test
	public void testCheckUserSolution() {

		List<Submission> submission = dao.checkUserSolution(4);
		assertNotNull(submission);

		submission = dao.checkUserSolution(100);
		assertNotNull(submission);
		assertTrue(submission.isEmpty());

	}

	@Test
	public void testGetUserGroup() {

		Groups group = dao.getUserGroup(4, 2);
		assertNotNull(group);

		// valid user id and invalid group id
		group = dao.getUserGroup(4, 200);
		assertNull(group);

		// invalid user id and invalid group id
		group = dao.getUserGroup(400, 200);
		assertNull(group);

		// invalid user id and valid group id
		group = dao.getUserGroup(400, 2);
		assertNull(group);

	}

	@Test
	public void testCheckGroupSolution() {

		List<Submission> submission = dao.checkGroupSolution(2);
		assertNotNull(submission);

		// invalid group submission = dao.checkGroupSolution(200);
		assertTrue(submission.isEmpty());

	}

	@Test
	public void testGetAllContests() {

		// database has two contests, it should not return null or empty
		List<Contest> contest = dao.getAllContests();
		assertNotNull(contest);
		assertFalse(contest.isEmpty());

	}

	@Test
	public void testGetContestPoints() {

		// this contest has number of points
		int points = dao.getContestPoints(2);
		assertNotEquals(points, 0);

		points = dao.getContestPoints(200);
		assertEquals(points, 0);

	}

	@Test
	public void testCheckIfRegistered() {

		boolean registered = dao.checkIfRegistered(4, 1, "Individual");
		assertTrue(registered);

		registered = dao.checkIfRegistered(100, 1, "Individual");
		assertFalse(registered);

	}

	@Test
	public void testGetCountry() {

		Country country = dao.getCountry(2000);
		assertNull(country);

		country = dao.getCountry(20);
		assertNotNull(country);
		assertEquals((Integer) country.getCountryId(), (Integer) 20);

	}

	@Test
	public void testGetSchool() {

		School school = dao.getSchool(2000);
		assertNull(school);

		school = dao.getSchool(2);
		assertNotNull(school);
		assertEquals((Integer) school.getSchoolId(), (Integer) 2);

	}

	@Test
	public void testGetTestcaseData() {

		Testcases testcases = dao.getTestcaseData(2000);
		assertNull(testcases);

		testcases = dao.getTestcaseData(2);
		assertNotNull(testcases);
		assertEquals((Integer) testcases.getTestcaseId(), (Integer) 2);

	}

	@Test
	public void testGetGroupDetails() {

		Groups groups = dao.getGroupDetails(30, 2, "groupId");
		assertNotNull(groups);

		groups = dao.getGroupDetails(100, 1, "groupId");
		assertNull(groups);

	}

	@Test
	public void testGetQuestion() {

		Forumquestion question = dao.getQuestion(1);
		assertNotNull(question);

		question = dao.getQuestion(100);
		assertNull(question);

	}
}
