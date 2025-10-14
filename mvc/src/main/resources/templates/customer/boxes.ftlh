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
            <a class="navbar-brand" href="/">
                <img src="/images/localharvest-logo.png" alt="LocalHarvest Hub" height="30"
                    class="d-inline-block align-text-top me-2">
                LocalHarvest Hub
            </a>
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
                                <a class="nav-link" href="/customers/boxes">Browse Boxes</a>
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
    <div class="container mt-4">
        <h2>Available Produce Boxes</h2>
        <div class="row">
            <#list boxes as box>
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${box.name}</h5>
                            <p class="card-text">${box.description!''}</p>
                            <p class="card-text"><strong>Price: $${box.price}</strong></p>
                            <p class="card-text"><small class="text-muted">From: ${box.farm.farmName}</small></p>
                            <a href="/customers/boxes/${box.id}" class="btn btn-primary">View Details</a>
                        </div>
                    </div>
                </div>
                <#else>
                    <div class="col-12">
                        <p>No produce boxes available at the moment.</p>
                    </div>
            </#list>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>