<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Панель керування | HorseRacing</title>
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
            <a href="/admin" class="active"><i class="bi bi-speedometer2 me-2"></i> Дашборд</a>
            <a href="/admin/races"><i class="bi bi-flag-fill me-2"></i> Забіги</a>
            <a href="/admin/horses"><i class="bi bi-suit-heart-fill me-2"></i> Коні</a>
            <a href="/admin/users"><i class="bi bi-people-fill me-2"></i> Користувачі</a>
            <a href="/admin/audit"><i class="bi bi-journal-text me-2"></i> Аудит логів</a>
            <a href="/all-races" class="mt-5 text-warning"><i class="bi bi-box-arrow-left me-2"></i> На сайт</a>
            <div class="d-grid gap-2 mt-4">
                <form action="/logout" method="post">
                    <button type="submit" class="btn btn-danger w-100">Вийти з облікового запису</button>
                </form>
            </div>
        </div>

        <div class="col-md-10 bg-light p-5">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2>Огляд системи</h2>
                <div class="text-muted">Ласкаво просимо, Адміністратор!</div>
            </div>

            <div class="row">
                <div class="col-md-4">
                    <div class="card text-white bg-primary shadow-sm h-100">
                        <div class="card-body">
                            <h5 class="card-title">Усього забігів</h5>
                            <h1 class="display-4">${totalRaces!"0"}</h1>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card text-white bg-success shadow-sm h-100">
                        <div class="card-body">
                            <h5 class="card-title">Активних ставок</h5>
                            <h1 class="display-4">...</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>