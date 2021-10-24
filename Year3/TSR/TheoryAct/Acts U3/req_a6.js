const { setInterval } = require('timers/promises')
const zmq = require('zeromq')
const rq = zmq.socket('req')
// Act2 identify the messages
let id = 1; // Id of the message
rq.connect('tcp://127.0.0.1:8888')
// Act1 send the FIRST message sended
rq.send('Hello ' + id++)


rq.on('message', function(msg) {
 console.log('Response: ' + msg)
 // set interval when receiving the response
 rq.send('Hello ' + id++)
})