<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Set Up Farm - LocalHarvest Hub</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-success mb-4">
        <div class="container">
            <a class="navbar-brand" href="/">
                <img src="/images/localharvest-logo.png" alt="LocalHarvest Hub" height="30" class="d-inline-block align-text-top me-2">
                LocalHarvest Hub
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/farmers/dashboard">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/farmers/logout">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title mb-0">Set Up Your Farm</h4>
                    </div>
                    <div class="card-body">
                        <form action="/farmers/farm/setup" method="POST">
                            <div class="mb-3">
                                <label for="farmName" class="form-label">Farm Name *</label>
                                <input type="text" class="form-control" id="farmName" name="farmName" required>
                                <div class="form-text">Enter the name of your farm as you want customers to see it.</div>
                            </div>

                            <div class="mb-3">
                                <label for="location" class="form-label">Location *</label>
                                <input type="text" class="form-control" id="location" name="location" required>
                                <div class="form-text">Enter your farm's location (city and state).</div>
                            </div>

                            <div class="mb-3">
                                <label for="description" class="form-label">Description</label>
                                <textarea class="form-control" id="description" name="description" rows="4"></textarea>
                                <div class="form-text">Describe your farm, what you grow, and what makes it special.</div>
                            </div>

                            <div class="d-flex justify-content-between">
                                <a href="/farmers/dashboard" class="btn btn-secondary">Cancel</a>
                                <button type="submit" class="btn btn-success">Create Farm</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>