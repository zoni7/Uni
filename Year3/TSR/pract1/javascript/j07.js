//Uso de operaciones asíncronas, aquí modeladas con la función setTimeout.
//Note el valor de i asociado a las ejecuciones de las temporizaciones.
//Uso de la sentencia let.

var i = 0;

do {
	let k = i; // El let k permite que la variable k se mantenga en cada bloque, debe de existir un scope para cada bucle
	setTimeout(function(){console.log(k)},k*1000);
	i++;
    
} while (i<10);

console.log("Terminado codigo script","   valor actual de i: ",i);
