//Uso de operaciones asíncronas, aquí modeladas con la función setTimeout.
//Note el valor de i asociado a las ejecuciones de las temporizaciones.
//Uso de la sentencia let.

let i = 0;

do {
	setTimeout(function(){console.log(i)},i*1000);
	i++;  
} while (i<10);
// i will be 10 always as it is in the global scope
console.log("Terminado codigo script  valor actual de i: ",i);
