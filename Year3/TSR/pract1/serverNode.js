const net = require('net')
const server = net.createServer((c)=>{
    console.log("client connected")
    // Events
    c.write('Hello')

    c.on('end',
    function() {
        console.log('server: client disconnected');
});
})

server.listen(8000)

