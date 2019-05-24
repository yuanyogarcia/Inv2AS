/*
 Monopoly para cliente Inv2AS”
 Proyectos Informáticos II - 2019
 Grupo C (Juan Jose Garcia, Manuel Angel Mateos, Jaime Ojeda)
 */

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Jugador {

	private String nombre;
	private int rCuenta;
	private int mCuenta;
	private int dCuenta;
	private int jfCuenta;
	private int nDado1, dado2;
	private boolean enDado2 = false;
	private int dadoTotal;
	private Casilla nodoActual = new Casilla();
	private ArrayList<PropiedadCasilla> propPropias;
	private int tiempoCarcel;

	// Objeto que representa cada jugador en el juego
	public Jugador() {
	}

	public Jugador(String name) {
		this.nombre = nombre;
		this.dCuenta = 0;
		this.mCuenta = 1500;
	}

	public void dineroNeto(int valor) {
		mCuenta += valor;

	}

	// Mueve al jugador a través de la la lista de casillas
	public void mover() {

		for (int j = 0; j < this.obtenerRCuenta(); j++) {
			this.ponerNodoActual(this.obtenerCNode().obtenerSiguiente());
			if (nodoActual.obtenerPosicion() == 1) {
				this.ponerMCuenta(this.obtenerMCuenta() + 200);
			}
		}
	}

	public void moverAIdentidad(int moverAIdentidad) {

		if (moverAIdentidad == 0) {

		} else if (moverAIdentidad == 11) {

		} else {

		}

	}

	public void tirar() {
		if (enDado2 == false) {
			this.ponerDado1((int) (6 * Math.random() + 1));
			this.enDado2 = true;
			this.tirar();
		} else {
			this.ponerDado2((int) (6 * Math.random() + 1));
			dadoTotal = this.obtenerDado1() + this.obtenerDado2();
			this.rCuenta = dadoTotal;
			enDado2 = false;
		}
	}

	public void negociar(Jugador negociador2, int dinero) {
		int currentT1MC = this.obtenerMCuenta();
		int currentT2MC = negociador2.obtenerMCuenta();
		this.ponerMCuenta(currentT1MC - dinero);
		negociador2.ponerMCuenta(currentT2MC + dinero);
	}




	public void ponerNombre(String nNombre) {
		this.nombre = nNombre;
	}

	public String obtenerNombre() {
		return nombre;
	}

	public int obtenerDCuenta() {
		return dCuenta;
	}

	public void ponerDCuenta(int ndCuenta) {
		this.dCuenta = ndCuenta;
	}

	public int obtenerRCuenta() {
		return rCuenta;
	}

	public void ponerRCuenta(int nrCuenta) {
		this.rCuenta = nrCuenta;
	}

	public int obtenerMCuenta() {
		return mCuenta;
	}

	public void ponerMCuenta(int nmCuenta) {
		this.mCuenta = nmCuenta;
	}

	public int obtenerDado1() {
		return nDado1;
	}

	public int obtenerDado2() {
		return dado2;
	}

	public void ponerDado1(int nDado1) {
		this.nDado1 = nDado1;
	}

	public void ponerDado2(int nDado2) {
		this.dado2 = nDado2;
	}
	
	public int obtenerjfCuenta() {
		return jfCuenta;
	}
	
	public ArrayList<PropiedadCasilla> obtenerPropPropias() {
		return propPropias;
	}

	public void ponerjfCuenta(int jfCuenta) {
		this.jfCuenta = jfCuenta;
	}

	public void ponerTiempoCarcel(int tiempoCarcel) {
		this.tiempoCarcel = tiempoCarcel;
	}
	
	public void comprar(PropiedadCasilla landed) {
		this.mCuenta -= Integer.parseInt(landed.obtenerPrecio());
		landed.ponerPropiedad(this);
		this.propPropias.add(landed);
	}	
	
	public boolean tieneMonopoly(PropiedadCasilla aterrizado) {
		boolean tiene;
		String colorTemp = aterrizado.obtenerColor();
		char caracterColor = colorTemp.charAt(0);
		int contador = 0;

		switch (caracterColor) {
		case 'B':
			for (int i = 0; i < this.propPropias.size(); i++) {

				if (this.propPropias.get(i).obtenerColor().charAt(0) == caracterColor) {
					contador++;
				} else {
				}
			}
			contador -= 1;
			if (contador == 2) {
				tiene = true;
			} else {
				tiene = false;
			}
			break;
		case 'D':
			for (int i = 0; i < this.propPropias.size(); i++) {

				if (this.propPropias.get(i).obtenerColor().charAt(0) == caracterColor) {
					contador++;
				} else {
				}
			}
			contador -= 1;
			if (contador == 2) {
				tiene = true;
			} else {
				tiene = false;
			}
			break;
		case 'R':
			for (int i = 0; i < this.propPropias.size(); i++) {

				if (this.propPropias.get(i).obtenerColor().charAt(0) == caracterColor) {
					contador++;
				} else {
				}
			}
			contador -= 1;
			if (contador == 4) {
				tiene = true;
			} else {
				tiene = false;
			}
			break;
		case 'U':
			for (int i = 0; i < this.propPropias.size(); i++) {
				if (this.propPropias.get(i).obtenerColor().charAt(0) == caracterColor) {
					contador++;
				} else {
				}
			}
			contador -= 1;
			if (contador == 2) {
				tiene = true;
			} else {
				tiene = false;
			}
			break;
		default:
			for (int i = 0; i < this.propPropias.size(); i++) {
				if (this.propPropias.get(i).obtenerColor().charAt(0) == caracterColor) {
					contador++;
				} else {
				}
			}
			contador -= 1;
			if (contador == 3) {
				tiene = true;
			} else {
				tiene = false;
			}
			break;

		}

		return tiene;
	}
	
	public void hipotecar(PropiedadCasilla propiedad) {
		propiedad.ponerHipotecada("true");
		this.mCuenta += (Integer.parseInt(propiedad.obtenerPrecio())) / 2;
	}

	public void deshipotecar(PropiedadCasilla propiedad) {
		this.mCuenta -= (Integer.parseInt(propiedad.obtenerPrecio())) / 2;
		propiedad.ponerHipotecada("false");
	}
	
	
	public int pagarAlquiler(Jugador propietario, PropiedadCasilla aterrizado) {
		int alquiler = Integer.parseInt(aterrizado.obtenerAlquiler());
		int nAlquiler = 0;

		if (aterrizado.obtenerTipo() == 'p') {
			int hAlquiler = Integer.parseInt(aterrizado.obtenerAquilerUnaCasa());
			if (aterrizado.estaHipotecada() == "false") {
				switch (aterrizado.obtenerCasas()) {
				case 0:
					if (this.tieneMonopoly(aterrizado) == true) {
						nAlquiler = 2 * alquiler;
					} else {
						nAlquiler = alquiler;
					}
					break;
				case 1:
					nAlquiler = hAlquiler;
					break;
				case 2:
					nAlquiler = hAlquiler * 3;
					break;
				case 3:
					nAlquiler = hAlquiler * 9;
					break;
				case 4:
					nAlquiler = hAlquiler * 18;
					break;
				case 5:
					nAlquiler = hAlquiler * 36;
					break;
				}
			}
		} else {
			nAlquiler = alquiler;
		}

		if (aterrizado.obtenerPropiedad() != this) {
			this.mCuenta -= nAlquiler;
			propietario.ponerMCuenta(propietario.obtenerMCuenta() + nAlquiler);
		}

		return nAlquiler;
	}

	public int obtenerTiempoCarcel() {
		return tiempoCarcel;
	}

	public Casilla obtenerCNode() {
		return nodoActual;
	}
	
	public void ponerNodoActual(Casilla nCNode) {
		this.nodoActual = nCNode;
	}
}
