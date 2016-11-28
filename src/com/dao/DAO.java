package com.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.beans.Awards;
import com.beans.Contest;
import com.beans.ContestUser;
import com.beans.ContestUserId;
import com.beans.Contestgroup;
import com.beans.ContestgroupId;
import com.beans.Country;
import com.beans.Forumanswer;
import com.beans.ForumanswerId;
import com.beans.Forumquestion;
import com.beans.Groups;
import com.beans.Problems;
import com.beans.School;
import com.beans.Submission;
import com.beans.Testcases;
import com.beans.Tutorial;
import com.beans.User;

public class DAO {
	SessionFactory sessionFactory = new Configuration()
			.configure("com/config/hibernate.cfg.xml").buildSessionFactory();

	public List<Country> getCountryList() {

		Session session = null;
		List<Country> countryList = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("Country.allCountry");
			// query.setParameter("department", department);

			countryList = (List<Country>) query.getResultList();

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return countryList;

	}

	public List<User> getTopTen() {
		Session session = null;
		List<User> countryList = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("User.topTen");
			// query.setParameter("department", department);
			query.setMaxResults(10);

			countryList = (List<User>) query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return countryList;

	}

	public List<User> getTopTenCountry(int countryId) {
		Session session = null;
		List<User> countryList = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("User.topTenCountry");
			query.setParameter("countryId", countryId);
			query.setMaxResults(10);

			countryList = (List<User>) query.getResultList();

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return countryList;

	}

	public List<User> getTopTenSchool(int schoolId) {
		Session session = null;
		List<User> countryList = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("User.topTenSchool");
			query.setParameter("schoolId", schoolId);
			query.setMaxResults(10);

			countryList = (List<User>) query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return countryList;

	}

	public byte[] getUserFlag(int userId) {
		Session session = null;
		byte[] flag = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("Country.getFlagOfUser");
			query.setParameter("userId", userId);
			flag = (byte[]) query.getSingleResult();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return flag;
	}

	public List<School> getSchoolList() {
		Session session = null;
		List<School> schoolList = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			// String sql = "select s from School s JOIN s.country c where
			// c.countryName = 'Canada'";
			// Query query = session.createQuery(sql);
			Query query = session.getNamedQuery("School.countrySchool");

			// List results = query.getResultList();
			schoolList = (List<School>) query.getResultList();

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return schoolList;
	}

	public List<Forumquestion> getQuestionList() {
		Session session = null;
		List<Forumquestion> schoolList = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			// String sql = "select s from School s JOIN s.country c where
			// c.countryName = 'Canada'";
			// Query query = session.createQuery(sql);
			Query query = session.getNamedQuery("Forumquestion.allQuestions");

			schoolList = (List<Forumquestion>) query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return schoolList;
	}

	public List<Forumanswer> getAnswerList(int questionId) {
		Session session = null;
		List<Forumanswer> schoolList = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			// String sql = "select s from School s JOIN s.country c where
			// c.countryName = 'Canada'";
			// Query query = session.createQuery(sql);
			Query query = session.getNamedQuery("Forumanswer.allAnswers");
			query.setParameter("questionId", questionId);
			schoolList = (List<Forumanswer>) query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return schoolList;
	}

	public List<Forumquestion> searchQuestionList(String keyword) {
		Session session = null;
		List<Forumquestion> schoolList = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			// String sql = "select s from School s JOIN s.country c where
			// c.countryName = 'Canada'";
			// Query query = session.createQuery(sql);
			Query query = session
					.getNamedQuery("Forumquestion.searchQuestions");
			query.setParameter("keywords", "%" + keyword + "%");

			List results = query.getResultList();
			schoolList = (List<Forumquestion>) query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return schoolList;
	}

	/**
	 * Get all the tutorials from tutorial table.
	 * 
	 * @return list of tutorial object.
	 */
	public List<Tutorial> getTutorialList() {
		Session session = null;
		List<Tutorial> tutorialList = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("Tutorial.allTutorial");

			tutorialList = (List<Tutorial>) query.getResultList();

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return tutorialList;

	}

	public Tutorial getTutorialDetails(int tutorialId) {
		Session session = null;
		Tutorial tutorial = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("Tutorial.tutorialDetails");
			query.setParameter("tutId", tutorialId);
			tutorial = (Tutorial) query.getSingleResult();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return tutorial;

	}

	public boolean checkEmailValid(String userEmail, String userHandle) {
		Session session = null;
		boolean emailValid = false;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("User.checkEmailExist");
			query.setParameter("userEmail", userEmail);
			query.setParameter("currUserHandle", userHandle);

			emailValid = (query.getResultList().size() == 0);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return emailValid;

	}

	public Problems getProblemForTutorial(int tutorialId) {
		Session session = null;
		Problems problem = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("Problems.problemDetails");
			query.setParameter("tutId", tutorialId);

			problem = (Problems) query.getSingleResult();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return problem;

	}

	/**
	 * Get all the values from user table.
	 * 
	 * @return list of user object.
	 */
	public List<User> getUserDetails(int uid) {
		Session session = null;
		List<User> userDetails = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("User.allDetails");
			query.setParameter("uId", uid);

			userDetails = (List<User>) query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return userDetails;

	}

	public String checkTutorialCount(int tutId) {

		Session session = null;
		int lastTutorial = 0;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("Tutorial.tutorialCount");

			lastTutorial = (Integer) query.getSingleResult();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		System.out.println("tutoriql last count is " + lastTutorial
				+ "and tutoril id is " + tutId);
		if (lastTutorial == tutId) {
			return "finish";
		} else {
			return "incomplete";
		}

	}

	public List<Problems> getAllProblems(String problemType) {
		Session session = null;
		List<Problems> problems = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("Problems.allProblems");
			query.setParameter("problemType", problemType);

			problems = (List<Problems>) query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return problems;

	}

	public List<Problems> getContestProblems(int contestId) {
		Session session = null;
		List<Problems> problems = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("Contest.getProblems");
			query.setParameter("contestId", contestId);

			problems = (List<Problems>) query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return problems;

	}

	public List<Submission> checkUserSolution(int userId) {
		Session session = null;
		List<Submission> submission = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("Submission.getSubmission");
			query.setParameter("userId", userId);

			submission = (List<Submission>) query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return submission;

	}

	public Groups getUserGroup(int userId, int contestId) {
		Session session = null;
		Groups group = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session
					.getNamedQuery("Contestgroup.checkIndividual");
			query.setParameter("userId", userId);
			query.setParameter("contestId", contestId);

			group = (Groups) query.getSingleResult();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return group;

	}

	public List<Submission> checkGroupSolution(int groupId) {
		Session session = null;
		List<Submission> submission = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session
					.getNamedQuery("Submission.getGroupSubmission");
			query.setParameter("groupId", groupId);

			submission = (List<Submission>) query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return submission;

	}

	public List<Contest> getAllContests() {
		Session session = null;
		List<Contest> contests = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("Contest.allContest");

			contests = (List<Contest>) query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return contests;

	}

	public int getContestPoints(int contestId) {

		Session session = null;
		int contestPoints = 0;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("Problems.contestPoints");
			query.setParameter("contestID", contestId);

			contestPoints = (int) (long) query.getSingleResult();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return contestPoints;

	}

	public boolean checkIfRegistered(int userId, int contestId,
			String contestType) {

		Session session = null;
		int size = 0;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = null;
			if (contestType.equals("Individual")) {
				query = session.getNamedQuery("ContestUser.check");
				query.setParameter("contestId", contestId);
				query.setParameter("userId", userId);

			} else {
				query = session.getNamedQuery("Contestgroup.checkIndividual");
				query.setParameter("userId", userId);
				query.setParameter("contestId", contestId);
			}
			size = query.getResultList().size();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return size > 0;

	}

	public User getUser(int userId) {

		Session session = null;
		User user = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("User.getUser");
			query.setParameter("userId", userId);

			user = (User) query.getSingleResult();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return user;

	}

	public Contest getContest(int contestId) {

		Session session = null;
		Contest contest = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("Contest.getContest");
			query.setParameter("contestId", contestId);

			contest = (Contest) query.getSingleResult();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return contest;

	}

	public void insertIndividualUser(int userId, int contestId) {
		Session session = null;
		try {
			User user = getUser(userId);
			Contest contest = getContest(contestId);
			ContestUserId contestUserId = new ContestUserId(contestId, userId);

			session = sessionFactory.openSession();
			session.beginTransaction();
			ContestUser contestUser = new ContestUser(contestUserId, contest,
					user);

			session.save(contestUser);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public void insertSchool(School school) {

		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			session.save(school);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public Country getCountry(int countryId) {

		Session session = null;
		Country country = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("Country.getCountry");
			query.setParameter("countryId", countryId);

			country = (Country) query.getSingleResult();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return country;

	}

	public School getSchool(int schoolId) {

		Session session = null;
		School school = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("School.getSchool");
			query.setParameter("schoolId", schoolId);

			school = (School) query.getSingleResult();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return school;

	}

	public boolean updateUserProfile(String fname, String lname, String email,
			int countryId, String schoolId, String upwd, String schoolCity,
			String schoolName, int userId) {
		Session session = null;
		try {

			// check if school not exist in dropdown
			if (schoolId == null) {
				Country country = getCountry(countryId);
				School school = new School(country, schoolName, schoolCity);
				insertSchool(school);

			}

			User user = getUser(userId);
			user.setFirstName(fname);
			user.setLastName(lname);
			user.setEmail(email);
			user.setSchool(getSchool(Integer.parseInt(schoolId)));
			if (!upwd.equals("")) {
				user.setPassword(upwd);
			}

			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();

			try {

				session.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return true;
	}

	public Problems getProblem(int problemId) {

		Session session = null;
		Problems problem = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("Problems.getProblem");
			query.setParameter("problemId", problemId);

			problem = (Problems) query.getSingleResult();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return problem;

	}

	public String addGroup(String groupName, String user1, String user2,
			String contestId) {
		Session session = null;
		boolean status = false;
		if (user1.equals(user2)) {
			return "same users";
		}

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query1 = session.getNamedQuery("User.getUserByUserName");
			query1.setParameter("userHandle", user1);
			Query query2 = session.getNamedQuery("User.getUserByUserName");
			query2.setParameter("userHandle", user2);

			Set<User> users = new HashSet<User>();
			users.add((User) query1.getSingleResult());
			boolean statusUser = false;
			User userObj2 = null;

			userObj2 = (User) query2.getSingleResult();
			users.add(userObj2);
			statusUser = true;

			if (!statusUser) {
				if (session != null) {
					session.close();
				}
				return "user does not exist";
			}

			// check if user is already registered for group competition

			Query checkUserRegistered = session
					.getNamedQuery("Contestgroup.checkIndividual");
			checkUserRegistered.setParameter("contestId",
					Integer.parseInt(contestId));
			checkUserRegistered.setParameter("userId", userObj2.getUserId());

			int size = checkUserRegistered.getResultList().size();
			if (size > 0) {
				if (session != null) {
					session.close();
				}
				return "user participated";
			}

			Groups newGroup = new Groups(groupName, null, null, users);
			status = false;

			session.save(newGroup);
			session.getTransaction().commit();
			session.beginTransaction();

			Query queryContest = session.getNamedQuery("Contest.getContest");
			queryContest.setParameter("contestId",
					Integer.parseInt(contestId));

			Contest contest = (Contest) queryContest.getSingleResult();

			Query queryGroup = session.getNamedQuery("Groups.getGroup");
			queryGroup.setParameter("groupName", groupName);
			Groups group = (Groups) queryGroup.getSingleResult();

			ContestgroupId cgi = new ContestgroupId(
					Integer.parseInt(contestId), group.getGroupId());
			Contestgroup cg = new Contestgroup(cgi, contest, group, 0);
			session.save(cg);
			status = true;
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		if (!status) {
			return "exist";
		} else {
			return "not exist";
		}
	}

	public Testcases getTestcaseData(int testcaseId) {
		Session session = null;
		Testcases testcase = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("Testcases.getData");
			query.setParameter("testcaseId", testcaseId);

			testcase = (Testcases) query.getSingleResult();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return testcase;

	}

	public List<Testcases> getTestcaseInputData(String testcaseType,
			int problemId) {
		Session session = null;
		List<Testcases> testcases = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("Testcases.getInputData");
			query.setParameter("testcaseType", testcaseType);
			query.setParameter("problemId", problemId);

			testcases = (List<Testcases>) query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return testcases;

	}

	public Groups getGroupDetails(int id, int contestId, String idType) {
		Query query = null;
		Session session = null;
		Groups groups = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			if (idType.equals("groupId")) {
				query = session.getNamedQuery("Groups.getGroupUsers");
				query.setParameter("groupId", id);
			} else {
				query = session.getNamedQuery("Groups.getUsersGroup");
				query.setParameter("userId", id);
				query.setParameter("contestId", contestId);
			}

			groups = (Groups) query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return groups;

	}

	public void increasePoints(int userId, int successPoints) {
		User user = getUser(userId);
		int userPoints = user.getPoints() + successPoints;
		user.setPoints(userPoints);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public void giveAward(int userId) {
		User user = getUser(userId);
		int userPoints = user.getPoints();
		Awards award = new Awards();
		if (userPoints >= 500) {
			award.setTitleId(3);
		} else if (userPoints >= 200) {
			award.setTitleId(2);
		} else {
			award.setTitleId(1);
		}
		user.setAwards(award);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		// return user;

	}

	public void insertSolutionSubmit(String problemId, Boolean accepted,
			String message, int userId) {

		Session session = null;
		try {
			session = sessionFactory.openSession();

			// start here
			Problems problem = getProblem(Integer.parseInt(problemId));
			int problemPoints = problem.getProblemPoints();

			System.out.println(accepted);
			User userData = getUser(userId);
			session.beginTransaction();
			Submission submission = null;
			Query query = session
					.getNamedQuery("Submission.getUserProblemSubmission");
			query.setParameter("userId", userId);
			query.setParameter("problemId", Integer.parseInt(problemId));
			boolean submissionExists = query.getResultList().size() > 0;
			Submission oldsubmission = null;
			if (submissionExists) {
				oldsubmission = (Submission) query.getSingleResult();
			}
			if (accepted) {
				if (!submissionExists)
					submission = new Submission(problem, userData, true,
							message, problemPoints);
				else {
					submission = oldsubmission;
					submission.setAccepted(true);
					submission.setMessage(message);
					submission.setPoints(problemPoints);
				}
				if (!submissionExists || !oldsubmission.isAccepted())
					increasePoints(userId, problemPoints);
			} else {

				// Solution is not accepted. Now check if user has already
				// successfully solved this problem before.

				if (!submissionExists) {
					submission = new Submission(problem, userData, false,
							message, 0);
				} else {
					if (session != null) {
						session.close();
					}
					return;
				}

			}

			session.saveOrUpdate(submission);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		// end here

	}

	public void insertCompetitionSolution(int contestId, String problemId,
			Boolean accepted, String message, int userId) {

		Session session = null;
		try {
			session = sessionFactory.openSession();

			// start here
			Problems problem = getProblem(Integer.parseInt(problemId));
			int problemPoints = problem.getProblemPoints();
			User userData = getUser(userId);
			Contest contest = getContest(contestId);
			session.beginTransaction();
			Submission submission = null;
			Query query = session
					.getNamedQuery("Submission.getCompetitionSubmission");
			query.setParameter("userId", userId);
			query.setParameter("problemId", Integer.parseInt(problemId));
			query.setParameter("contestId", contestId);
			if (accepted) {

				submission = new Submission(contest, null, problem, userData,
						accepted, message, problemPoints);
				if (query.getResultList().size() > 0) {
					Submission oldSubmission = (Submission) query
							.getSingleResult();
					submission = oldSubmission;
					submission.setAccepted(true);
					submission.setMessage(message);
					submission.setPoints(problemPoints);
					session.update(submission);
					if (!oldSubmission.isAccepted())
						increasePoints(userId, problemPoints);
				} else {
					session.save(submission);
					increasePoints(userId, problemPoints);
				}

			} else {

				// Solution is not accepted. Now check if user has already
				// successfully solved this problem before.

				if (query.getResultList().size() <= 0) {
					submission = new Submission(contest, null, problem,
							userData, accepted, message, 0);
					session.save(submission);
				}

			}

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		// end here

	}

	public void insertGroupCompetitionSolution(int contestId, String problemId,
			Boolean accepted, String message, int groupId) {

		Session session = null;
		try {
			session = sessionFactory.openSession();

			// start here

			// get each user from group
			Groups groups = getGroupDetails(groupId, contestId, "groupId");

			Problems problem = getProblem(Integer.parseInt(problemId));
			int problemPoints = problem.getProblemPoints();
			Contest contest = getContest(contestId);

			session.beginTransaction();
			Submission submission = null;
			Query query = session.getNamedQuery(
					"Submission.getGroupContestProblemSubmission");

			query.setParameter("problemId", Integer.parseInt(problemId));
			query.setParameter("contestId", contestId);
			query.setParameter("groupId", groupId);

			if (accepted) {
				submission = new Submission(contest, groups, problem, null,
						accepted, message, problemPoints);
				if (query.getResultList().size() > 0) {
					Submission oldSubmission = (Submission) query
							.getSingleResult();
					submission = oldSubmission;
					submission.setAccepted(true);
					submission.setMessage(message);
					submission.setPoints(problemPoints);
					session.update(submission);
					if (!oldSubmission.isAccepted()) {
						for (User u : groups.getUsers()) {
							increasePoints(u.getUserId(), problemPoints);
						}
					}
				} else {
					session.save(submission);
					for (User u : groups.getUsers()) {
						increasePoints(u.getUserId(), problemPoints);
					}
				}

			} else {

				// Solution is not accepted. Now check if user has already
				// successfully solved this problem before.

				if (query.getResultList().size() <= 0) {
					submission = new Submission(contest, groups, problem, null,
							accepted, message, 0);
					session.save(submission);
				}

			}

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		// end here

	}

	public void insertQuestion(int userId, String userQuestion) {
		User user = getUser(userId);

		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Forumquestion question = new Forumquestion(user, userQuestion);
			session.save(question);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public void insertAnswer(int userId, String userAnswer, int questionId) {
		User user = getUser(userId);
		ForumanswerId answerId = new ForumanswerId(questionId, userAnswer,
				userId);
		Forumquestion question = getQuestion(questionId);

		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Forumanswer answer = new Forumanswer(answerId, question, user);

			session.save(answer);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public Forumquestion getQuestion(int questionId) {

		Session session = null;
		Forumquestion question = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("Forumquestion.getQuestion");
			query.setParameter("questionId", questionId);

			question = (Forumquestion) query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return question;

	}

}
