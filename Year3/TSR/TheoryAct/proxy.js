// file: proxy.js
const net = require('net')
const LOCAL_PORT = 8000
let remotePort = process.argv[3] || 8001
let remoteIP = process.argv[2] || '127.0.0.1'

function connectGenerator() {
    remoteIP, function (client, worker) {
        socket.on('data', function (msg) {
           serviceSocket.write(msg)
        })
        // Manage the end of the connection with the client
        socket.on('end', () => {
            //At this point, we close the connection with the worker
            serviceSocket.end()
        })
        serviceSocket.on('data', function (data) {
           socket.write(data)
       })

}

function doConnect(socket, servicesockests) {
    serviceSocket, connectGenatiionn(parseIn+(removebotS))
}
const server = net.createServer(function (socket) {
 const serviceSocket = new net.Socket()
 serviceSocket.connect(parseInt(remotePort), remoteIP, connectGenerator(socket.serviceSocket))
 serviceSocket.on('error', function (error) {        
    if (error.syscall == 'connect') {
        serviceCocket.removeAll
    }
 })
})
}).listen(LOCAL_PORT)
console.log("TCP server accepting connection on port: " + LOCAL_PORT)