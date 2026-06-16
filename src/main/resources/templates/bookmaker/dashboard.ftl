<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Панель Букмекера | HorseRacing</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <style>
        .sidebar { height: 100vh; background-color: #212529; padding-top: 20px; }
        .sidebar a { color: #adb5bd; text-decoration: none; padding: 10px 20px; display: block; font-size: 1.1rem; }
        .sidebar a:hover, .sidebar a.active { background-color: #198754; color: white; }
    </style>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-2 sidebar d-flex flex-column">
            <h4 class="text-white text-center mb-4">Кабінет Букмекера</h4>
            <a href="/bookmaker" class="active"><i class="bi bi-ui-checks-grid me-2"></i> Активні забіги</a>
            <a href="/bookmaker/results"><i class="bi bi-trophy me-2"></i> Результати</a>
            <a href="/all-races" class="mt-auto text-warning mb-3"><i class="bi bi-box-arrow-left me-2"></i> На сайт</a>
            <div class="px-3 mb-4">
                <form action="/logout" method="post">
                    <button type="submit" class="btn btn-danger w-100">Вийти з облікового запису</button>
                </form>
            </div>
        </div>

        <div class="col-md-10 bg-light p-5">
            <h2 class="mb-4">Управління забігами (Коефіцієнти та Статуси)</h2>

            <div class="card shadow-sm">
                <div class="card-body">
                    <table class="table table-hover align-middle">
                        <thead class="table-success">
                        <tr>
                            <th>ID</th>
                            <th>Назва забігу</th>
                            <th>Дата</th>
                            <th>Статус</th>
                            <th>Дії букмекера</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list races as race>
                            <tr>
                                <td>${race.id}</td>
                                <td><strong>${race.title}</strong></td>
                                <td>${race.eventDate!"Не визначена"}</td>
                                <td><span class="badge bg-warning text-dark">${race.status!"Очікується"}</span></td>
                                <td>
                                    <a href="/bookmaker/race/${race.id}" class="btn btn-sm btn-success">
                                        <i class="bi bi-gear-fill"></i> Управління забігом
                                    </a>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>