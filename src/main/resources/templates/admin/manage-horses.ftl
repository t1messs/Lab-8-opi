<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Управління стайнею | Admin</title>
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
            <a href="/admin/races"><i class="bi bi-flag-fill me-2"></i> Забіги</a>
            <a href="/admin/horses" class="active"><i class="bi bi-suit-heart-fill me-2"></i> Коні</a>
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
            <div class="d-flex justify-content-between mb-4">
                <h2>Управління стайнею (Коні)</h2>
                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addHorseModal">
                    <i class="bi bi-plus-circle"></i> Додати коня
                </button>
            </div>

            <table class="table table-hover shadow-sm bg-white align-middle">
                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Фото</th>
                    <th>Кличка</th>
                    <th>Характеристики</th>
                    <th>Дії</th>
                </tr>
                </thead>
                <tbody>
                <#list horses as horse>
                    <tr>
                        <td>${horse.id}</td>
                        <td><img src="/images/horse/${horse.image!'default_horse.jpg'}" width="50" height="50" style="object-fit: cover;" class="rounded-circle"></td>
                        <td><strong>${horse.name}</strong></td>
                        <td><small>${horse.statsInfo!'Немає даних'}</small></td>
                        <td>
                            <form action="/admin/horses/delete/${horse.id}" method="post" class="d-inline">
                                <button type="submit" class="btn btn-sm btn-outline-danger" onclick="return confirm('Дійсно видалити цього коня?');">
                                    <i class="bi bi-trash"></i>
                                </button>
                            </form>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>


<div class="modal fade" id="addHorseModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="/admin/horses/save" method="post">
                <div class="modal-header bg-primary text-white">
                    <h5 class="modal-title">Додати нового коня</h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label class="form-label">Кличка коня (Name)</label>
                        <input type="text" name="name" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Характеристики (Stats)</label>
                        <textarea name="statsInfo" class="form-control" rows="2" placeholder="Наприклад: Вік 3 роки, 5 перемог"></textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Назва файлу фото (Image)</label>
                        <input type="text" name="image" class="form-control" placeholder="horse1.jpg">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Скасувати</button>
                    <button type="submit" class="btn btn-primary">Зберегти</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>