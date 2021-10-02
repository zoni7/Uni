const fs = require('fs')
fs.length = function (filename) {
    let len
    try {
        len = fs.readFileSync(filename).length
    } catch (e) {
        return -1
    }
    
    return len
}

module.exports = fs