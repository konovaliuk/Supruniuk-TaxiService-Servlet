<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSP - Hello World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <style>
        .wrapper {
            width: 80%;
            margin: 0 auto;
        }
    </style>
</head>
<body class="wrapper my-3">
<h1>Taxi Service API</h1>

<div class="border-top border-bottom">
    <h3>Roles</h3>
    <div class="alert alert-primary d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/roles</p>
    </div>
    <div class="alert alert-primary  d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/roles/:id</p>
    </div>
    <div class="alert alert-primary d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/roles/rolename/:name</p>
    </div>

    <div class="alert alert-success d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-success mx-4">POST</button>
        <p class="m-0 p-0">/api/roles</p>
        <small class="px-4">body: { rolename: String }</small>
    </div>

    <div class="alert alert-warning d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-warning mx-4">PUT</button>
        <p class="m-0 p-0">/api/roles/:id</p>
        <small class="px-4">body: { rolename: String }</small>
    </div>

    <div class="alert alert-danger d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-danger mx-4">DELETE</button>
        <p class="m-0 p-0">/api/roles/:id</p>
    </div>
</div>



<div class="border-top border-bottom">
    <h3>Car types</h3>
    <div class="alert alert-primary d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/car-types</p>
    </div>
    <div class="alert alert-primary  d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/car-types/:id</p>
    </div>
    <div class="alert alert-primary d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/car-types/typename/:name</p>
    </div>

    <div class="alert alert-success d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-success mx-4">POST</button>
        <p class="m-0 p-0">/api/car-types</p>
        <small class="px-4">body: { typename: String, description: String }</small>
    </div>

    <div class="alert alert-warning d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-warning mx-4">PUT</button>
        <p class="m-0 p-0">/api/car-types/:id</p>
        <small class="px-4">body: { typename: String, description: String }</small>
    </div>

    <div class="alert alert-danger d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-danger mx-4">DELETE</button>
        <p class="m-0 p-0">/api/car-types/:id</p>
    </div>
</div>
</body>
</html>