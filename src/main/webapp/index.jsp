<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>ToDoList</title>
</head>
<body>
<div class="card-body">
    <form action="<%=request.getContextPath()%>/addItemServlet" method="get">
        <div class="form-group">
            <label>Дело</label>
            <input required type="text" class="form-control" name="description" placeholder="Укажите дело">
        </div>
        <button type="submit" class="btn btn-primary">Добавить</button>
    </form>
</div>
<div class="container">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/getItems'/>">Все</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/getAllPerformItems'/>">Выполненные</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/getAllNotPerformItems'/>">Открытые</a>
            </li>
        </ul>
    </div>
</div>
<div class="container">
    <div class="container pt-3">
        <div class="row">
            <div class="card" style="width: 100%">
                <div class="card-header">
                    Дела
                </div>
                <div class="card-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Описание</th>
                            <th scope="col">Дата создания</th>
                            <th scope="col">Выполнение</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${items}" var="item">
                            <tr>
                                <td><c:out value="${item.description}"/></td>
                                <td><c:out value="${item.created}"/></td>
                                <td>
                                    <c:if test="${item.done == false}">
                                        <a button type="submit" class="btn btn-warning"
                                           href="<c:url value='/performItemServlet?id=${item.id}'/>">
                                            Выполнить</a>
                                    </c:if>
                                    <c:if test="${item.done == true}">
                                        <a class="btn btn-success" disabled>Выполненно</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>