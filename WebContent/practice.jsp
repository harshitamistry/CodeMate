<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="header.jsp"%>
<%@include file="dbConnectJava.jsp"%>
<script type="text/javascript" src="_js/practice.js"></script>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active practiceTab"><a
					href="#easy" aria-controls="easy" role="tab" data-toggle="tab">easy</a></li>
				<li role="presentation" class="practiceTab"><a href="#medium"
					aria-controls="medium" role="tab" data-toggle="tab">medium</a></li>
				<li role="presentation" class="practiceTab"><a href="#hard"
					aria-controls="hard" role="tab" data-toggle="tab">hard</a></li>

			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
				<%
					String[] types = { "easy", "medium", "hard" };
					for (String type : types) {
						if (type.equals("easy")) {
				%><div role="tabpanel" class="tab-pane active" id="<%=type%>">
					<%
						} else {
					%><div role="tabpanel" class="tab-pane" id="<%=type%>">
						<%
							}
						%>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Problem ID</th>
									<th>Problem Title</th>
									<th>Result</th>
									<th>Points</th>
									<th>Points Earned</th>
								</tr>
							</thead>


							<%
								String sql = "SELECT * FROM problems WHERE Complexity = '"
											+ type + "' and ProblemType='Practice'";
									try {
										ResultSet rs = stmt.executeQuery(sql);
										int problemNumber = 0;
										while (rs.next()) {
											problemNumber += 1;
											 System.out.println(problemNumber); 

							%>
							<tr>
								<td><%=problemNumber%></td>
							    <td><a href="practiceProblem.jsp?id=<%=rs.getInt("ProblemID")%>"><%=rs.getString("ProblemName")%></a></td>
								
								<%
									int problemPoints = rs.getInt("ProblemPoints");
												String sqlProblem = "SELECT * FROM submission WHERE UserID ="
														+ session.getAttribute("userId")
														+ " AND ProblemID=" + rs.getInt("ProblemID");
												Statement stmtProb=conn.createStatement();

												ResultSet rsProblem = stmtProb.executeQuery(sqlProblem);
												if (rsProblem.next()) {
													System.out.print("solved by user");
								%>
								<td>
									<%
										if (rsProblem.getBoolean("Accepted")) {
									%>Successful<%
										} else {
									%> Unsuccessful <%
										}
									%>
								</td>
								<td><%=problemPoints%></td>
								<td><%=rsProblem.getInt("Points")%></td>
								<%
												} else {
													System.out.print("not solved by user");
								%>
								<td>Not Solved</td>
								<td><%=problemPoints%></td>
								<td>0</td>

								<%
									}
												rsProblem.close();
								%>
							</tr>
							<%
								}
										rs.close();
									} catch (Exception e) {
										e.printStackTrace();
									}
							%>
						</table>
					</div>
					<%
						}
					%>


				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>