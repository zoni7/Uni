const zmq = requiere('zeromq')
const rs = zmq.socket('pull') // Sending socket pull
const ss = zmq.socket('pub') // Receiving socket pub


// connect both sockets
rs.bindSynch('tcp://127.0.0.1:5621')
ss.bindSynch('tcp://127.0.0.1:5622')

// Handle incoming messages
rs.on('message', (msg) => {
    let request = JSON.parse(msg)
    let response = ''
    switch(request.type) {
        case 'ID':
            response = 'server> ' + request.nick + ' has connected to the chat service!'
            break;
        case 'POST':
            response = request.nick + '>' + request.contents
            break;
        case 'BYE':
            response = 'BYE' + request.nick

    }
    ss.send(response)
})