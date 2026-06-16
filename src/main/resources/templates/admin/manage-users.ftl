<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Управління користувачами | Admin</title>
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
            <a href="/admin/horses"><i class="bi bi-suit-heart-fill me-2"></i> Коні</a>
            <a href="/admin/users" class="active"><i class="bi bi-people-fill me-2"></i> Користувачі</a>
            <a href="/admin/audit"><i class="bi bi-journal-text me-2"></i> Аудит логів</a>
            <a href="/" class="mt-5 text-warning"><i class="bi bi-box-arrow-left me-2"></i> На сайт</a>
            <div class="d-grid gap-2 mt-4">
                <form action="/logout" method="post">
                    <button type="submit" class="btn btn-danger w-100">Вийти з облікового запису</button>
                </form>
            </div>
        </div>

        <div class="col-md-10 bg-light p-5">
            <h2 class="mb-4">Управління користувачами</h2>

            <div class="card shadow-sm">
                <div class="card-body">
                    <table class="table table-hover align-middle">
                        <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Ім'я / Email</th>
                            <th>Поточна Роль</th>
                            <th>Змінити роль</th>
                            <th>Дії</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list users as user>
                            <tr>
                                <td>${user.id}</td>
                                <td>
                                    <strong>${user.firstName!""} ${user.lastName!""}</strong><br>
                                    <small class="text-muted">${user.email}</small>
                                </td>
                                <td>
                                    <#list user.roles as role>
                                        <span class="badge bg-info text-dark">${role.name}</span>
                                    </#list>
                                </td>
                                <td>
                                    <form action="/admin/users/role/${user.id}" method="post" class="d-flex gap-2">
                                        <select name="roleId" class="form-select form-select-sm" style="width: auto;">
                                            <#list roles as role>
                                                <option value="${role.id}">${role.name}</option>
                                            </#list>
                                        </select>
                                        <button type="submit" class="btn btn-sm btn-primary">Оновити</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="/admin/users/delete/${user.id}" method="post">
                                        <button type="submit" class="btn btn-sm btn-outline-danger"
                                                onclick="return confirm('Дійсно видалити цього користувача?')">
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
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>