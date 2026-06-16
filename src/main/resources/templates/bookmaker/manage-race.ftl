<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Управління забігом | Букмекер</title>
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

        <div class="col-md-10 bg-light p-5">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2>Забіг: <span class="text-success">${race.title}</span></h2>
                <a href="/bookmaker" class="btn btn-outline-secondary"><i class="bi bi-arrow-left me-2"></i> Назад до списку</a>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <div class="card shadow-sm mb-4">
                        <div class="card-header bg-success text-white">
                            <h5 class="mb-0"><i class="bi bi-percent"></i> Виставлення коефіцієнтів</h5>
                        </div>
                        <div class="card-body">
                            <form action="/bookmaker/race/${race.id}/coefficients" method="post">
                                <table class="table align-middle">
                                    <thead>
                                    <tr>
                                        <th>Кінь</th>
                                        <th>Поточний коефіцієнт</th>
                                        <th>Новий коефіцієнт</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#list race.horses as horse>
                                        <tr>
                                            <td><strong>${horse.name}</strong></td>
                                            <td><span class="badge bg-primary">${coeffs[horse.id?c]!"1.00"}</span></td>
                                            <td>
                                                <input type="number" step="0.01" min="1.01" name="coeff_${horse.id}" class="form-control form-control-sm w-50" placeholder="Наприклад: 2.5">
                                            </td>
                                        </tr>
                                    </#list>
                                    </tbody>
                                </table>
                                <button type="submit" class="btn btn-success">Зберегти коефіцієнти</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card shadow-sm border-warning">
                        <div class="card-header bg-warning text-dark">
                            <h5 class="mb-0"><i class="bi bi-flag-fill"></i> Завершити забіг</h5>
                        </div>
                        <div class="card-body">
                            <p class="small text-muted">Оберіть коня-переможця для фіксації результатів та виплати виграшів.</p>
                            <form action="/bookmaker/race/${race.id}/finish" method="post">
                                <div class="mb-3">
                                    <label class="form-label">Переможець:</label>
                                    <select name="winnerId" class="form-select" required>
                                        <option value="" disabled selected>Оберіть коня...</option>
                                        <#list race.horses as horse>
                                            <option value="${horse.id}">${horse.name}</option>
                                        </#list>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-warning w-100" onclick="return confirm('Увага! Це завершить забіг і розрахує ставки. Продовжити?');">
                                    Оголосити переможця
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>