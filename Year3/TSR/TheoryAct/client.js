// File: client.js
const net = require('net')
const client = net.connect(parseInt(process.argv[2]) || 8000, 
 function() { //connect listener
 console.log('client connected')
 client.write(process.pid+'')
 })
//-------------------EX 4------------------------------------------------
 // Function used to reconnect in case of error
function doConnect() {
   // Remove All previusly listeners to get just the final one
   client.removeAllListeners('connect')
   client.connect(parseInt(process.argv[2]) || 8000, 
   function() { //connect listener
   console.log('client connected')
   client.write(process.pid+'')
   })
} 
//-----------------------------------------------------------------------
client.on('data', 
 function(data) {
    console.log(data.toString())
    client.end(); // Close the connection when data is received
 })
client.on('end', 
 function() {
 console.log('client disconnected')
 })

 //-----------------------EX 4-------------------------------------
 // Detecting errrors
client.on('error', 
 function(error) {
    // If the type of error if from the connection, reconnect
    if (error.syscall == 'connect') {
      setTimeout(doConnect, 1000)
    }
  //---------------------------------------------------------------
 })
