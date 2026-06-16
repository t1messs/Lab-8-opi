<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Реєстрація | HorseRacing</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex flex-column min-vh-100">

<#include "navbar.ftl">

<div class="container mt-5 flex-grow-1 d-flex justify-content-center align-items-center">
    <div class="card shadow-sm p-4" style="width: 100%; max-width: 500px;">
        <h3 class="text-center mb-4">Створення акаунту</h3>

        <form action="/registration" method="post">
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label for="firstName" class="form-label">Ім'я *</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" required>
                </div>
                <div class="col-md-6 mb-3">
                    <label for="lastName" class="form-label">Прізвище *</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" required>
                </div>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Електронна пошта *</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label for="phone" class="form-label">Телефон</label>
                    <input type="text" class="form-control" id="phone" name="phone">
                </div>
                <div class="col-md-6 mb-3">
                    <label for="age" class="form-label">Вік *</label>
                    <input type="number" class="form-control" id="age" name="age" min="18" required>
                </div>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Пароль *</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <button type="submit" class="btn btn-success w-100">Зареєструватися</button>
        </form>

        <div class="text-center mt-3">
            <a href="/login" class="text-decoration-none">Вже є акаунт? Увійти</a>
        </div>
    </div>
</div>

<#include "footer.ftl">

</body>
</html>