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
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <ul class="nav nav-tabs card-header-tabs">
                            <li class="nav-item">
                                <a class="nav-link active" id="customer-tab" data-bs-toggle="tab"
                                    href="#customer">Customer</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="farmer-tab" data-bs-toggle="tab" href="#farmer">Farmer</a>
                            </li>
                        </ul>
                    </div>
                    <div class="card-body">
                        <div class="tab-content">
                            <div class="tab-pane fade show active" id="customer">
                                <h3 class="card-title mb-4">Customer Sign In</h3>
                                <form action="/customers/signin" method="post">
                                    <div class="mb-3">
                                        <label for="customerEmail" class="form-label">Email</label>
                                        <input type="email" class="form-control" id="customerEmail" name="email"
                                            required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="customerPassword" class="form-label">Password</label>
                                        <input type="password" class="form-control" id="customerPassword"
                                            name="password" required>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Sign In</button>
                                </form>
                                <div class="mt-3">
                                    <a href="/customers/signup">Need an account? Sign up as a customer</a>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="farmer">
                                <h3 class="card-title mb-4">Farmer Sign In</h3>
                                <form action="/farmers/signin" method="post">
                                    <div class="mb-3">
                                        <label for="farmerEmail" class="form-label">Email</label>
                                        <input type="email" class="form-control" id="farmerEmail" name="email" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="farmerPassword" class="form-label">Password</label>
                                        <input type="password" class="form-control" id="farmerPassword" name="password"
                                            required>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Sign In</button>
                                </form>
                                <div class="mt-3">
                                    <a href="/farmers/signup">Need an account? Sign up as a farmer</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>