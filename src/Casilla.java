/*
 Monopoly para cliente Inv2AS”
 Proyectos Informáticos II - 2019
 Grupo C (Juan Jose Garcia, Manuel Angel Mateos, Jaime Ojeda)
 */

public class Casilla {
  
  private int pos;
  private char tipo;
  private String name;				
  private Casilla siguiente; 

  	public Casilla(){}
  	
  	public Casilla(char tipo, String name, int pos){ 
  		this.tipo = tipo;
  		this.name = name;		
  		this.pos = pos;
  	}

	public void ponerPosicion(int pos) {
	//  this.pos = pos;
  	}
  	public int obtenerPosicion() {
  		return pos;
  	}
	public void ponerTipo(char tipo) {
		this.tipo = tipo;
	}
	
	public char obtenerTipo() {
		return tipo;
	}
	
	public void ponerNombre(String name) {
		this.name = name;
	}
	
	public String obtenerNombre() {
		return name;
	}
	
	public void ponerSiguiente(Casilla siguiente) {
		this.siguiente = siguiente;
	}
	public Casilla obtenerSiguiente() {
		return siguiente;
	}	

}
