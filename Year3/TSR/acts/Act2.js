const ev = require('events')
const emitter = new ev.EventEmitter
const e1 = "print"
const e2 = "read"
const e3 = "event 3"
const books = [ "Walk Me Home", "When I Found You", "Jane's Melody", "Pulse" ]

let num1 = 0
let num2 = 0
let counter=2000;

// Listener for event e1.
function listener1() {
	num1++
	console.log( "Active listener" )
}

// Listener for event e2.
function listener2() {
	num2++
	if (num1 < num2) console.log( "Evenmt two" )
	else { console.log( "I have received more events of type one" )}
}

// Listener for event e3.
function listener3() {	
	console.log("Event three")
	clearInterval(e2)
	setInterval(generateEvent2 , counter)

}
// Listeners are registered in the event emitter.
emitter.on(e1, listener1)
emitter.on(e2, listener2)
emitter.on(e3, listener3)

// Auxiliary function for generating e2.

function generateEvent2() {
	counter= counter + 1000
	// This second argument provides the argument for the "e2" listener.
	emitter.emit(e2)
}
// Generate the events periodically...
// First event generated every 2 seconds.
setInterval( function() {
	emitter.emit(e1)
}, 1000 )
// Second event generated every 3 seconds.
setInterval( generateEvent2, 2000 )
// Third event generated every 10 seconds.
setInterval(function() {
	emitter.emit(e3)
}, 10000 )
