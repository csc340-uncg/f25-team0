<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Farm Statistics - LocalHarvest Hub</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
                <li class="breadcrumb-item active">Farm Statistics</li>
            </ol>
        </nav>

        <div class="row mb-4">
            <div class="col-md-3">
                <div class="card text-white bg-success">
                    <div class="card-body">
                        <h6 class="card-title">Monthly Revenue</h6>
                        <h3 class="card-text">$${stats.monthlyRevenue}</h3>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card text-white bg-primary">
                    <div class="card-body">
                        <h6 class="card-title">Active Subscribers</h6>
                        <h3 class="card-text">${stats.activeSubscribers}</h3>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card text-white bg-info">
                    <div class="card-body">
                        <h6 class="card-title">Average Rating</h6>
                        <h3 class="card-text">${stats.averageOverallRating?string("0.0")}/5.0</h3>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card text-white bg-warning">
                    <div class="card-body">
                        <h6 class="card-title">Active Boxes</h6>
                        <h3 class="card-text">${stats.activeBoxes}</h3>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mb-4">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h5 class="card-title mb-0">Monthly Revenue</h5>
                    </div>
                    <div class="card-body">
                        <canvas id="revenueChart"></canvas>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-header">
                        <h5 class="card-title mb-0">Rating Distribution</h5>
                    </div>
                    <div class="card-body">
                        <canvas id="ratingChart"></canvas>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h5 class="card-title mb-0">Most Popular Boxes</h5>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Box Name</th>
                                        <th>Active Subscribers</th>
                                        <th>Rating</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <#list stats.mostPopularBoxes?keys as boxName>
                                        <tr>
                                            <td>${boxName}</td>
                                            <td>${stats.mostPopularBoxes[boxName]}</td>
                                            <td>${(stats.ratingsByBox[boxName]!0)?string("0.0")}/5.0</td>
                                        </tr>
                                    </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h5 class="card-title mb-0">Review Statistics</h5>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-6">
                                <p><strong>Total Reviews:</strong> ${stats.totalReviews}</p>
                                <p><strong>Response Rate:</strong> ${stats.responseRate?string("0.0")}%</p>
                            </div>
                            <div class="col-6">
                                <p><strong>Freshness Rating:</strong> ${stats.averageFreshnessRating?string("0.0")}/5.0</p>
                                <p><strong>Delivery Rating:</strong> ${stats.averageDeliveryRating?string("0.0")}/5.0</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        // Revenue Chart
        const revenueCtx = document.getElementById('revenueChart').getContext('2d');
        new Chart(revenueCtx, {
            type: 'line',
            data: {
                labels: [<#list stats.revenueByMonth?keys as month>'${month}'<#sep>, </#list>],
                datasets: [{
                    label: 'Monthly Revenue',
                    data: [<#list stats.revenueByMonth?values as revenue>${revenue}<#sep>, </#list>],
                    borderColor: 'rgb(75, 192, 192)',
                    tension: 0.1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: {
                            callback: function(value) {
                                return '$' + value;
                            }
                        }
                    }
                }
            }
        });

        // Rating Distribution Chart
        const ratingCtx = document.getElementById('ratingChart').getContext('2d');
        new Chart(ratingCtx, {
            type: 'pie',
            data: {
                labels: [<#list stats.ratingDistribution?keys as rating>'${rating} Stars'<#sep>, </#list>],
                datasets: [{
                    data: [<#list stats.ratingDistribution?values as count>${count}<#sep>, </#list>],
                    backgroundColor: [
                        '#4CAF50',
                        '#8BC34A',
                        '#CDDC39',
                        '#FFEB3B',
                        '#FFC107'
                    ]
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'bottom'
                    }
                }
            }
        });
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>