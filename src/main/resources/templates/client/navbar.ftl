<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="/all-races"> HorseRacing 2026</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="/all-races">Забіги</a></li>
                <li class="nav-item"><a class="nav-link" href="/my-bets">Мої ставки</a></li>
                <li class="nav-item"><a class="nav-link" href="/profile">Профіль</a></li>
                <#if isAdmin>
                <li class="nav-item"><a class="btn btn-outline-warning ms-lg-3" href="/admin">Адмінка</a></li>
                </#if>
            </ul>
        </div>
    </div>
</nav>