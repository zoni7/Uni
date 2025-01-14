const zmq = require('zeromq')
let req = zmq.socket('req')
let URL = process.argv[2] 
let NICKNAME = process.argv[3]
let TXTREPLAY = process.argv[4] 
let contReqManaged = 0;

req.identity=NICKNAME+process.pid
req.connect(URL)
req.on('message', (c,sep,msg)=> {
	console.log('ROGER THAT')
	setTimeout(()=> {
		req.send([c,'',TXTREPLAY + ' and ' + NICKNAME + ' managed ' + ++contReqManaged])
		console.log(contReqManaged);
	}, 1000)
})
req.send(['','','']) // Initial request 
