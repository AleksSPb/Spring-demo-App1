<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
  <style>
    .error {
      color: #ff0000;
    }

    .errorblock {
      color: #000;
      background-color: #ffEEEE;
      border: 3px solid #ff0000;
      padding: 8px;
      margin: 16px;
    }
  </style>
</head>

<body>
<h2>Форма ввода данных</h2>

<form:form method="POST" commandName="people" action="/check">
  <form:errors path="*" cssClass="errorblock" element="div" />
  <table>
    <tr>
      <td>Имя :</td>
      <td><form:input path="name" /></td>
      <td><form:errors path="name" cssClass="error" /></td>
    </tr>
    <tr>
      <td>Возраст :</td>
      <td><form:input path="age" /></td>
      <td><form:errors path="age" cssClass="error" /></td>
    </tr>
    <tr>
      <td colspan="3"><input type="submit" /></td>
    </tr>
  </table>
</form:form>

</body>
</html>