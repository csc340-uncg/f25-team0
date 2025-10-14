<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Box Subscriptions - LocalHarvest Hub</title>
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
        <div class="row">
            <div class="col-12">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/farmers/dashboard">Dashboard</a></li>
                        <li class="breadcrumb-item active">Box Subscriptions</li>
                    </ol>
                </nav>

                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title mb-0">Box Subscriptions</h4>
                    </div>
                    <div class="card-body">
                        <#if boxes?? && boxes?size gt 0>
                            <#list boxes as box>
                                <div class="card mb-4">
                                    <div class="card-header bg-light">
                                        <h5 class="card-title mb-0">${box.name}</h5>
                                    </div>
                                    <div class="card-body">
                                        <#if box.subscriptions?? && box.subscriptions?size gt 0>
                                            <div class="table-responsive">
                                                <table class="table table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>Customer</th>
                                                            <th>Type</th>
                                                            <th>Start Date</th>
                                                            <th>Status</th>
                                                            <th>Last Delivery</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <#list box.subscriptions as sub>
                                                            <tr>
                                                                <td>
                                                                    <div>${sub.customer.name}</div>
                                                                    <small class="text-muted">${sub.customer.email}</small>
                                                                </td>
                                                                <td>${sub.type}</td>
                                                                <td>${sub.startDate?datetime.iso}</td>
                                                                <td>
                                                                    <span class="badge bg-${sub.active?then('success', 'secondary')}">
                                                                        ${sub.active?then('Active', 'Inactive')}
                                                                    </span>
                                                                </td>
                                                                <td>
                                                                    <#if sub.lastDeliveryDate??>
                                                                        ${sub.lastDeliveryDate?datetime.iso}
                                                                    <#else>
                                                                        Not delivered yet
                                                                    </#if>
                                                                </td>
                                                            </tr>
                                                        </#list>
                                                    </tbody>
                                                </table>
                                            </div>
                                        <#else>
                                            <p class="text-muted mb-0">No active subscriptions for this box.</p>
                                        </#if>
                                    </div>
                                    <div class="card-footer text-muted">
                                        Total Subscriptions: ${box.subscriptions?size}
                                    </div>
                                </div>
                            </#list>
                        <#else>
                            <div class="alert alert-info">
                                You don't have any produce boxes yet. <a href="/farmers/boxes/new" class="alert-link">Create your first box</a> to start receiving subscriptions.
                            </div>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>