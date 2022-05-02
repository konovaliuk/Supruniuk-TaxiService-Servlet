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

        .container-margin {
            margin: 100px 0px;
        }
    </style>
</head>
<body class="wrapper my-3">
<h1>Taxi Service API</h1>


<div class="border-top border-bottom container-margin">
    <h3>Users</h3>
    <div class="alert alert-primary d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/users</p>
    </div>
    <div class="alert alert-primary  d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/users/:id</p>
    </div>

    <div class="alert alert-success d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-success mx-4">POST</button>
        <p class="m-0 p-0">/api/users</p>
        <small class="px-4">body: { name: String, surname: String, email: String, password_hash: String, roleId: Number }</small>
    </div>

    <div class="alert alert-warning d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-warning mx-4">PUT</button>
        <p class="m-0 p-0">/api/users/:id</p>
        <small class="px-4">body: { field: String, value: String }</small>
    </div>

    <div class="alert alert-danger d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-danger mx-4">DELETE</button>
        <p class="m-0 p-0">/api/users/:id</p>
    </div>
</div>


<div class="border-top border-bottom container-margin">
    <h3>Orders</h3>
    <div class="alert alert-primary d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/orders</p>
    </div>


    <div class="alert alert-primary  d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/orders</p>
        <div>
            <small  class="px-4">param: {role: [client, driver, dispatcher], id: Number}</small>
            <small>http://localhost:8080/api/orders?role=client&id=6</small>
        </div>
    </div>

    <div class="alert alert-primary  d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/orders/:id</p>
    </div>

    <div class="alert alert-success d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-success mx-4">POST</button>
        <p class="m-0 p-0">/api/order</p>
        <small class="px-4">body: { client_id: Number, origin_address: String, destination_address: String, distance: Number, number_of_people: Number, car_type_id: Number }</small>
    </div>

    <div class="alert alert-warning d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-warning mx-4">PUT</button>
        <p class="m-0 p-0">/api/orders/dispatcher/:id</p>
        <small class="px-4">body: { dispatcher_id: Number, approved: boolean, order_status: Number, total_payment: String }</small>
    </div>

    <div class="alert alert-warning d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-warning mx-4">PUT</button>
        <p class="m-0 p-0">/api/orders/driver/:id</p>
        <small class="px-4">body: { driver_id: Number, waiting_time: String, order_status: Number, car_id: Number }</small>
    </div>

    <div class="alert alert-warning d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-warning mx-4">PUT</button>
        <p class="m-0 p-0">/api/orders/feedback/:id</p>
        <small class="px-4">body: { comment: String, grade: Number}, param: {role: [client, driver]</small>
    </div>


</div>


<div class="border-top border-bottom container-margin">
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


<div class="border-top border-bottom container-margin">
    <h3>Users role connections</h3>
    <div class="alert alert-primary d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/role-connections</p>
    </div>
    <div class="alert alert-primary  d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/role-connections/:id</p>
    </div>

    <div class="alert alert-primary  d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/role-connections/user/:id</p>
    </div>

    <div class="alert alert-primary  d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/role-connections/role/:id</p>
    </div>

    <div class="alert alert-success d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-success mx-4">POST</button>
        <p class="m-0 p-0">/api/role-connections</p>
        <small class="px-4">body: { role_id: Number, user_id: Number }</small>
    </div>

    <div class="alert alert-danger d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-danger mx-4">DELETE</button>
        <p class="m-0 p-0">/api/role-connections/:id</p>
    </div>
</div>

<div class="border-top border-bottom container-margin">
    <h3>Cars</h3>
    <div class="alert alert-primary d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/cars</p>
    </div>
    <div class="alert alert-primary  d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/cars/:id</p>
    </div>

    <div class="alert alert-primary  d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/cars/driver/:id</p>
    </div>

    <div class="alert alert-success d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-success mx-4">POST</button>
        <p class="m-0 p-0">/api/cars</p>
        <small class="px-4">body: { driver_id: Number, license_number: String, model: String, color: String, type_id: Number }</small>
    </div>

    <div class="alert alert-warning d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-warning mx-4">PUT</button>
        <p class="m-0 p-0">/api/cars/:id</p>
        <small class="px-4">body: { field: String, value: String }</small>
    </div>

    <div class="alert alert-danger d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-danger mx-4">DELETE</button>
        <p class="m-0 p-0">/api/cars/:id</p>
    </div>
</div>



<div class="border-top border-bottom container-margin">
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

<div class="border-top border-bottom container-margin">
    <h3>Driver Status</h3>
    <div class="alert alert-primary d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/driver-statuses</p>
    </div>

    <div class="alert alert-primary  d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-primary mx-4">GET</button>
        <p class="m-0 p-0">/api/driver-statuses/:driver_id</p>
    </div>

    <div class="alert alert-success d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-success mx-4">POST</button>
        <p class="m-0 p-0">/api/driver-statuses/</p>
        <small class="px-4">body: { driver_id: Number, status: [offline, busy, available] }</small>
    </div>

    <div class="alert alert-warning d-flex align-items-center py-2" role="alert">
        <button type="button" class="btn btn-warning mx-4">PUT</button>
        <p class="m-0 p-0">/api/driver-statuses/:driver_id</p>
        <small class="px-4">body: { status: String }</small>
    </div>
</div>
</body>
</html>