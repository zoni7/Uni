
//Ejercicio de recapitulación (operaciones asíncronas y clausuras):
//     - ¿Cuál es el resultado de la ejecución de este código?
//     - Una vez contestada la cuestión anterior ejecute el código 
//       compruebe y la validez de la respuesta.



for (var i=0;i<5;i++) 
   (function(){
       setTimeout( function(){console.log("---> ",i);}, i*1000 );})(); // la i es global
         
         
for (var k=0;k<3;k++) 
   (function(k){
       setTimeout( 
         function(){console.log("==========> ",k);}, k*4010);})(k); // Pasa la k como argumento
         
	   
for (var x=0;x<3;x++){ 
   (function(){
	   var mm=x; // la asignacion se hace antes del Timeout
       setTimeout( function(){
		   var m=x; // la asignación se hace en el final del timeout
		   console.log("::::::::::::::> ",m,"   ",mm);}, x*8030);})();
}




