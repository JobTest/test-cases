
* `getBalance()`

    Request
    -------
    url     http://localhost:8090/shepherdmanager/balance
    method  GET
    
    Response
    --------
    body    16895


* `getSheepStatusses()`

    Request
    -------
    url     http://localhost:8090/shepherdmanager/sheepstatusses
    method  GET

    Response
    --------
    {
        "numberOfHealthySheep": 6,
        "numberOfDeadSheep": 19
    }


* `checkSheepStatusses()`

    Request
    -------
    url     http://localhost:8090/ordermanager/checksheepstates
    method  GET

    Response
    --------
    body    true


* `postOrderSheep(<request>)`

    Request
    -------
    url     http://localhost:8090/ordermanager/ordersheep
    method  POST
    header  Content-Type = application/json;
    body
    {
        "notSheepDesired": 10
    }

    Response
    --------
    {
        "numberOfHealthySheep": 17,
        "numberOfDeadSheep": 13
    }
