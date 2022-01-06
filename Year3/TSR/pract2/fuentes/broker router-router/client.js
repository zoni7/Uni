const zmq = require('zeromq')
let req = zmq.socket('req');
let URL = process.argv[2] 
let NICKNAME = process.argv[3]
let TXTREQUEST = process.argv[4]
req.connect(URL)
req.on('message', (msg)=> {
	console.log("received")
	console.log('resp: '+msg)
	process.exit(0); // when it receive the desponse the client stops
})
req.send([NICKNAME,'',TXTREQUEST])
