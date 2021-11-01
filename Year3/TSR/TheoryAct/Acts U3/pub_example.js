const zmq = require("zeromq")
const pub = zmq.socket('pub')
let count = 0
pub.bindSync("tcp://*:5555")

setInterval(function() {
    pub.send("TEST " + count++)
}, 1000)