<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>Usuarios</h1>
<table>
    <thead><tr><td>ID</td><td>Name</td></tr></thead>
    <tbody>
        <c:forEach var="usuario" items="${usuarios}">
        <tr><td>${usuario.id}</td><td>${usuario.nome}</td></tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>