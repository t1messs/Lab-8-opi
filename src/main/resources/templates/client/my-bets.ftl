<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Мої ставки | HorseRacing</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex flex-column min-vh-100">

<#include "navbar.ftl">

<div class="container mt-5 flex-grow-1">
    <h2 class="mb-4">Мої ставки</h2>

    <#if successMessage??>
        <div class="alert alert-success shadow-sm">
            ${successMessage}
        </div>
    </#if>
    <#if errorMessage??>
        <div class="alert alert-danger shadow-sm">
            ${errorMessage}
        </div>
    </#if>

    <#if betSlip.items?size == 0>
        <div class="alert alert-info text-center py-4 shadow-sm">
            <h5 class="mb-3">Ви ще не зробили жодної ставки</h5>
            <p>Перейдіть до списку забігів, оберіть свого фаворита та зробіть ставку!</p>
            <a href="/all-races" class="btn btn-primary mt-2">Дивитись забіги</a>
        </div>
    <#else>
        <div class="card shadow-sm mb-4">
            <div class="card-body">
                <table class="table table-hover align-middle">
                    <thead class="table-light">
                    <tr>
                        <th>Забіг</th>
                        <th>Кінь</th>
                        <th>Сума ставки</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list betSlip.items as item>
                        <tr>
                            <td>${item.race.title} <span class="text-muted">(ID: ${item.race.id})</span></td>
                            <td><strong>${item.horse.name}</strong></td>
                            <td><span class="badge bg-success fs-6">${item.amount} ₴</span></td>
                        </tr>
                    </#list>
                    </tbody>
                </table>

                <div class="d-flex justify-content-end mt-4 border-top pt-3">
                    <h4 class="me-4 mb-0">Загальна сума: <span class="text-success fw-bold">${betSlip.totalAmount} ₴</span></h4>
                </div>
            </div>
        </div>

        <div class="d-flex justify-content-end mb-5">
            <form action="/checkout-bets" method="post">
                <button type="submit" class="btn btn-success btn-lg px-5 shadow">Підтвердити ставки</button>
            </form>
        </div>
    </#if>
</div>

<#include "footer.ftl">

</body>
</html>