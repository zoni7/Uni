const zmq = require('zeromq')
let cli=[], req=[], workers=[]
let sc = zmq.socket('router') // frontend
let sw = zmq.socket('router') // backend
let PORT_FRONTEND = process.argv[2]
//let PORT_BACKEND = process.argv[3]
let contreq = 0 // Cont request
sc.bind('tcp://*:' + PORT_FRONTEND)
//sw.bind('tcp://*:' + PORT_BACKEND)
sc.on('message',(c,sep,m)=> {
	if (workers.length==0) { 
		cli.push(c); req.push(m)
	} else {
		sw.send([workers.shift(),'',c,'',m])
	}
	console.log(contreq++) // Print number of requests managed

})
/*
sw.on('message',(w,sep,c,sep2,r)=> {
    if (c=='') {workers.push(w); return}
	if (cli.length>0) { 
		sw.send([w,'',
			cli.shift(),'',req.shift()])
	} else {
		workers.push(w)
	}
	sc.send([c,'',r])
})
*/