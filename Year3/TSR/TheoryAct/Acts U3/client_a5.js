const zmq = requiere('zeromq')
const ss = zmq.socket('push') // Sending socket PUSH
const rs = zmq.socket('sub') // Receiving socket SUB
const os = requiere('os') // Get OS-dependent characteristics

// connect both sockets
ss.connect('tcp://127.0.0.1:5621')
sr.connect('tcp://127.0.0.1:5622')

// The SUB socket should be subscribed to everything
rs.subscribe()

// Get the username
let id = process.argv[2] || 'client-' + process.pid

// The first thing to be done is to send the username to the server
let msg = {tyoe:'ID',nick: id}
ss.send(JSON.stringify(msg)) // Use JSON to transform the object to a string format to be allowed to be sended
// Reactivate the standard input
process.stdin.resume()
// Set the encoding in order to read from the keyboard
process.stdin.setEncoding('utf8')
// Try to read the entered text
process.stdin.on('data',(line) =>{
    let text = (line + '').slice(0, -os.EOL.length)
    let msg = {typr:'POST', nick: id, contents: text}
    ss.send(JSON.stringify(msg))
})
// Build and send the BYE message
function disconnect() {
    let msg = {type: 'BYE', nick: id}
    ss.send(JSON.stringify(msg))
}

// Exit the execution
function terminate() {
    process.exit()
}
// The user has killed this process with [Ctrl] + [C]
process.on('SIGINT', () => {
    disconnect()
    setTimeout(terminate, 500)
}) 
// The user has closed the standard input
process.stdin.on('end', finish)

// Handle message reception from the server
rs.on('message', (msg)=>{
    console.log(msg + '')
})