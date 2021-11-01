const zmq = require("zeromq")
const sub = zmq.socket('sub')
sub.connect("tcp://localhost:5555")

sub.subscribe("TEST")

sub.on("message", function(msg) {
    console.log("Received: " + msg)
})