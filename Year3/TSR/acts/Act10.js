const fs = require('fs')

// process.argv is an array with all command arguments
const names = process.argv.slice(2)
let contents = {}
let counter = 0 // number of files processed

// Cheack if there are enouth arguments
if (names.length == 0) {
    console.log("You need 5 names maquina")
}
// SYNCHRONOUS VARIANT
names.forEach((name) => { 
    try {
        console.log(fs.readFileSync(name)+ '')
    } catch (e){
        console.log(e)
    }

} ) // an other alternative to these code is a for loop more similar as in java

// ASYNCHRONOUS VARIANT
names.forEach((name) => { 
    fs.readFile(name, (err, data)=> {
        if (err) {
            contents[name] = ''
         }
         else {
             contents[name] = data + ""
         }
        counter++
        if (counter == names.length) { // We check if we are in the last one
            names.forEach((n) =>  {
                console.log(contents[n])
            })
        }
    })

} )