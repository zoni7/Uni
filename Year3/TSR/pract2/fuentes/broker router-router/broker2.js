const zmq = require('zeromq')
let workers=[]
let sb = zmq.socket('dealer') // broker1
let sw = zmq.socket('router') // backend
let PORT_BROKER = process.argv[2] || '8001'
let PORT_BACKEND = process.argv[3] || '8002'

//Connect the B1 dealer
sb.connect('tcp://localhost:' + PORT_BROKER)
// Bind for the req of the worker
sw.bind('tcp://*:' + PORT_BACKEND)


sb.on('message',(c,sep,m)=> {
	
	// This will not happen because in broker1 we take care of available workers
	if (workers.length==0) { 		
		console.log('No worker available packet dropped')
		return
	}

	let worker = workers.shift()
	sw.send([worker,'',c,'',m])
	console.log("BROKER 2 ==> FROM CLI: " + c + " / TO WOR: " + worker + " / CONTENT: " + m + "(freeWorkers=" + workers.length + ")")


})

sw.on('message',(w,sep,c,sep2,r)=> {
    // Add the worker to the available workers list
	workers.push(w)	
	
 	sb.send(['',c,'',r])
	console.log("BROKER 2 ==> FROM WOR: " + w + " / TO: CLI: " + c + " / CONTENT: " + r)
	
})
