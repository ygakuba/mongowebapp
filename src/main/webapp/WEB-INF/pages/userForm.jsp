<%@ include file="template/header.jsp" %>

		<script>
			
			$(function() {
		    	$( "#registrationDate" ).datepicker();
		  	});
  		</script>

		<c:if test="${(user ne null)}">
			<h2>Edit User Details</h2>
			<form:form action="/user/${user.id}" method="post">
				<table>
					<tr>
						<td></td>
						<td><input type="hidden" name="id" value="${user.id}"/></td>
					</tr>
					<tr>
						<td>Firstname</td>
						<td><input type="text" name="firstname" value="${user.firstname}" size="35"/></td>
					</tr>
					<tr>
						<td>Lastname</td>
						<td><input type="text" name="lastname" value="${user.lastname}" size="35"/></td>
					</tr>
					<tr>
						<td>Username</td>
						<td><input type="text" name="username" value="${user.username}"/></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="text" name="email" value="${user.email}" size="35"/></td>
					</tr>
					<tr>
						<td>Age</td>
						<td><input type="text" name="age" value="${user.age}" size="5" class="number"/></td>
					</tr>
					<tr>
						<td>Registration Date</td>
						<td><input type="text" name="registrationDate" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${user.registrationDate}'/>" size="12" id="registrationDate" autocomplete="off"/></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Save"/></td>
					</tr>
				</table>
			</form:form>
			<br/>
			<a href="../list">&lt;&lt; Back</a>
		</c:if>
		<c:if test="${(user eq null)}">
			<h2>New User</h2>
			<form:form action="/user" method="post">
				<table>
					<tr>
						<td></td>
						<td><input type="hidden" name="id" value="${user.id}"/></td>
					</tr>
					<tr>
						<td>Firstname</td>
						<td><input type="text" name="firstname" value="${user.firstname}" size="35"/></td>
					</tr>
					<tr>
						<td>Lastname</td>
						<td><input type="text" name="lastname" value="${user.lastname}" size="35"/></td>
					</tr>
					<tr>
						<td>Username</td>
						<td><input type="text" name="username" value="${user.username}"/></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="text" name="email" value="${user.email}" size="35"/></td>
					</tr>
					<tr>
						<td>Age</td>
						<td><input type="text" name="age" value="${user.age}" size="5" style="text-align:right"/></td>
					</tr>
					<tr>
						<td>Registration Date</td>
						<td><input type="text" name="registrationDate" value="${user.registrationDate}" id="registrationDate" size="12" autocomplete="off"/></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Save"/></td>
					</tr>
				</table>
			</form:form>
			<br/>
			<a href="list">&lt;&lt; Back</a>
		</c:if>
<%@ include file="template/footer.jsp" %>