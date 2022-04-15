// EJERCIO
/**
 * ount elements in an array without counting repeted ones
 */

let array = [] 
let arrayAux = []
let res = 0

// Pick arguments
for (let i = 2; i < process.argv.length; i++) {
    array[i-2] = process.argv[i]
}

function countElements() {
    array.forEach(element => { 
        // Check if it is already counted 
        if (!arrayAux.includes(element))  {
            res++
            arrayAux.push(element)
        }                                   

    });
    return res
}

// MAIN
console.log(countElements())