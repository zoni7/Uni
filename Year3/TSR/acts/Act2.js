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
	console.log( "Event one has happened " + num1 + " times" )
}

// Listener for event e2.
function listener2() {
	num2++
	if (num1 < num2) console.log( "Evenmt two: " + num2 + " times")
	else { console.log( "I have received more events of type one" )}
	setInterval( generateEvent2, counter )
}

// Listener for event e3.
function listener3() {	
	console.log("Event three")
	if (counter < 18000) counter *= 3	

}
// Listeners are registered in the event emitter.
emitter.on(e1, listener1)
emitter.on(e2, listener2)
emitter.on(e3, listener3)

// Auxiliary function for generating e2.

function generateEvent2() {
	
	// This second argument provides the argument for the "e2" listener.
	emitter.emit(e2)
}
// Generate the events periodically...
// First event generated every 2 seconds.
setInterval( function() {
	emitter.emit(e1)
}, 3000 )
// Second event generated
setInterval( generateEvent2, counter )
// Third event generated every 10 seconds.
setInterval(function() {
	emitter.emit(e3)
}, 10000 )
