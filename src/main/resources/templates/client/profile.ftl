<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Мій Профіль</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex flex-column min-vh-100">

<#include "navbar.ftl">

<div class="container mt-5 flex-grow-1">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-sm">
                <div class="card-header bg-primary text-white">
                    <h4 class="mb-0">Особистий кабінет</h4>
                </div>
                <div class="card-body">
                    <div class="mb-3">
                        <strong>Email (Логін):</strong>
                        <span class="fs-5">${user.email}</span>
                    </div>

                    <div class="mb-4">
                        <strong>Поточний баланс:</strong>
                        <span class="badge bg-success fs-5"><#if user.balance??>${user.balance}<#else>0.00</#if> $</span>
                    </div>

                    <!-- Форма поповнення рахунку -->
                    <div class="mb-4 p-3 border rounded bg-white">
                        <label class="form-label fw-bold">Поповнити рахунок:</label>
                        <form action="/wallet/deposit" method="post" class="d-flex align-items-center">
                            <div class="input-group">
                                <span class="input-group-text">$</span>
                                <input type="number" name="amount" class="form-control" placeholder="Введіть суму" min="1" step="0.01" required>
                                <button type="submit" class="btn btn-success">Поповнити</button>
                            </div>
                        </form>
                    </div>
                    <!-- Кінець форми поповнення -->

                    <div class="d-grid gap-2">
                        <a href="/my-bets" class="btn btn-outline-primary">Переглянути мої ставки</a>
                        <a href="/all-races" class="btn btn-primary">Зробити нову ставку</a>
                    </div>
                    <div class="d-grid gap-2 mt-4">
                        <form action="/logout" method="post">
                            <button type="submit" class="btn btn-danger w-100">Вийти з облікового запису</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "footer.ftl">
</body>
</html>