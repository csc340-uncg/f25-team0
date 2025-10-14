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
                    <a href="/customers/profile/edit" class="btn btn-sm btn-outline-primary">Edit Profile</a>
                </div>
                <div class="card-body">
                    <p><strong>Name:</strong> ${customer.name}</p>
                    <p><strong>Email:</strong> ${customer.email}</p>
                    <p><strong>Shipping Address:</strong> ${customer.shippingAddress}</p>
                    <p><strong>Phone:</strong> ${customer.phoneNumber!'-'}</p>
                </div>
            </div>
        </div>
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h5 class="card-title mb-0">My Subscriptions</h5>
                </div>
                <div class="card-body">
                    <#if customer.subscriptions?? && customer.subscriptions?size gt 0>
                        <div class="list-group">
                            <#list customer.subscriptions as subscription>
                                <div class="list-group-item">
                                    <h6 class="mb-1">
                                        <a href="/customers/boxes/${subscription.produceBox.id}" class="text-decoration-none">${subscription.produceBox.name}</a>
                                    </h6>
                                    <p class="mb-1">
                                        <span class="badge bg-${subscription.active?then('success', 'secondary')}">
                                            ${subscription.active?then('Active', 'Inactive')}
                                        </span>
                                        ${subscription.type}
                                    </p>
                                    <small>Start Date: ${subscription.startDate?datetime.iso}</small>
                                    <#if subscription.endDate??>
                                        <br>
                                        <small>End Date: ${subscription.endDate?datetime.iso}</small>
                                    </#if>
                                    <div class="mt-2">
                                        <a href="/customers/boxes/${subscription.produceBox.id}/review"
                                           class="btn btn-sm btn-outline-primary">Write a Review</a>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    <#else>
                        <p>No subscriptions yet. Browse our produce boxes to get started!</p>
                        <a href="/produce-boxes" class="btn btn-primary">Browse Produce Boxes</a>
                    </#if>
                </div>
            </div>
        </div>
    </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>