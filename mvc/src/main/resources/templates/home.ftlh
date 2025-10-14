<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>${title!"LocalHarvest Hub"}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-success mb-4">
        <div class="container">
            <a class="navbar-brand" href="/">LocalHarvest Hub</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/">Home</a>
                    </li>
                    <#if customerId?? || farmerId??>
                        <#if customerId??>
                            <li class="nav-item">
                                <a class="nav-link" href="/customers/dashboard">Dashboard</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/customers/logout">Logout</a>
                            </li>
                            <#else>
                                <li class="nav-item">
                                    <a class="nav-link" href="/farmers/dashboard">Dashboard</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="/farmers/logout">Logout</a>
                                </li>
                        </#if>
                        <#else>
                            <li class="nav-item">
                                <a class="nav-link" href="/signin">Sign In</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/signup">Sign Up</a>
                            </li>
                    </#if>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="px-4 py-5 my-5 text-center">
            <h1 class="display-5 fw-bold text-body-emphasis">Welcome to LocalHarvest Hub</h1>
            <div class="col-lg-6 mx-auto">
                <p class="lead mb-4">
                    Connecting local farmers with customers who appreciate fresh, locally grown produce.
                </p>
                <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                    <a href="/signup" class="btn btn-primary btn-lg px-4 gap-3">Get Started</a>
                    <a href="/signin" class="btn btn-outline-secondary btn-lg px-4">Sign In</a>
                </div>
            </div>
        </div>

        <div class="row mb-5">
            <div class="col-12">
                <h2 class="text-center mb-4">Featured Farms</h2>
                <div class="row">
                    <#list farms as farm>
                        <div class="col-md-4 mb-4">
                            <div class="card h-100">
                                <div class="card-body">
                                    <h5 class="card-title">${farm.farmName}</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">${farm.location}</h6>
                                    <p class="card-text">${(farm.description!'')?truncate(150)}</p>
                                </div>
                            </div>
                        </div>
                    <#else>
                        <div class="col-12">
                            <p class="text-center">No farms available at the moment.</p>
                        </div>
                    </#list>
                </div>
            </div>
        </div>

        <div class="row mb-5">
            <div class="col-12">
                <h2 class="text-center mb-4">Featured Produce Boxes</h2>
                <div class="row">
                    <#list boxes as box>
                        <div class="col-md-4 mb-4">
                            <div class="card h-100">
                                <div class="card-body">
                                    <h5 class="card-title">${box.name}</h5>
                                    <p class="card-text">${(box.description!'')?truncate(150)}</p>
                                    <p class="card-text">
                                        <strong>Price: $${box.price}</strong><br>
                                        <small class="text-muted">From: ${box.farm.farmName}</small>
                                    </p>
                                </div>
                            </div>
                        </div>
                    <#else>
                        <div class="col-12">
                            <p class="text-center">No produce boxes available at the moment.</p>
                        </div>
                    </#list>
                </div>
            </div>
        </div>

        <div class="text-center mb-5">
            <p class="lead">
                Join LocalHarvest Hub to explore all our farms and produce boxes!
            </p>
            <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                <a href="/signup" class="btn btn-success btn-lg px-4 gap-3">Sign Up Now</a>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>