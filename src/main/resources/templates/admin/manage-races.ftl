<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Управління забігами | Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <style>
        .sidebar { height: 100vh; background-color: #343a40; padding-top: 20px; }
        .sidebar a { color: #cfd8dc; text-decoration: none; padding: 10px 20px; display: block; font-size: 1.1rem; }
        .sidebar a:hover { background-color: #495057; color: white; }
        .sidebar a.active { background-color: #0d6efd; color: white; }
    </style>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-2 sidebar">
            <h4 class="text-white text-center mb-4">Адмін Панель</h4>
            <a href="/admin"><i class="bi bi-speedometer2 me-2"></i> Дашборд</a>
            <a href="/admin/races" class="active"><i class="bi bi-flag-fill me-2"></i> Забіги</a>
            <a href="/admin/horses"><i class="bi bi-suit-heart-fill me-2"></i> Коні</a>
            <a href="/admin/users"><i class="bi bi-people-fill me-2"></i> Користувачі</a>
            <a href="/admin/audit"><i class="bi bi-journal-text me-2"></i> Аудит логів</a>
            <a href="/" class="mt-5 text-warning"><i class="bi bi-box-arrow-left me-2"></i> На сайт</a>
            <div class="d-grid gap-2 mt-4">
                <form action="/logout" method="post">
                    <button type="submit" class="btn btn-danger w-100">Вийти з облікового запису</button>
                </form>
            </div>
        </div>

        <div class="col-md-10 bg-light p-5">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2>Управління забігами</h2>
                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addRaceModal">
                    <i class="bi bi-plus-circle"></i> Створити забіг
                </button>
            </div>

            <div class="card shadow-sm">
                <div class="card-body">
                    <table class="table table-hover align-middle">
                        <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Назва забігу</th>
                            <th>Дата</th>
                            <th>Статус</th>
                            <th>Дії</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list races as race>
                            <tr>
                                <td>${race.id}</td>
                                <td><strong>${race.title}</strong></td>
                                <td>${race.eventDate!"Не визначена"}</td>
                                <td><span class="badge bg-success">${race.status!"Активний"}</span></td>
                                <td>
                                    <button class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#editRaceModal${race.id}" title="Редагувати">
                                        <i class="bi bi-pencil"></i>
                                    </button>

                                    <form action="/admin/races/delete/${race.id}" method="post" class="d-inline">
                                        <button type="submit" class="btn btn-sm btn-outline-danger" title="Видалити" onclick="return confirm('Ви впевнені?');">
                                            <i class="bi bi-trash"></i>
                                        </button>
                                    </form>
                                </td>
                            </tr>

                            <div class="modal fade" id="editRaceModal${race.id}" tabindex="-1" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <form action="/admin/races/edit" method="post">
                                            <input type="hidden" name="id" value="${race.id}">

                                            <div class="modal-header bg-warning">
                                                <h5 class="modal-title">Редагувати забіг #${race.id}</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="mb-3">
                                                    <label class="form-label">Назва забігу</label>
                                                    <input type="text" name="title" class="form-control" required value="${race.title}">
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label">Дата та час події</label>
                                                    <input type="datetime-local" name="eventDate" class="form-control" required value="${race.eventDate!''}">
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label">Опис події</label>
                                                    <textarea name="description" class="form-control" rows="2">${race.description!''}</textarea>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Скасувати</button>
                                                <button type="submit" class="btn btn-warning">Зберегти зміни</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="addRaceModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="/admin/races/add" method="post">
                <div class="modal-header bg-primary text-white">
                    <h5 class="modal-title">Створити новий забіг</h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label class="form-label">Назва забігу (Title)</label>
                        <input type="text" name="title" class="form-control" required placeholder="Наприклад: Derby 2026">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Дата та час події</label>
                        <input type="datetime-local" name="eventDate" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Опис події</label>
                        <textarea name="description" class="form-control" rows="2"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Скасувати</button>
                    <button type="submit" class="btn btn-primary">Зберегти забіг</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>