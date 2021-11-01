
const ev = require('events')
const emiter = new ev.EventEmitter
// Listeners to this particular event created
emiter.on("print", function() {
    console.log("hello world")
})
emiter.on("print", function() {
    console.log("hello world")
})
emiter.on("love", function(name) {
    console.log("I love you " + name)
})
// Emit the chosen event or events
emiter.emit("print", 'Anna')
