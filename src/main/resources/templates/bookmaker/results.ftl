<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Результати забігів | Букмекер</title>
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
        <!-- Сайдбар -->
        <div class="col-md-2 sidebar d-flex flex-column">
            <h4 class="text-white text-center mb-4">Кабінет Букмекера</h4>
            <a href="/bookmaker"><i class="bi bi-ui-checks-grid me-2"></i> Активні забіги</a>
            <!-- Тут ми додали клас active, щоб "Результати" підсвічувалися зеленим -->
            <a href="/bookmaker/results" class="active"><i class="bi bi-trophy me-2"></i> Результати</a>

            <a href="/" class="mt-auto text-warning mb-3"><i class="bi bi-box-arrow-left me-2"></i> На сайт</a>

            <!-- Твоя красива кнопка виходу -->
            <div class="px-3 mb-4">
                <form action="/logout" method="post">
                    <button type="submit" class="btn btn-danger w-100">Вийти з облікового запису</button>
                </form>
            </div>
        </div>

        <!-- Основна частина -->
        <div class="col-md-10 bg-light p-5">
            <h2 class="mb-4">Архів завершених забігів</h2>

            <div class="card shadow-sm">
                <div class="card-body p-0">
                    <table class="table table-hover mb-0">
                        <thead class="table-light">
                        <tr>
                            <th>ID</th>
                            <th>Назва забігу</th>
                            <th>Дата</th>
                            <th>Статус</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list races as race>
                            <tr>
                                <td>${race.id}</td>
                                <td><strong>${race.title}</strong></td>
                                <td>${race.eventDate}</td>
                                <td><span class="badge bg-secondary">${race.status}</span></td>
                            </tr>
                        <#else>
                            <tr>
                                <td colspan="4" class="text-center py-4 text-muted">
                                    Немає жодного завершеного забігу.
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>