<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Адмін-панель | Керування забігами</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-header bg-dark text-white d-flex justify-content-between align-items-center">
            <h3 class="mb-0">Список запланованих забігів</h3>
            <a href="/admin/races/add" class="btn btn-success btn-sm">Додати забіг</a>
        </div>
        <div class="card-body">
            <table class="table table-hover border">
                <thead class="table-light">
                <tr>
                    <th>ID</th>
                    <th>Назва забігу</th>
                    <th>Дата та час</th>
                    <th>Статус</th>
                    <th>Банер</th>
                    <th>Дії</th>
                </tr>
                </thead>
                <tbody>
                <#-- Цикл Freemarker, який проходить по кожному забігу зі списку -->
                <#list races as race>
                    <tr>
                        <td>${race.id}</td>
                        <td><strong>${race.title}</strong></td>
                        <td>${race.eventDate!"Дата не встановлена"}</td>
                        <td>
                            <span class="badge bg-primary">${race.status}</span>
                        </td>
                        <td>
                            <#if race.image??>
                                <img src="${race.image}" alt="poster" style="height: 40px; border-radius: 4px;">
                            <#else>
                                <span class="text-muted">Немає фото</span>
                            </#if>
                        </td>
                        <td>
                            <button class="btn btn-sm btn-outline-warning">Редагувати</button>
                            <button class="btn btn-sm btn-outline-danger">Видалити</button>
                        </td>
                    </tr>
                <#else>
                    <tr>
                        <td colspan="6" class="text-center text-muted py-4">
                            Забігів ще не створено. База даних порожня.
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="/js/script.js"></script>
</body>
</html>