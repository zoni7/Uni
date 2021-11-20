const zmq = require('zeromq')
let cli=[], req=[], workers=[]
let sc = zmq.socket('router') // frontend
let dealerB1 = zmq.socket('dealer') // Dealer B1 --> B2
let PORT_FRONTEND = process.argv[2]
//let PORT_BACKEND = process.argv[3]
let contreq = 0 // Cont request
sc.bind('tcp://*:' + PORT_FRONTEND)
dealerB1.connect('tcp://localhost:8002')
sc.on('message',(c,sep,m)=> {
	/*
	if (workers.length==0) { 
		cli.push(c); req.push(m)
		//console.log("hola")
	} else {
		dealerB1.send([workers.shift(),'',c,'',m])
	}
	*/
	dealerB1.send([c,'',m])
	console.log(contreq++) // Print number of requests managed

})

dealerB1.on('message',(sep, c,sep2,r)=> {
	console.log("received the message: " + r)

    //if (c=='') {workers.push(w); return}
	if (cli.length>0) { 
		dealerB1.send(
			[cli.shift(),'',req.shift()])			
	} 
	sc.send([c,sep1,r])
})
