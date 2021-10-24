// Library
const zmq = require("zeromq");
// Socket pull type
const sock = zmq.socket("pull");
// Run method
run();
async function run() {

    await sock.connect("tcp://127.0.0.1:7000");
    console.log("Connected to server.")

    /* DOESN'T WORK WITH THAT WAY
    for await (const msg of sock.removeListener) { 
        console.log(`received job ${msg.toString()}`);
    }
    */
}

sock.on("message", function(msg) { // for every message received
    console.log("work: %s", msg.toString());
  });
  

// DOCUMENTATION
// https://zeromq.org/languages/nodejs/
