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

	/**
	 * Gets list of countries from the database.
	 * 
	 * @return list of countries from the database
	 */
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

	/**
	 * Get users whose scores are in top ten by using named query. In the user
	 * bean this named query orders users in the descending order by their
	 * scores
	 * 
	 * 
	 * @return list of top ten users.
	 */
	public List<User> getTopTen() {
		Session session = null;
		List<User> userList = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("User.topTen");
			// query.setParameter("department", department);
			query.setMaxResults(10);

			userList = (List<User>) query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return userList;

	}

	/**
	 * Responsible to get top ten users from a specific country. In the user
	 * bean named query orders users of a specific country in the descending
	 * order by their points
	 * 
	 * @param countryId
	 *            id of a country for which it gets top ten users
	 * @return list of users
	 */
	public List<User> getTopTenCountry(int countryId) {
		Session session = null;
		List<User> userList = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("User.topTenCountry");
			query.setParameter("countryId", countryId);
			query.setMaxResults(10);

			userList = (List<User>) query.getResultList();

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return userList;

	}

	/**
	 * Responsible to get top ten users from a specific school. In the user bean
	 * named query orders users of a specific school in the descending order by
	 * their points
	 * 
	 * @param schoolId
	 *            id of a school for which it gets top ten users
	 * @return list of users
	 */
	public List<User> getTopTenSchool(int schoolId) {
		Session session = null;
		List<User> userBySchoolList = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("User.topTenSchool");
			query.setParameter("schoolId", schoolId);
			query.setMaxResults(10);

			userBySchoolList = (List<User>) query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return userBySchoolList;

	}

	/**
	 * Get the flag image of user's country.
	 * 
	 * @param userId
	 *            id of user to get flag
	 * @return flag image
	 */
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

	/**
	 * Responsible to get all the schools of country Canada from database.
	 * 
	 * @return list of schools
	 */
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

	/**
	 * Responsible to get all the forum questions from database.
	 * 
	 * @return list of questions
	 */
	public List<Forumquestion> getQuestionList() {
		Session session = null;
		List<Forumquestion> questionList = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			// String sql = "select s from School s JOIN s.country c where
			// c.countryName = 'Canada'";
			// Query query = session.createQuery(sql);
			Query query = session.getNamedQuery("Forumquestion.allQuestions");

			questionList = (List<Forumquestion>) query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return questionList;
	}

	/**
	 * Responsible to get list of Forum-Answers of a specific Forum-Question
	 * from the database
	 * 
	 * @param questionId
	 *            id of a Forum-Question
	 * @return list of Forum-Answers of a Forum-Question identified by
	 *         questionId
	 */
	public List<Forumanswer> getAnswerList(int questionId) {
		Session session = null;
		List<Forumanswer> answerList = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			// String sql = "select s from School s JOIN s.country c where
			// c.countryName = 'Canada'";
			// Query query = session.createQuery(sql);
			Query query = session.getNamedQuery("Forumanswer.allAnswers");
			query.setParameter("questionId", questionId);
			answerList = (List<Forumanswer>) query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return answerList;
	}

	/**
	 * Responsible to get list of Forum-Questions which are matching with the
	 * specified keyword in the parameter.
	 * 
	 * @param keyword
	 *            a String to search for Forum-Questions
	 * @return list of questions containing the keyword
	 */
	public List<Forumquestion> searchQuestionList(String keyword) {
		Session session = null;
		List<Forumquestion> questionList = null;
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
			questionList = (List<Forumquestion>) query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}

		return questionList;
	}

	/**
	 * Responsible to get all the tutorials from the database
	 * 
	 * @return list of tutorials
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

	/**
	 * Responsible to get tutorial details identified by tutorialId
	 * 
	 * @param tutorialId
	 *            id of a tutorial to get its infor
	 * @return tutorial object
	 */
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

	/**
	 * Responsible to check whether entered email already exist for the
	 * different user in the database
	 * 
	 * @param userEmail
	 *            email address to check
	 * @param userHandle
	 *            user name of a current user
	 * @return true if email does not already exist for another user, false
	 *         otherwise
	 */
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

	/**
	 * Get exercise problem from the tutorial, which is identified by tutorial
	 * id.
	 * 
	 * @param tutorialId
	 *            id of a tutorial
	 * @return problems object
	 */
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
	 * Responsible to get user details identified by user id
	 * 
	 * @param uid
	 *            id of a current user
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

	/**
	 * This function gets the maximum tutorial id from the tutorials table.
	 * Maximum tutorial id is matched with the current tutorial id. If current
	 * tutorial is the last tutorial then return finish, otherwise incomplete
	 * 
	 * @param tutId
	 *            id of the current tutorial
	 * @return tutorial is finished or not
	 */
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

		if (lastTutorial == tutId) {
			return "finish";
		} else {
			return "incomplete";
		}

	}

	/**
	 * Responsible to get list of problems, identified by the problemType i.e.
	 * Easy, Medium or Hard
	 * 
	 * @param problemType
	 *            complexity type of a problem (Easy, Medium, Hard)
	 * @return list of problems of problemType
	 */
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

	/**
	 * Responsible to get list of problems of a specific contest, identified by
	 * contestId
	 * 
	 * @param contestId
	 *            id of a contest
	 * @return list of problems
	 */
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

	/**
	 * Responsible to get submission of a user identified by user id
	 * 
	 * @param userId
	 *            id of a user
	 * @return submission object
	 */
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

	/**
	 * Responsible to get the contest group of a specific user for a specific
	 * Group Contest, identified by user id and contest id
	 * 
	 * @param userId
	 *            id of a user
	 * @param contestId
	 *            id of a contest
	 * @return contest group
	 */
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

	/**
	 * Responsible to get the submission details of a group, identified by group
	 * id
	 * 
	 * @param groupId
	 *            id of a group
	 * @return group submission
	 */
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

	/**
	 * Responsible to get list of all contests
	 * 
	 * @return list of contests
	 */
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

	/**
	 * Responsible to get total of problem-points for a specific contest,
	 * identified by contest id
	 * 
	 * @param contestId
	 *            id of a contest
	 * @return total points of a contest
	 */
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

	/**
	 * Responsible to check if a user(identified by user id)is already
	 * registered in a contest(identified by contest id and contest type i.e.
	 * Individual Contest or Group Contest) or not
	 * 
	 * @param userId
	 *            id of a user
	 * @param contestId
	 *            id of a contest
	 * @param contestType
	 *            type of a contest i.e. Individual or Group
	 * @return true if already registered, false otherwise
	 */
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

	/**
	 * Responsible to get the details of a user, identified by user id
	 * 
	 * @param userId
	 *            id of a user
	 * @return user object
	 */
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

	/**
	 * Responsible to get the details of a contest, identified by contestId
	 * 
	 * @param contestId
	 *            id of a contest
	 * @return contest object
	 */
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

	/**
	 * Responsible to register a user in a specific contest by inserting user
	 * details for a specific contest identified by contestId
	 * 
	 * @param userId
	 *            id of a user
	 * @param contestId
	 *            id of a contest
	 */
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

	/**
	 * Responsible to save a new school in the database
	 * 
	 * @param school
	 *            school object
	 */
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

	/**
	 * Responsible to get details of a Country identified by countryId
	 * 
	 * @param countryId
	 * @return country object
	 */
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

	/**
	 * Responsible to get details of a school identified by schoolId
	 * 
	 * @param schoolId
	 *            id of a school
	 * @return school object
	 */
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

	/**
	 * Responsible to update the user profile identified by userId with
	 * specified values in the parameter list in the user table in the database
	 * 
	 * @param fname
	 *            first name of a user
	 * @param lname
	 *            last name of a user
	 * @param email
	 *            email address of a user
	 * @param countryId
	 *            id of a country
	 * @param schoolId
	 *            id of a school
	 * @param upwd
	 *            user password
	 * @param schoolCity
	 *            school city
	 * @param schoolName
	 *            school name
	 * @param userId
	 *            id of a user
	 * @return true if values successfully updated, false otherwise
	 */
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

	/**
	 * Responsible to get the details of a Problem identified by problemId
	 * 
	 * @param problemId
	 *            id of a problem
	 * @return problem object
	 */
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

	/**
	 * Responsible to create and add a users group for a specific conest. This
	 * function does all the validation checking like if the specified user does
	 * not exist, or if the specified user exist but is registered with another
	 * group etc
	 * 
	 * @param groupName
	 *            name of a user group
	 * @param user1
	 *            userHandle of a user
	 * @param user2
	 *            userHandle of another user in a group
	 * @param contestId
	 *            id of a contest to register group
	 * @return if there is a problem with registering another user in a group,
	 *         it returns a particular response as a String
	 */
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

	/**
	 * Responsible to get the details of a testcase identified by testcaseId
	 * 
	 * @param testcaseId
	 *            id of a testcase
	 * @return testcase object
	 */
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

	/**
	 * Responsible to get list of testcases identified by problem id and
	 * testcase type
	 * 
	 * @param testcaseType
	 *            type of a testcase
	 * @param problemId
	 *            id of a problem
	 * @return list of testcases
	 */
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

	/**
	 * Responsible to get the details of a group/user
	 * 
	 * @param id
	 *            id of a group/user
	 * @param contestId
	 *            id of a contest
	 * @param idType
	 *            type of contest
	 * @return details of user/group
	 */
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

	/**
	 * Responsible to increase total points of a user, if the solution for the
	 * problem is successful
	 * 
	 * @param userId
	 *            id of a user
	 * @param successPoints
	 *            points to increase
	 */
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

	/**
	 * Responsible to allocate specific award-title to the user if points are
	 * greater than some range
	 * 
	 * @param userId
	 *            id of a user
	 */
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

	/**
	 * Responsible to insert a solution for practice problems. This function
	 * checks whether, user had already solved a question or not, whether the
	 * solution was successful or not,increase points if the solution was
	 * successful etc
	 * 
	 * @param problemId
	 *            id of a problem for which group has solution
	 * @param accepted
	 *            boolean value if the solution was accepted or not
	 * @param message
	 *            message for a solution
	 * @param userId
	 *            id of a user who solved the problem
	 */
	public void insertSolutionSubmit(String problemId, Boolean accepted,
			String message, int userId) {

		Session session = null;
		try {

			// start here
			Problems problem = getProblem(Integer.parseInt(problemId));
			int problemPoints = problem.getProblemPoints();

			System.out.println(accepted);
			User userData = getUser(userId);
			session = sessionFactory.openSession();

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
				if (!submissionExists) {
					submission = new Submission(problem, userData, true,
							message, problemPoints);
					session.save(submission);
					session.getTransaction().commit();
					session.close();
					increasePoints(userId, problemPoints);

				}

				else {
					submission = oldsubmission;
					submission.setAccepted(true);
					submission.setMessage(message);
					submission.setPoints(problemPoints);
					session.update(submission);
					session.getTransaction().commit();
					session.close();

				}

			} else {

				// Solution is not accepted. Now check if user has already
				// successfully solved this problem before.

				if (!submissionExists) {
					submission = new Submission(problem, userData, false,
							message, 0);
					session.save(submission);
					session.getTransaction().commit();
				} else {
					if (session != null) {
						session.close();
					}
					return;
				}

			}

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

	/**
	 * Responsible to insert a solution for the individual competition. This
	 * function checks whether, user had already solved a question or not,
	 * whether the solution was successful or not,increase points if the
	 * solution was successful etc
	 * 
	 * @param contestId
	 *            id of a group contest
	 * @param problemId
	 *            id of a problem for which group has solution
	 * @param accepted
	 *            boolean value if the solution was accepted or not
	 * @param message
	 *            message for a solution
	 * @param userId
	 *            id of a user who solved the problem
	 */
	public void insertCompetitionSolution(int contestId, String problemId,
			Boolean accepted, String message, int userId) {

		Session session = null;
		try {

			// start here
			Problems problem = getProblem(Integer.parseInt(problemId));
			int problemPoints = problem.getProblemPoints();
			User userData = getUser(userId);
			Contest contest = getContest(contestId);
			session = sessionFactory.openSession();

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
					session.getTransaction().commit();
					session.close();
					if (!oldSubmission.isAccepted()) {

						increasePoints(userId, problemPoints);
					}
				} else {
					session.save(submission);
					session.getTransaction().commit();
					session.close();
					increasePoints(userId, problemPoints);
				}

			} else {

				// Solution is not accepted. Now check if user has already
				// successfully solved this problem before.

				if (query.getResultList().size() <= 0) {
					submission = new Submission(contest, null, problem,
							userData, accepted, message, 0);
					session.save(submission);
					session.getTransaction().commit();
					session.close();
				}

			}

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

	/**
	 * Responsible to insert a solution for the group competition. This function
	 * checks whether, group had already solved a question or not, whether the
	 * solution was successful or not,increase points if the solution was
	 * successful etc
	 * 
	 * @param contestId
	 *            id of a group contest
	 * @param problemId
	 *            id of a problem for which group has solution
	 * @param accepted
	 *            boolean value if the solution was accepted or not
	 * @param message
	 *            message for a solution
	 * @param groupId
	 *            id of a group who solved the problem
	 */
	public void insertGroupCompetitionSolution(int contestId, String problemId,
			Boolean accepted, String message, int groupId) {

		Session session = null;
		try {

			// start here

			// get each user from group
			Groups groups = getGroupDetails(groupId, contestId, "groupId");

			Problems problem = getProblem(Integer.parseInt(problemId));
			int problemPoints = problem.getProblemPoints();
			Contest contest = getContest(contestId);
			session = sessionFactory.openSession();

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
					session.getTransaction().commit();
					session.close();
					if (!oldSubmission.isAccepted()) {
						for (User u : groups.getUsers()) {
							increasePoints(u.getUserId(), problemPoints);
						}
					}
				} else {
					session.save(submission);
					session.getTransaction().commit();
					session.close();
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
					session.getTransaction().commit();
					session.close();
				}

			}

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

	/**
	 * Responsible to insert a new Forum-Question in the database
	 * 
	 * @param userId
	 *            id of a user, who asked the question
	 * @param userQuestion
	 *            question asked by user
	 */
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

	/**
	 * Responsible to insert a new Forum-Answer of a Forum-Question identified
	 * by questionId
	 * 
	 * @param userId
	 *            id of a user, who answered the question
	 * @param userAnswer
	 *            answer given by user
	 * @param questionId
	 *            id of a question
	 */
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

	/**
	 * Responsible to get the details of a Forum Question identified by
	 * questionId
	 * 
	 * @param questionId
	 *            id of a Forum-Question
	 * @return Forumquestion object
	 */
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
