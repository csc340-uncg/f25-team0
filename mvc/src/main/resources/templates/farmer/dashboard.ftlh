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

    <div class="container">
    <div class="row">
        <div class="col-md-4">
            <div class="card mb-4">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h5 class="card-title mb-0">Profile Information</h5>
                    <a href="/farmers/profile/edit" class="btn btn-sm btn-outline-primary">Edit Profile</a>
                </div>
                <div class="card-body">
                    <p><strong>Name:</strong> ${farmer.name}</p>
                    <p><strong>Email:</strong> ${farmer.email}</p>
                    <p><strong>Phone:</strong> ${farmer.phoneNumber!'-'}</p>
                    <#if farmer.farm??>
                        <hr>
                        <div class="d-flex justify-content-between align-items-center mb-2">
                            <h6 class="mb-0">Farm Details</h6>
                            <div>
                                <a href="/farmers/statistics" class="btn btn-sm btn-outline-info me-2">View Statistics</a>
                                <a href="/farmers/farm/edit" class="btn btn-sm btn-outline-primary">Edit Farm</a>
                            </div>
                        </div>
                        <p><strong>Farm Name:</strong> ${farmer.farm.farmName}</p>
                        <p><strong>Location:</strong> ${farmer.farm.location}</p>
                        <#if farmer.farm.description??>
                            <p><strong>Description:</strong> ${farmer.farm.description}</p>
                        </#if>
                    <#else>
                        <div class="alert alert-info">
                            Please set up your farm details to start selling produce.
                            <a href="/farmers/farm/setup" class="btn btn-primary btn-sm mt-2">Set Up Farm</a>
                        </div>
                    </#if>
                </div>
            </div>
        </div>
        <div class="col-md-8">
            <#if farmer.farm??>
                <div class="card">
                    <div class="card-header d-flex justify-content-between align-items-center">
                        <div>
                            <h5 class="card-title mb-0">My Produce Boxes</h5>
                            <div>
                                <a href="/farmers/subscriptions" class="btn btn-link btn-sm ps-0">View Subscriptions</a>
                                <span class="text-muted">|</span>
                                <a href="/farmers/reviews" class="btn btn-link btn-sm">Manage Reviews</a>
                            </div>
                        </div>
                        <a href="/farmers/boxes/new" class="btn btn-success btn-sm">Add New Box</a>
                    </div>
                    <div class="card-body">
                        <#if farmer.farm.produceBoxes?? && farmer.farm.produceBoxes?size gt 0>
                            <div class="list-group">
                                <#list farmer.farm.produceBoxes as box>
                                    <div class="list-group-item">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <div>
                                                <h6 class="mb-1">${box.name}</h6>
                                                <p class="mb-1">
                                                    <span class="badge bg-${box.available?then('success', 'secondary')}">
                                                        ${box.available?then('Available', 'Unavailable')}
                                                    </span>
                                                    $${box.price}
                                                </p>
                                                <small>${box.description!''}</small>
                                            </div>
                                            <div>
                                                <a href="/farmers/boxes/${box.id}/edit" class="btn btn-sm btn-outline-primary">Edit</a>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                            </div>
                        <#else>
                            <p>No produce boxes yet. Add your first box to start selling!</p>
                            <a href="/farmers/boxes/new" class="btn btn-success">Add First Box</a>
                        </#if>
                    </div>
                </div>
            </#if>
        </div>
    </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>