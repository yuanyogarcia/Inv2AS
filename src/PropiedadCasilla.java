/*
 Monopoly para cliente Inv2ASâ€�
 Proyectos InformÃ¡ticos II - 2019
 Grupo C (Juan Jose Garcia, Manuel Angel Mateos, Jaime Ojeda)
 */

public class PropiedadCasilla extends Casilla{

	
	private String aquilerUnaCasa;
	private Jugador propiedad = null;	
	private String hipotecada = "false";
	private String alquiler;
	private String precio;
	private String costeConstruccion;
	private int casas = 0;
	private String color;
	
		//Se extiende desde casilla atributos Ãºnicos 
		public PropiedadCasilla(char tipo, String name, String color, String price, String costeConstruccion, String rent, String aquilerUnaCasa, int pos){
			super(tipo, name, pos);
			this.precio = price;
			this.costeConstruccion = costeConstruccion;			
			this.aquilerUnaCasa = aquilerUnaCasa;
			this.alquiler = alquiler;
			this.color = color;
		
		}

		public PropiedadCasilla() {} // Constructor en blanco para uso en casos especiales en otras clases.



		public void ponerPropiedad(Jugador ownership) {
			this.propiedad = ownership;
		}

		public Jugador obtenerPropiedad() {
			return propiedad;
		}

		public void ponerHipotecada(String hipotecada) {
			this.hipotecada = hipotecada;
		}

		public String estaHipotecada() {
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
 }