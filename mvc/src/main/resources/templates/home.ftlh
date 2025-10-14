<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>${title!"LocalHarvest Hub"}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .hero-image-container {
            position: relative;
            overflow: hidden;
            border-radius: 1rem;
            box-shadow: 0 1rem 3rem rgba(0, 0, 0, .175);
            transition: transform 0.3s ease-in-out;
        }

        .hero-image-container:hover {
            transform: translateY(-5px);
        }

        .featured-section {
            position: relative;
            z-index: 1;
        }

        .featured-section::before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: #f8f9fa;
            transform: skewY(-3deg);
            z-index: -1;
        }

        .card {
            transition: transform 0.2s ease-in-out;
        }

        .card:hover {
            transform: translateY(-5px);
        }
    </style>
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
        <div class="px-4 py-5 my-5">
            <div class="row align-items-center">
                <div class="col-lg-6 text-center text-lg-start">
                    <h1 class="display-5 fw-bold text-body-emphasis mb-4">Welcome to LocalHarvest Hub</h1>
                    <p class="lead mb-4">
                        Connecting local farmers with customers who appreciate fresh, locally grown produce.
                        Experience the difference of farm-fresh vegetables delivered directly to your door.
                    </p>
                    <div class="d-grid gap-2 d-sm-flex justify-content-lg-start justify-content-center">
                        <a href="/signup" class="btn btn-primary btn-lg px-4 gap-3">Get Started</a>
                        <a href="/signin" class="btn btn-outline-secondary btn-lg px-4">Sign In</a>
                    </div>
                </div>
                <div class="col-lg-6 mt-5 mt-lg-0">
                    <div class="position-relative">
                        <img src="/images/farmers-handover.jpg" class="img-fluid rounded-3 shadow-lg"
                            alt="Farmer handing over fresh produce to a customer"
                            style="width: 100%; max-height: 400px; object-fit: cover;">
                        <div class="position-absolute bottom-0 start-0 w-100 p-3 text-white"
                            style="background: linear-gradient(transparent, rgba(0,0,0,0.7));">
                            <p class="mb-0"><small>Supporting local agriculture, one box at a time</small></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="featured-section py-5 mb-5">
            <div class="row mb-5">
                <div class="col-12">
                    <h2 class="text-center mb-4">Featured Farms</h2>
                    <p class="text-center text-muted mb-5">Discover local farms committed to sustainable agriculture</p>
                    <div class="row">
                        <#list farms as farm>
                            <div class="col-md-4 mb-4">
                                <div class="card h-100 border-0 shadow-sm">
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
                    <p class="text-center text-muted mb-5">Fresh, seasonal produce delivered to your door</p>
                    <div class="row">
                        <#list boxes as box>
                            <div class="col-md-4 mb-4">
                                <div class="card h-100 border-0 shadow-sm">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between align-items-start mb-2">
                                            <h5 class="card-title mb-0">${box.name}</h5>
                                            <span class="badge bg-success">$${box.price}</span>
                                        </div>
                                        <p class="card-text">${(box.description!'')?truncate(150)}</p>
                                        <p class="card-text">
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
        </div>

        <div class="text-center py-5 mb-5" style="background-color: #f8f9fa; border-radius: 1rem;">
            <div class="col-lg-8 mx-auto">
                <h2 class="display-6 mb-3">Ready to Experience Farm-Fresh Produce?</h2>
                <p class="lead mb-4">
                    Join LocalHarvest Hub to explore all our farms and produce boxes.
                    Support local agriculture while enjoying the freshest seasonal produce.
                </p>
                <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                    <a href="/signup" class="btn btn-success btn-lg px-5 gap-3">Sign Up Now</a>
                    <a href="/signin" class="btn btn-outline-success btn-lg px-5">Sign In</a>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>