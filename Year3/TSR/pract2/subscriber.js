const zmq = require('zeromq')
const sub = zmq.socket('sub')

const PORT = process.argv[2]
sub.connect('tcp://127.0.0.1:' + PORT)
sub.subscribe('')

sub.on('message', (msg, id) => {
    console.log(msg + ' ' +id)
})