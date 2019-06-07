/*
 Monopoly para cliente Inv2AS”
 Proyectos Informáticos II - 2019
 Grupo C (Juan Jose Garcia, Manuel Angel Mateos, Jaime Ojeda)
 */

public class PropiedadCasilla extends Casilla{

	
	private String aquilerUnaCasa;
	private Jugador propiedad = null;	
	private boolean hipotecada = false;
	private String alquiler;
	private String precio;
	private String costeConstruccion;
	private int casas = 0;
	private String color;
	
		//Extends from Casilla, anadiring attributes unique to buyable spaces
		public PropiedadCasilla(char tipo, String name, String color, String price, String costeConstruccion, String rent, String aquilerUnaCasa, int pos){
			super(tipo, name, pos);
			this.precio = price;
			this.costeConstruccion = costeConstruccion;			
			this.aquilerUnaCasa = aquilerUnaCasa;
			this.alquiler = alquiler;
			this.color = color;
		
		}

		public PropiedadCasilla() {} //blank constructor for use in special cases in other classes


		public void ponerPropiedad(Jugador ownership) {
			this.propiedad = ownership;
		}

		public Jugador obtenerPropiedad() {
			return propiedad;
		}

		public void ponerHipotecada(boolean hipotecada) {
			this.hipotecada = hipotecada;
		}

		public boolean estaHipotecada() {
			return hipotecada;
		}

		public void ponerAlquiler(String rent) {
			this.alquiler = alquiler;
		}

		public String obtenerAlquiler() {
			return alquiler;
		}

		public void ponerPrecio(String price) {
			this.precio = price;
		}

		public String obtenerPrecio() {
			return precio;
		}

		public void ponerCasas(int houses) {
			this.casas = houses;
		}

		public int obtenerCasas() {
			return casas;
		}
		

		public void setcosteConstruccion(String costeConstruccion) {
			this.costeConstruccion = costeConstruccion;
		}

		public String obtenerCosteConstruccion() {
			return costeConstruccion;
		}

		public void ponerAlquilerUnaCasa(String aquilerUnaCasa) {
			this.aquilerUnaCasa = aquilerUnaCasa;
		}

		public String obtenerAquilerUnaCasa() {
			return aquilerUnaCasa;
		}

		public void ponerColor(String color) {
			this.color = color;
		}

		public String obtenerColor() {
			return color;
		}

}
