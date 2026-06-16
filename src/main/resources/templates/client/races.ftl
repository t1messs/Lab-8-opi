<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Лінія забігів | HorseRacing</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex flex-column min-vh-100">

<#-- Підключаємо меню -->
<#include "navbar.ftl">

<div class="container mt-4 flex-grow-1">
    <header class="mb-4">
        <h1 class="display-5 fw-bold">Актуальні забіги</h1>
        <p class="text-muted">Оберіть подію, коня, та зробіть свою ставку!</p>
    </header>

    <div class="row g-4">
        <#-- Активні забіги -->
        <#list activeRaces as race>
            <div class="col-md-4">
                <div class="card h-100 shadow-sm border-0">
                    <img src="${race.image!'/images/races/race.jpg'}" class="card-img-top" alt="race" style="height: 200px; object-fit: cover;">
                    <div class="card-body">
                        <h5 class="card-title">${race.title}</h5>
                        <p class="card-text text-muted">Дата: ${race.eventDate!"Не визначена"}</p>
                        <span class="badge bg-success mb-3">${race.status}</span>
                    </div>
                    <div class="card-footer bg-white border-0">
                        <form action="/add-bet" method="post">
                            <input type="hidden" name="raceId" value="${race.id}">

                            <div class="list-group mb-3" style="max-height: 250px; overflow-y: auto;">
                                <#list race.horses as horse>
                                    <label class="list-group-item d-flex gap-3 align-items-center list-group-item-action cursor-pointer">
                                        <input class="form-check-input flex-shrink-0" type="radio" name="horseId" value="${horse.id}" required>

                                        <img src="/images/horse/${horse.image!'default_horse.jpg'}" alt="Кінь"
                                             style="width: 50px; height: 50px; border-radius: 50%; object-fit: cover; background-color: #eee;">

                                        <div class="d-flex flex-column">
                                            <strong class="mb-0">${horse.name}</strong>
                                            <small class="text-muted">
                                                ${horse.statsInfo!'Додаткова інформація відсутня'}
                                            </small>
                                            <div class="mt-1">
                                                <small class="text-secondary">
                                                    Коефіцієнт: <strong class="text-dark">${coeffs[race.id?string + "_" + horse.id?string]!"1.0"}</strong>
                                                </small>
                                            </div>
                                        </div>
                                    </label>
                                </#list>
                            </div>

                            <div class="input-group mb-2">
                                <span class="input-group-text">$</span>
                                <input type="number" name="amount" class="form-control" placeholder="Сума" min="10" required>
                            </div>

                            <button type="submit" class="btn btn-primary w-100">Зробити ставку</button>
                        </form>
                    </div>
                </div>
            </div>
        <#else>
            <div class="col-12 text-center py-5">
                <h3>Сьогодні забігів немає. Заходьте пізніше!</h3>
            </div>
        </#list>
    </div>

    <#-- Завершені забіги -->
    <h2 class="mb-4 text-secondary">Архів забігів</h2>

    <div class="row">
        <#list completedRaces as race>
            <div class="col-md-6 mb-4">
                <div class="card shadow-sm border-secondary bg-light">
                    <div class="card-body">
                        <h5 class="card-title text-muted">${race.title}</h5>
                        <p class="card-text text-muted small">Дата проведення: ${race.eventDate}</p>
                        <span class="badge bg-secondary mb-3">Завершено</span>

                        <div class="alert alert-secondary text-center" role="alert">
                                <p class="mb-1 text-muted small">Переможець забігу:</p>
                                <h5 class="text-success mb-0">
                                    <strong> ${winners[race.id?c]!"Очікування результатів..."}</strong>
                                </h5>
                            <p class="mb-0">Забіг завершено, результати зафіксовані.</p>
                        </div>
                    </div>
                </div>
            </div>
        <#else>
            <div class="col-12"><p class="text-muted">Історія забігів порожня.</p></div>
        </#list>
    </div>
</div>

<#-- Підключаємо footer -->
<#include "footer.ftl">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>