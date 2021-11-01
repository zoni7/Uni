const zmq = require("zeromq")
const consumer = zmq.socket("pull")
consumer.connect("tcp://127.0.0.1:8888")

consumer.on("message", function(msg) {
    console.log("received: " + msg)
})