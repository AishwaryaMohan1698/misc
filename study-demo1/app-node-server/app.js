'use strict';
var express = require('express');
var app = express();

// ---------------------- SERVER HANDLING ------------------------------------------

var server = app.listen(8060, 'localhost', function () {
    var host = server.address().address;
    var port = server.address().port;
    console.log('Listening at http://%s:%s', host, port);
});

// ------------------ Eureka Config --------------------------------------------
var Eureka = require("eureka-js-client").Eureka;

var client = new Eureka({
    filename: 'eureka-client',  // filename can be changed
    cwd: __dirname   // can be altered to specify directory
});
client.logger.level('debug');
client.start(function (error) {  // register eureka client
    console.log(error || 'Registration complete');
});

// on exit - de-register eureka
function exitHandler(deRegisterChoice) {
    if (this.deRegisterChoice) {
        client.stop();
        console.log('Client De-registered');
    }
    process.exit();
}





// ------------------ METHODS --------------------------------------------------

app.all('/', function (req, res) {
    var port = server.address().port;
    var msg = 'Hello Service ' + port;
    res.status(200);
    res.end(msg);
})


app.get('/test', function (req, res) {
    var msg = 'Test Endpoint (/node/test) display';
    res.status(200);
    res.end(msg);
})

app.get('/getMessage', function (req, res) {
    var msg = 'Test Endpoint (/node/test/getMessage) display';
    res.status(200);
    res.send(msg);
})



//do something when app is closing
process.on('SIGINT' || 'exit', exitHandler.bind({ deRegisterChoice: true }));
