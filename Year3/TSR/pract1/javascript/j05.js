
//Uso de operaciones asíncronas, aquí modeladas con la función setTimeout.
//Note el valor de índice asociado a las ejecuciones de las temporizaciones.


for(var i=0; i<10; i++) 
  setTimeout(function(índice){
	  return function(){console.log("índice: ",índice)}}(i) /** Esta i pone el valor de indice  */,i*1000);
// Al tener el parametro de índice el valor será el pasado por el argumento

console.log("Terminado codigo script  valor actual de i: ",i);

