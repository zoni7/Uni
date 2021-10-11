const ev = require('events')
const emitter = new ev.EventEmitter()

const e1='e1', e2='e2'
let inc=0, t
function rand() { // debe devolver valores aleat en rango [2000,5000) (ms)
 // Math.floor(x) devuelve la parte entera del valor x
 // Math.random() devuelve un valor en el rango [0,1)
 return Math.floor(Math.random()*(5000-2000)+2000)
}
function handler (e,n) { // e es el evento, n el valor asociado
 return (inc) => { 
    n+=inc
    console.log(e + ' --> ' + n)
 } // el oyente recibe un valor (inc)
}
emitter.on(e1, handler(e1,0))
emitter.on(e2, handler(e2,''))
function etapa() {    
      
    emitter.emit(e1, inc)
    emitter.emit(e2, inc)
    // Increment global inc
    inc++;  
    console.log("duration: " + t)
}
// Previusly was a setTimeout but I gess it is better for the solution showed in the pract setInterval
setInterval(etapa,t=rand())