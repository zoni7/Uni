const zmq = require('zeromq')
const nick= process.argv[2]
let sub = zmq.socket('sub') 
let psh = zmq.socket('push')
sub.connect('tcp://127.0.0.1:9998')
psh.connect('tcp://127.0.0.1:9999')
sub.subscribe('') // subscribe to every person in the chat
sub.on('message', (nick,m) => {
	console.log('['+nick+']'+m)
})

// READ FROM THE CONSOLE
process.stdin.resume()
process.stdin.setEncoding('utf8')
// Listener for the event of writting something
process.stdin.on('data'  ,(str)=> {
	psh.send([nick, str.slice(0,-1)]) // send the message to the server
})
// When we end the process
process.stdin.on('end',()=> {
	psh.send([nick, 'BYE'])
	sub.close(); psh.close()
})
process.on('SIGINT',()=> {
	process.stdin.end()
})
psh.send([nick,'HI'])
