const net = require('net');
const PORT = process.argv[2]
const IP = process.argv[3]
const client = net.connect({port:PORT,host:IP},
    function() { //connect listener
        console.log('client connected');
        client.write('localhost')
        
});
client.on('data',
    function(data) {
        console.log(data.toString());
        client.end(); //no more data written to the
        stream
});
client.on('end',
    function() {
    console.log('client disconnected');
    process.exit() // All processes must terminate
});
