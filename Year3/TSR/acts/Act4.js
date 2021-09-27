const http = require('http')
const url = require('url')
const qs = require('querystring')
http.createServer( function(request,response) {
	let query = url.parse(request.url).query
	let info = qs.parse(query).info
	let x = '2015'
	let y = '1492'
	response.writeHead(200, {'Content-Type':'text/plain'})
	switch( info ) {
		case 'x': response.end('Value = ' + x); break;
		case 'y': response.end('Value = ' + y); break;
	}
}).listen('1337')
