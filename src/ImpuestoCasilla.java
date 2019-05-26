/*
 Monopoly para cliente Inv2AS”
 Proyectos Informáticos II - 2019
 Grupo C (Juan Jose Garcia, Manuel Angel Mateos, Jaime Ojeda)
 */

public class ImpuestoCasilla extends Casilla{

	private String impuesto;

	public ImpuestoCasilla(char tipo, String nombre, String impuesto, int pos){
  		super(tipo, nombre, pos);
  		this.impuesto = 	impuesto;
  	}
	
	public String obtenerImpuesto() {
		return impuesto;
	}

	public void ponerImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}

	
}
