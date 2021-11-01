const net = require('net')
const server = net.createServer((c)=>{
	console.log("client connected")
    // Events
    //c.write('Hello')
    c.on('end',
    function() {
        console.log('server: client disconnected');
    });

    c.on('data', function(data){
        console.log('Client has sent: ' + data)
    })
})
server.listen(8000)

