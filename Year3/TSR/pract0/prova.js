/*
function a() {
    let a1=1
    b(a1)
}

function b(p1) {
    let b1=2
    console.log(p1)
    console.log(b1)
    console.log(gl1)
}
a()
var gl1=0
*/
/*
function a() {
    let a1=1
    b(a1)
    function b(p1) {
        let b1=2
        console.log(p1)
        console.log(b1)
        console.log(gl1)
    }
}


a()
let gl1=0
*/

// FUNCTIONS TO PRACTISE
function x(a, b , ... args) {
    for (let i in args) {
        console.log("%dth arg: %s", i , args[i])
    }
    //console.log(arguments[1])
}

function f (a,b,c = "hola", d = 4) {
    console.log(a)
    console.log(b)
    console.log(c)
    console.log(d)
}

x = 1

function v() {
    var x = 2
    console.log(x)
    console.log(global.x)
}

function fibo(n) {
    return (n<2) ? 1: fibo(n-2) + fibo(n-1)
}

setTimeout(function() {
    console.lo
})
// OUTPUTS
//x(1,2,4)
//f(2)
//v()