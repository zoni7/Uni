const zmq = require('zeromq')
const pub = zmq.socket('pub')
const PORT = process.argv[2]
const numMessages = process.argv[3]
// bind publisher
pub.bind('tcp://127.0.0.1:' + PORT)

for(let i=1; i <= numMessages; i++) {
    for(let j=4; j < process.argv.length; j++){
        setTimeout(() => {pub.send([process.argv[j],i])}, 1000)
    }
}


