const net = require('net');
const { argv } = require('process');
const LOCAL_PORT = 8000;
const LOCAL_IP = '127.0.0.1';
const REMOTE_PORT = argv[2]; // argv[2] is 80 for instance
const REMOTE_IP = argv[3];  // argv[3] is www.upv.es for instance
const server = net.createServer(function (socket) {
    const serviceSocket = new net.Socket();
    serviceSocket.connect(parseInt(REMOTE_PORT), REMOTE_IP, function () {
        socket.on('data', function (msg) {
            serviceSocket.write(msg);
        });
        serviceSocket.on('data', function (data) {
            socket.write(data);
            });
    });
}).listen(LOCAL_PORT, LOCAL_IP);


console.log("TCP server accepting connection on port: " + LOCAL_PORT);
