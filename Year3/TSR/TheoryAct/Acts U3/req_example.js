const zmq = require('zeromq')
const rq = zmq.socket('req')

rq.connect('tcp://127.0.0.1:8888')
rq.send('Hello')
rq.on('message', function(msg) {
    console.log()('Response:' + msg)
})

