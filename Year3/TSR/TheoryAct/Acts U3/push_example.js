const zmq = require("zeromq")
const producer = zmq.socket("push")
let count = 0
producer.bind("tcp://*:8888", function(err) {
    if (err) throw err
    setInterval(function() {
        let t = producer.send("msg nr. " + count++)
        console.log(t)
    }, 1000)
})
