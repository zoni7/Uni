const { time } = require('console');
const http = require('http')
const fs = require('fs');
const path = require('path')
//joining path of directory 
const directoryPath = path.join(__dirname);

http.createServer( function(request,response) {
	let url = request.url.slice(1)
	//response.end(`<marquee>Node y Http  </marquee>`);
	response.writeHead(200, {'Content-Type':'text/plain'})
	let time = new Date()
	switch( url ) {
		case 'time': response.end('TIME = ' + time ); break;
		case 'dir': readdirJ(response); break;
		default: fileDetected(url, response)		
					
	}
}).listen('8000')

// Get the Path of the actual directory
function queryDir(){
	return __dirname
}

// Get the Files
function readdirJ(response) {
	let fileList = ''
	//passsing directoryPath and callback function
	fs.readdir(directoryPath, function (err, files) {
		//handling error
		if (err) {
			return console.log('Unable to scan directory: ' + err);
		} 
		
		//listing all files using forEach
		files.forEach(function (file) {			
			// Do whatever you want to do with the file
			fileList += file + ' ' 
			console.log(file)	
		});
		setTimeout(()=>response.end("Directory: " +queryDir() + " \n Files:  "  + fileList), 2000)
	});
	
}

function fileDetected(url, response) {
	let result
	try {
		result = fs.readFileSync('./' + url, 'utf8') 
		response.end(result)
	} catch(e) {}
}


