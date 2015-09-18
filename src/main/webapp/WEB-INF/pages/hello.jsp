<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Spring MVC+JPA+Security Page!!!</title>
</head>
<body>
<h1>Start page public access!</h1>

Private page - /addPeople
</br>
To login use name:aleks
</br>
password:123
</br>
	<h1>${message}</h1>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
     <h2>Welcome : ${pageContext.request.userPrincipal.name}
     </br>
     <a href="<c:url value="/logout"/> Logout</a></h2>
     </c:if>


</body>
</html>