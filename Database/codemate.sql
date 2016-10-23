-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 23, 2016 at 08:42 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `codemate`
--
CREATE DATABASE IF NOT EXISTS `codemate` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `codemate`;

-- --------------------------------------------------------

--
-- Table structure for table `awards`
--

CREATE TABLE `awards` (
  `TitleID` int(11) NOT NULL,
  `Title` varchar(255) NOT NULL,
  `BadgePicture` longblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `awards`:
--

-- --------------------------------------------------------

--
-- Table structure for table `calendar`
--

CREATE TABLE `calendar` (
  `EventStartDate` datetime NOT NULL,
  `EventEndDate` datetime NOT NULL,
  `EventName` varchar(255) NOT NULL,
  `UserID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `calendar`:
--   `UserID`
--       `user` -> `UserID`
--

-- --------------------------------------------------------

--
-- Table structure for table `contest`
--

CREATE TABLE `contest` (
  `ContestID` int(11) NOT NULL,
  `ContestName` varchar(255) NOT NULL,
  `ContestStartDate` datetime NOT NULL,
  `ContestEndDate` datetime NOT NULL,
  `ContestType` enum('Individual','Group') NOT NULL,
  `MaxGroupMembers` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `contest`:
--

-- --------------------------------------------------------

--
-- Table structure for table `contestgroup`
--

CREATE TABLE `contestgroup` (
  `ContestID` int(11) NOT NULL,
  `GroupID` int(11) NOT NULL,
  `Points` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `contestgroup`:
--   `ContestID`
--       `contest` -> `ContestID`
--   `GroupID`
--       `groups` -> `GroupID`
--

-- --------------------------------------------------------

--
-- Table structure for table `contestproblem`
--

CREATE TABLE `contestproblem` (
  `ProblemID` int(11) NOT NULL,
  `ContestID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `contestproblem`:
--   `ProblemID`
--       `problems` -> `ProblemID`
--   `ContestID`
--       `contest` -> `ContestID`
--

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE `country` (
  `CountryID` int(11) NOT NULL,
  `CountryName` varchar(255) NOT NULL,
  `CountryFlag` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `country`:
--

-- --------------------------------------------------------

--
-- Table structure for table `exerciseproblem`
--

CREATE TABLE `exerciseproblem` (
  `ProblemID` int(11) NOT NULL,
  `TutorialID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `exerciseproblem`:
--   `TutorialID`
--       `tutorial` -> `TutorialID`
--   `ProblemID`
--       `problems` -> `ProblemID`
--

-- --------------------------------------------------------

--
-- Table structure for table `forumanswer`
--

CREATE TABLE `forumanswer` (
  `QuestionID` int(11) NOT NULL,
  `Answer` text NOT NULL,
  `UserID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `forumanswer`:
--   `QuestionID`
--       `forumquestion` -> `QuestionID`
--   `UserID`
--       `user` -> `UserID`
--

-- --------------------------------------------------------

--
-- Table structure for table `forumquestion`
--

CREATE TABLE `forumquestion` (
  `QuestionID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `Question` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `forumquestion`:
--   `UserID`
--       `user` -> `UserID`
--

-- --------------------------------------------------------

--
-- Table structure for table `groupmember`
--

CREATE TABLE `groupmember` (
  `GroupID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `groupmember`:
--   `GroupID`
--       `groups` -> `GroupID`
--   `UserID`
--       `user` -> `UserID`
--

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

CREATE TABLE `groups` (
  `GroupID` int(11) NOT NULL,
  `GroupName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `groups`:
--

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `ImageID` int(11) NOT NULL,
  `Image` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `images`:
--

-- --------------------------------------------------------

--
-- Table structure for table `problemresult`
--

CREATE TABLE `problemresult` (
  `ProblemID` int(11) NOT NULL,
  `Result` text NOT NULL,
  `Visibility` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `problemresult`:
--   `ProblemID`
--       `problems` -> `ProblemID`
--

-- --------------------------------------------------------

--
-- Table structure for table `problems`
--

CREATE TABLE `problems` (
  `ProblemID` int(11) NOT NULL,
  `ProblemName` varchar(255) NOT NULL,
  `ProblemType` enum('Exercise','Contest','Practice') NOT NULL DEFAULT 'Exercise',
  `ProblemQuestion` text NOT NULL,
  `ProblemPoints` int(11) NOT NULL,
  `Hint` text NOT NULL,
  `Solution` text NOT NULL,
  `Complexity` enum('easy','medium','hard') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `problems`:
--

-- --------------------------------------------------------

--
-- Table structure for table `school`
--

CREATE TABLE `school` (
  `SchoolID` int(11) NOT NULL,
  `SchoolName` varchar(255) NOT NULL,
  `SchoolCity` varchar(255) NOT NULL,
  `CountryID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `school`:
--   `CountryID`
--       `country` -> `CountryID`
--

-- --------------------------------------------------------

--
-- Table structure for table `submission`
--

CREATE TABLE `submission` (
  `SubmissionID` int(11) NOT NULL,
  `Accepted` tinyint(1) NOT NULL,
  `Message` varchar(255) NOT NULL,
  `ContestID` int(11) DEFAULT NULL,
  `ProblemID` int(11) NOT NULL,
  `GroupID` int(11) DEFAULT NULL,
  `UserID` int(11) NOT NULL,
  `Points` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `submission`:
--   `ContestID`
--       `contest` -> `ContestID`
--   `ProblemID`
--       `problems` -> `ProblemID`
--   `GroupID`
--       `groups` -> `GroupID`
--   `UserID`
--       `user` -> `UserID`
--

-- --------------------------------------------------------

--
-- Table structure for table `testcases`
--

CREATE TABLE `testcases` (
  `testcaseID` int(11) NOT NULL,
  `problemID` int(11) NOT NULL,
  `input` text NOT NULL,
  `output` text NOT NULL,
  `type` enum('test','submit') NOT NULL DEFAULT 'test'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `testcases`:
--   `problemID`
--       `problems` -> `ProblemID`
--

-- --------------------------------------------------------

--
-- Table structure for table `tutorial`
--

CREATE TABLE `tutorial` (
  `TutorialID` int(11) NOT NULL,
  `TutorialName` varchar(255) NOT NULL,
  `TutorialContent` text NOT NULL,
  `TutorialDescription` text,
  `TutorialType` enum('Program','Text','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `tutorial`:
--

-- --------------------------------------------------------

--
-- Table structure for table `tutorialimages`
--

CREATE TABLE `tutorialimages` (
  `tutorialID` int(11) NOT NULL,
  `imageid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `tutorialimages`:
--   `tutorialID`
--       `tutorial` -> `TutorialID`
--   `imageid`
--       `images` -> `ImageID`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserID` int(11) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `SchoolID` int(11) NOT NULL,
  `UserHandle` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `UserPicture` longblob,
  `UserPhone` varchar(15) DEFAULT NULL,
  `UserBirthDate` date DEFAULT NULL,
  `Points` int(11) NOT NULL DEFAULT '0',
  `TitleID` int(11) NOT NULL DEFAULT '1',
  `TutorialProgressID` int(11) NOT NULL DEFAULT '1',
  `TutorialCompleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `user`:
--   `SchoolID`
--       `school` -> `SchoolID`
--   `TitleID`
--       `awards` -> `TitleID`
--   `TutorialProgressID`
--       `tutorial` -> `TutorialID`
--

--
-- Indexes for dumped tables
--

--
-- Indexes for table `awards`
--
ALTER TABLE `awards`
  ADD PRIMARY KEY (`TitleID`);

--
-- Indexes for table `calendar`
--
ALTER TABLE `calendar`
  ADD KEY `UserID` (`UserID`);

--
-- Indexes for table `contest`
--
ALTER TABLE `contest`
  ADD PRIMARY KEY (`ContestID`);

--
-- Indexes for table `contestgroup`
--
ALTER TABLE `contestgroup`
  ADD PRIMARY KEY (`ContestID`,`GroupID`),
  ADD KEY `GroupID` (`GroupID`);

--
-- Indexes for table `contestproblem`
--
ALTER TABLE `contestproblem`
  ADD PRIMARY KEY (`ProblemID`,`ContestID`),
  ADD KEY `ContestID` (`ContestID`);

--
-- Indexes for table `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`CountryID`);

--
-- Indexes for table `exerciseproblem`
--
ALTER TABLE `exerciseproblem`
  ADD PRIMARY KEY (`ProblemID`,`TutorialID`),
  ADD KEY `TutorialID` (`TutorialID`);

--
-- Indexes for table `forumanswer`
--
ALTER TABLE `forumanswer`
  ADD KEY `QuestionID` (`QuestionID`),
  ADD KEY `UserID` (`UserID`);

--
-- Indexes for table `forumquestion`
--
ALTER TABLE `forumquestion`
  ADD PRIMARY KEY (`QuestionID`),
  ADD KEY `UserID` (`UserID`);

--
-- Indexes for table `groupmember`
--
ALTER TABLE `groupmember`
  ADD PRIMARY KEY (`GroupID`,`UserID`),
  ADD KEY `UserID` (`UserID`);

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`GroupID`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`ImageID`);

--
-- Indexes for table `problemresult`
--
ALTER TABLE `problemresult`
  ADD KEY `ProblemID` (`ProblemID`);

--
-- Indexes for table `problems`
--
ALTER TABLE `problems`
  ADD PRIMARY KEY (`ProblemID`);

--
-- Indexes for table `school`
--
ALTER TABLE `school`
  ADD PRIMARY KEY (`SchoolID`),
  ADD KEY `CountryID` (`CountryID`);

--
-- Indexes for table `submission`
--
ALTER TABLE `submission`
  ADD PRIMARY KEY (`SubmissionID`),
  ADD KEY `ContestID` (`ContestID`),
  ADD KEY `ProblemID` (`ProblemID`),
  ADD KEY `GroupID` (`GroupID`),
  ADD KEY `IndividualContestantID` (`UserID`);

--
-- Indexes for table `testcases`
--
ALTER TABLE `testcases`
  ADD PRIMARY KEY (`testcaseID`),
  ADD KEY `problemID` (`problemID`);

--
-- Indexes for table `tutorial`
--
ALTER TABLE `tutorial`
  ADD PRIMARY KEY (`TutorialID`);

--
-- Indexes for table `tutorialimages`
--
ALTER TABLE `tutorialimages`
  ADD PRIMARY KEY (`tutorialID`,`imageid`),
  ADD KEY `imageid` (`imageid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`),
  ADD KEY `SchoolID` (`SchoolID`),
  ADD KEY `TitleID` (`TitleID`),
  ADD KEY `TutorialProgressID` (`TutorialProgressID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `awards`
--
ALTER TABLE `awards`
  MODIFY `TitleID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `contest`
--
ALTER TABLE `contest`
  MODIFY `ContestID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `country`
--
ALTER TABLE `country`
  MODIFY `CountryID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=250;
--
-- AUTO_INCREMENT for table `forumquestion`
--
ALTER TABLE `forumquestion`
  MODIFY `QuestionID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `groups`
--
ALTER TABLE `groups`
  MODIFY `GroupID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `ImageID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `problems`
--
ALTER TABLE `problems`
  MODIFY `ProblemID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `school`
--
ALTER TABLE `school`
  MODIFY `SchoolID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `submission`
--
ALTER TABLE `submission`
  MODIFY `SubmissionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `testcases`
--
ALTER TABLE `testcases`
  MODIFY `testcaseID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT for table `tutorial`
--
ALTER TABLE `tutorial`
  MODIFY `TutorialID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `calendar`
--
ALTER TABLE `calendar`
  ADD CONSTRAINT `calendar_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON UPDATE CASCADE;

--
-- Constraints for table `contestgroup`
--
ALTER TABLE `contestgroup`
  ADD CONSTRAINT `contestgroup_ibfk_1` FOREIGN KEY (`ContestID`) REFERENCES `contest` (`ContestID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `contestgroup_ibfk_2` FOREIGN KEY (`GroupID`) REFERENCES `groups` (`GroupID`) ON UPDATE CASCADE;

--
-- Constraints for table `contestproblem`
--
ALTER TABLE `contestproblem`
  ADD CONSTRAINT `contestproblem_ibfk_1` FOREIGN KEY (`ProblemID`) REFERENCES `problems` (`ProblemID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `contestproblem_ibfk_2` FOREIGN KEY (`ContestID`) REFERENCES `contest` (`ContestID`) ON UPDATE CASCADE;

--
-- Constraints for table `exerciseproblem`
--
ALTER TABLE `exerciseproblem`
  ADD CONSTRAINT `exerciseproblem_ibfk_1` FOREIGN KEY (`TutorialID`) REFERENCES `tutorial` (`TutorialID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `exerciseproblem_ibfk_2` FOREIGN KEY (`ProblemID`) REFERENCES `problems` (`ProblemID`);

--
-- Constraints for table `forumanswer`
--
ALTER TABLE `forumanswer`
  ADD CONSTRAINT `forumanswer_ibfk_1` FOREIGN KEY (`QuestionID`) REFERENCES `forumquestion` (`QuestionID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `forumanswer_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON UPDATE CASCADE;

--
-- Constraints for table `forumquestion`
--
ALTER TABLE `forumquestion`
  ADD CONSTRAINT `forumquestion_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON UPDATE CASCADE;

--
-- Constraints for table `groupmember`
--
ALTER TABLE `groupmember`
  ADD CONSTRAINT `groupmember_ibfk_1` FOREIGN KEY (`GroupID`) REFERENCES `groups` (`GroupID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `groupmember_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON UPDATE CASCADE;

--
-- Constraints for table `problemresult`
--
ALTER TABLE `problemresult`
  ADD CONSTRAINT `problemresult_ibfk_1` FOREIGN KEY (`ProblemID`) REFERENCES `problems` (`ProblemID`) ON UPDATE CASCADE;

--
-- Constraints for table `school`
--
ALTER TABLE `school`
  ADD CONSTRAINT `school_fk_1` FOREIGN KEY (`CountryID`) REFERENCES `country` (`CountryID`) ON UPDATE CASCADE;

--
-- Constraints for table `submission`
--
ALTER TABLE `submission`
  ADD CONSTRAINT `submission_ibfk_1` FOREIGN KEY (`ContestID`) REFERENCES `contest` (`ContestID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `submission_ibfk_2` FOREIGN KEY (`ProblemID`) REFERENCES `problems` (`ProblemID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `submission_ibfk_3` FOREIGN KEY (`GroupID`) REFERENCES `groups` (`GroupID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `submission_ibfk_4` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON UPDATE CASCADE;

--
-- Constraints for table `testcases`
--
ALTER TABLE `testcases`
  ADD CONSTRAINT `testcases_ibfk_1` FOREIGN KEY (`problemID`) REFERENCES `problems` (`ProblemID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tutorialimages`
--
ALTER TABLE `tutorialimages`
  ADD CONSTRAINT `tutorialimages_ibfk_1` FOREIGN KEY (`tutorialID`) REFERENCES `tutorial` (`TutorialID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tutorialimages_ibfk_2` FOREIGN KEY (`imageid`) REFERENCES `images` (`ImageID`) ON UPDATE CASCADE;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`SchoolID`) REFERENCES `school` (`SchoolID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `user_ibfk_2` FOREIGN KEY (`TitleID`) REFERENCES `awards` (`TitleID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `user_ibfk_3` FOREIGN KEY (`TutorialProgressID`) REFERENCES `tutorial` (`TutorialID`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
