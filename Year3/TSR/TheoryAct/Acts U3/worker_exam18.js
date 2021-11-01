const zmq = require('zmq')
let pull = zmq.socket('pull')
let push = zmq.socket('push')
let math = require('./myMath.js')

pull.connect('tcp://127.0.0.1:8001')
push.connect('tcp://127.0.0.1:8002')

// Worker ID
let id = process.argv[2]
pull.on('message', function(msg){
    push.send([id, msg, math.factP(ParseInt(msg))])
})