const net = require('net');
const { argv } = require('process');
//const LOCAL_PORT = Number(argv[4]);
//const LOCAL_IP = argv[5].toString();
const NEW_PORT = Number(argv[2]);
const NEW_IP = argv[3].toString(); // www.upv.es
const client = net.connect({port:8001,host:"localhost"},
    function() { //connect listener
        console.log('programador connected');    
        // Pack the message in JSON format    
        var msg = JSON.stringify ({'remote_ip':NEW_IP, 'remote_port':NEW_PORT})
        // Send the message
        client.write(msg);
});

    
