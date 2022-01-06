const zmq = require('zeromq')
// Buffers to store requests and identifiers
let cli=[], req=[]

// We need a variable to count workers free
let freeWorkers = 0
let sc = zmq.socket('router') // frontend
let sb = zmq.socket('dealer') //broker 2

let PORT_FRONTEND = process.argv[2] || '8000'
let PORT_BROKER = process.argv[3] || '8001'
let contreq = 0 // Cont request

// BINDINGS
sc.bind('tcp://*:' + PORT_FRONTEND)
sb.bind('tcp://*:' + PORT_BROKER)

sc.on('message',(c,sep,m)=> {
	// Queue client request
	console.log("pushed")
	cli.push(c); req.push(m)
	
	while(freeWorkers > 0 && cli.length > 0) {
		sb.send([c,'',m])
		freeWorkers--; // We reduce the number of workers
		console.log("\t FWD TO B2==> CLI: " + c + " / MSG: " + m  + "\nNumber of requests " + ++contreq)
	}

})

sb.on('message',(sep, c,sep2,r)=> {	
	freeWorkers++
	console.log("BROKER 1 => FWD TO C: " + c + " / REP: " + r + "(numFree = " + freeWorkers + ")")
    if (c=='') {freeWorkers; return}
	while (cli.length>0 && freeWorkers > 0) { //while there are free workers and there are client messages 
		freeWorkers--
		let client = cli.shift()
		let message = req.shift()
		sb.send(
			[client,'',message])
			console.log("\t FWD TO B2==> CLI: " + client + " / MSG: " + message)			
	} 
	if (c!='') sc.send([c,'',r])
})
