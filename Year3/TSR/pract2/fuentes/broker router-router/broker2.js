const zmq = require('zeromq')
let cli=[], req=[], workers=[]
let dealer = zmq.socket('dealer') // B2 <-- B1
let sw = zmq.socket('router') // backend
//let PORT_FRONTEND = process.argv[2]
let PORT_BACKEND = process.argv[2]
let contreq = 0 // Cont request dealer.bind('tcp://*:8002')
sw.bind('tcp://*:' + PORT_BACKEND)


 dealer.on('message',(c,sep,m)=> {
	/*
	if (workers.length==0) { 
		cli.push(c); req.push(m)
		console.log('hola')
	} else {
		sw.send([workers.shift(),'',c,'',m])
		console.log('hola')
	}
	*/
	sw.send([workers.shift(),'',b1,'',c,'',m])
	console.log(contreq++) // Print number of requests managed

})

sw.on('message',(w,sep,c,sep2,r)=> {
    if (c=='') {workers.push(w); return}
	/*
	if (cli.length>0) { 
		sw.send([w,'',
			cli.shift(),'',req.shift()])
	} else {
		workers.push(w)
	}
	*/
 dealer.send(['',c,'',r])
	console.log("response sended: " + r)
})
