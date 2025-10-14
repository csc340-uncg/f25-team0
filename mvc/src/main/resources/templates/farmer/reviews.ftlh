<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Box Reviews - LocalHarvest Hub</title>
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
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/farmers/dashboard">Dashboard</a></li>
                <li class="breadcrumb-item active">Box Reviews</li>
            </ol>
        </nav>

        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title mb-0">Customer Reviews</h4>
                    </div>
                    <div class="card-body">
                        <#if reviews?size == 0>
                            <p class="text-muted">No reviews yet.</p>
                        <#else>
                            <#list reviews as review>
                                <div class="card mb-3">
                                    <div class="card-header bg-light">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <h6 class="mb-0">${review.produceBox.name}</h6>
                                            <small class="text-muted">${review.createdAt?datetime.iso}</small>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="mb-3">
                                            <div class="d-flex align-items-center mb-2">
                                                <div class="me-3">
                                                    <strong>Overall:</strong> ${review.overallRating}/5
                                                </div>
                                                <div class="me-3">
                                                    <strong>Freshness:</strong> ${review.freshnessRating}/5
                                                </div>
                                                <div>
                                                    <strong>Delivery:</strong> ${review.deliveryRating}/5
                                                </div>
                                            </div>
                                            <p class="mb-0">${review.comment}</p>
                                        </div>

                                        <#if review.farmerResponse??>
                                            <div class="border-start border-success border-3 ps-3 mt-3">
                                                <small class="text-muted">Your Response:</small>
                                                <p class="mb-0">${review.farmerResponse}</p>
                                            </div>
                                        <#else>
                                            <form action="/farmers/reviews/${review.id}/reply" method="POST" class="mt-3">
                                                <div class="form-group">
                                                    <label for="response-${review.id}" class="form-label">Reply to this review:</label>
                                                    <textarea class="form-control" id="response-${review.id}"
                                                              name="response" rows="2" required></textarea>
                                                </div>
                                                <button type="submit" class="btn btn-primary btn-sm mt-2">Send Reply</button>
                                            </form>
                                        </#if>
                                    </div>
                                </div>
                            </#list>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>