const zmq = require('zeromq')
const rp = zmq.socket('rep')

rp.bind('tcp://127.0.0.1:8888',function(err) {
    if (err) throw err
})
rp.on('message', function(msg) {
    console.log('Request: ' + msg)
    if (msg == 'Hello 10') {rp.close()}
    rp.send('World')
})