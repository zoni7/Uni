//Uso de operaciones asíncronas, aquí modeladas con la función setTimeout.
//Note el valor de i asociado a las ejecuciones de las temporizaciones.
//Uso de la sentencia let.

for(let i=0; i<10; i++) 
  setTimeout(function(x){return function(){console.log(x)}}(i),i*1000);

//¿Cuál es el resultado de la ejecución de la sentencia
console.log("i= ",i); 
//con un var la i está dentro de una funcion, en este caso al no haber funcion es la global. Pero con let el scope es solo del for 

console.log("Terminado codigo script");


