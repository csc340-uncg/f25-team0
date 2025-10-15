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
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/customers/boxes">Produce Boxes</a></li>
                <li class="breadcrumb-item active" aria-current="page">${box.name}</li>
            </ol>
        </nav>

        <div class="row">
            <div class="col-md-8">
                <h2>${box.name}</h2>
                <p class="text-muted">From: ${box.farm.farmName}</p>
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title">Description</h5>
                        <p class="card-text">${box.description!''}</p>
                    </div>
                </div>

                <#if box.reviews?? && box.reviews?size gt 0>
                    <h4>Customer Reviews</h4>
                    <div class="list-group mb-4">
                        <#list box.reviews as review>
                            <div class="list-group-item">
                                <div class="d-flex justify-content-between">
                                    <h6 class="mb-1">${review.overallRating} stars</h6>
                                    <small class="text-muted">${review.createdAt?datetime.iso}</small>
                                </div>
                                <p class="mb-1">${review.comment!''}</p>
                            </div>
                        </#list>
                    </div>
                </#if>
            </div>

            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h3 class="card-title text-center mb-4">$${box.price}</h3>
                        <form action="/customers/boxes/${box.id}/subscribe" method="post">
                            <div class="mb-3">
                                <label for="subscriptionType" class="form-label">Subscription Type</label>
                                <select class="form-select mb-3" id="subscriptionType" name="subscriptionType" required>
                                    <option value="">Choose a subscription type...</option>
                                    <option value="ONE_TIME">One-time Purchase</option>
                                    <option value="MONTHLY">Monthly Subscription</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary btn-lg w-100">Subscribe Now</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>