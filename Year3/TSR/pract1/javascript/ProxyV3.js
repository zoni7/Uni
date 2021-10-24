const net = require('net');
const { argv } = require('process');
const LOCAL_PORT = 8000;
const LOCAL_IP = '127.0.0.1';

// Connect to browser and send the request
const server = net.createServer(function (socket) {
    const serviceSocket = new net.Socket();
    // Connect to web page demanded by the PROGRAMADOR and send it to the browser
    serviceSocket.connect(parseInt(REMOTE_PORT), REMOTE_IP, function () {
        socket.on('data', function (msg) {
            serviceSocket.write(msg);          
        });
        serviceSocket.on('data', function (data) {
            socket.write(data);
        });
        /* ----------- WORKING ON IT --------------------
        RECONNECT IN CASE OF ERROR
        serviceSocket.on('error', function (error) {        
            if (error.syscall == 'connect') {
                serviceCocket.removeAll
            }   crearServer();
            })
            */
    });
}).listen(LOCAL_PORT, LOCAL_IP);


// Connect with Programador
const server2 = net.createServer(function (socket) {
    socket.on('data', function (msg) {
        let data = JSON.parse(msg);
        REMOTE_IP = data.remote_ip;
        REMOTE_PORT = data.remote_port;
    });
}).listen(8001, LOCAL_IP);


console.log("TCP server accepting connection on port: " + LOCAL_PORT);

