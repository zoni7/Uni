const net = require('net')

const client = net.connect(8000, function() {
    console.log("Client connected")
})

// Events
client.on("data", function(data){
    console.log(data + " world")
    client.end()
})

client.on("end", function(){
    console.log("client disconnected")
})