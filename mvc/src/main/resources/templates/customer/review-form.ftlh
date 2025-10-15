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
                <li class="breadcrumb-item"><a href="/customers/dashboard">Dashboard</a></li>
                <li class="breadcrumb-item active" aria-current="page">Review ${box.name}</li>
            </ol>
        </nav>

        <div class="row">
            <div class="col-md-8 mx-auto">
                <div class="card">
                    <div class="card-header">
                        <h4 class="mb-0">Review ${box.name}</h4>
                    </div>
                    <div class="card-body">
                        <form action="/customers/boxes/${box.id}/review" method="post">
                            <div class="mb-3">
                                <label class="form-label">Freshness Rating</label>
                                <div class="d-flex align-items-center">
                                    <#list 1..5 as i>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="freshnessRating"
                                                id="freshness${i}" value="${i}" required>
                                            <label class="form-check-label" for="freshness${i}">${i}</label>
                                        </div>
                                    </#list>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Delivery Rating</label>
                                <div class="d-flex align-items-center">
                                    <#list 1..5 as i>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="deliveryRating"
                                                id="delivery${i}" value="${i}" required>
                                            <label class="form-check-label" for="delivery${i}">${i}</label>
                                        </div>
                                    </#list>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="comment" class="form-label">Comments (Optional)</label>
                                <textarea class="form-control" id="comment" name="comment" rows="4"
                                    placeholder="Share your experience with this produce box..."></textarea>
                            </div>

                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary">Submit Review</button>
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