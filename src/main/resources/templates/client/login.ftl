<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Вхід | HorseRacing</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex flex-column min-vh-100">

<#include "navbar.ftl">

<div class="container mt-5 flex-grow-1 d-flex justify-content-center align-items-center">
    <div class="card shadow-sm p-4" style="width: 100%; max-width: 400px;">
        <h3 class="text-center mb-4">Вхід у систему</h3>

        <form action="/login" method="post">
            <div class="mb-3">
                <label for="email" class="form-label">Електронна пошта</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Пароль</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Увійти</button>
        </form>

        <div class="text-center mt-3">
            <a href="/registration" class="text-decoration-none">Немає акаунту? Зареєструватися</a>
        </div>
    </div>
</div>

<#include "footer.ftl">

</body>
</html>