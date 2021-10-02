
//Clausuras de variables y funciones.

var gety;

function main(y){

	var traza="inicio";
	gety=getY;
	return f(y); // Al retornar el valor de f(y) cada nova cridada al metode main mantendrà el valor privat "y"
	
//   - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	function getY(){return y;}

	function f(y){
		var x=100+y;
		
		console.log("\ttraza: ", traza);
		
		if(x%2)return g0;
		   else return g1;

		function g0(){
			traza += "-g0";
			x++;
			console.log("g0: incremento de x:  "+x);
			return f(++y);
		}
		
		function g1(){
			traza += "-g1";
			y++;
			console.log("g1: incremento de y:  "+y);
			return f(y);	
		}
	}
}


var z=main(-100);

for(var i=0; i<10;i++) z=z();

//¿Cuál es el resultado de la ejecución de la sentencia
console.log(gety());
// -100 porque la y de gety() se refiere a la y de la clousure de la funcion main y no a la de f(y) que tiene un scope diferente

