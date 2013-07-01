<%@ include file="template/header.jsp" %>

		<script type="text/javascript">
			function deleteUser(url){
			
			    if (confirm("Are you sure you want to delete this user ?")) {
			        var request = new XMLHttpRequest();
			        request.open('DELETE', url, true);
			        request.onreadystatechange = function () { window.location.reload(true); };
			        request.send(null);
			    }
			}
		</script>

		<h2>List of users</h2>
		
		<table>
			<tr>
				<th class="left">#.</th>
				<th class="left">Firstname</th>
				<th class="left">Lastname</th>
				<th class="left">Username</th>
				<th class="left">Email</th>
				<th class="right">Age</th>
				<th class="left">Registration Date</th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${users}" var="user" varStatus="status">
				<tr>
					<td>${status.count}.</td>
					<td>${user.firstname}</td>
					<td>${user.lastname}</td>
					<td>${user.username}</td>
					<td>${user.email}</td>
					<td class="number">${user.age}</td>
					<td><fmt:formatDate pattern="yyyy-MMM-dd" value="${user.registrationDate}"/></td>
					<td><a href="user/${user.id}">View</a></td>
					<td><a href="${pageContext.request.contextPath}/rest/user/${user.id}">Get XML</a></td>
					<td><a href="javascript:deleteUser('<c:out value="user/${user.id}"/>')">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
		<br/>
		<a href="user">New User</a>
		
<%@ include file="template/footer.jsp" %>
