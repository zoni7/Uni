const http = require('http');
function dd(i) {return (i<10?"0":"")+i;}
const server = http.createServer(
    function (req,res) {
        // get the url of the request
        const url = req.url;
        // Send information to the browser
        res.writeHead(200,{'Content-Type':'text/html'});
        res.end(`<marquee>Node y Http ${url} </marquee>`); // marquee makes the effect of movement
        var d = new Date();
        console.log('alguien ha accedido a las '+
        d.getHours() +":"+
        dd(d.getMinutes()) +":"+
        dd(d.getSeconds()));
    }).listen(8000);