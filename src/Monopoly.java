/*
 Monopoly para cliente Inv2AS”
 Proyectos Informáticos II - 2019
 Grupo C (Juan Jose Garcia, Manuel Angel Mateos, Jaime Ojeda)
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.*;
import javax.swing.*;
import java.util.Scanner;

public class Monopoly {

//Declaracion de dada
private ArrayList<Jugador> listaJugadores;	
private Jugador cJugador;
private ListaTablero tablero;
private boolean gana = false;
private int cpIndex;
private Baraja baraja;
private int nJugadores;
private int nOpcion;

	public Monopoly(){}
	
	public static void main(String[] args){
		Monopoly juego = new Monopoly();
		juego.nuevoJuego();
	}

	public void nuevoJuego(){	
		

		Scanner sc = new Scanner(System.in); 
		System.out.println("Por favor, introduce entre 2 y 8 jugadores");
		nJugadores = sc.nextInt();
		
		if(nJugadores < 2 || nJugadores > 8 ) {
			nuevoJuego();
		}
		
		listaJugadores = new ArrayList<Jugador>(); 
		tablero = new ListaTablero();
		baraja = new Baraja(); 
		
		for(int i = 0; i<nJugadores; i++){ //Se anaden los jugadores a la lista de jugadores de la partida
			listaJugadores.add(i, new Jugador("Jugador"+(i+1)));
		} 
		
		cpIndex = 0;  
		cJugador = new Jugador(); 
		cJugador = listaJugadores.get(cpIndex);
		
		//Este bloque mantiene el juego en ejecución hasta que uno de los jugadores gana
		while(gana == false){ 
			
			turnoFase1(); 
			try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();} 
			turnoFase2(); 
			
			
			cpIndex = (cpIndex + 1) % listaJugadores.size();	
			cJugador = listaJugadores.get(cpIndex);
		}
		
	}
	
	public void ultimoEstado(){
		
		
	}
	public void crearInterfaz(ArrayList<Jugador> listaJugadores){
		
	}
	
	// La Fase 1 del turno presenta las opciones que tiene el jugador:
	// - Abandonar partida
	// - Ver propiedades
	// - Comprar casas
	// - Deshipotecar
	// - Negociar
	// - Tirar dado
	public void turnoFase1(){
		try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
	
		if(cJugador.obtenerTiempoCarcel()>0){
			cJugador.ponerTiempoCarcel(cJugador.obtenerTiempoCarcel()-1);
			JOptionPane.showMessageDialog(null, cJugador.obtenerNombre() + ", Estas encerrado!", "Sigues en la carcel!", JOptionPane.INFORMATION_MESSAGE);
			if(cJugador.obtenerjfCuenta()>0){
				JOptionPane.showMessageDialog(null, "Salir de la carcel!", "Usa tu carta!", JOptionPane.INFORMATION_MESSAGE);
				cJugador.ponerjfCuenta(cJugador.obtenerjfCuenta()-1);
			}else{return;}
		}
		
		Scanner sc = new Scanner(System.in); 
		System.out.println("Escoge una opción: 1. Abandonar partida; 2. Ver propiedades; 3. Comprar casas; 4. Deshipotecar; 5. Hipotecar; 6. Negociar; 7. Tirar dado");
		nOpcion = sc.nextInt();
		
		if(nOpcion == 7){ //Tirar dado
			cJugador.tirar(); 
			 System.out.println("Has sacado: " + cJugador.obtenerRCuenta());
			cJugador.mover();
		}else if(nOpcion == 6){ //Negociar


		}else if(nOpcion == 5){//Hipotecar

		}
		else if(nOpcion == 4){//Deshipotecar

		}
		else if(nOpcion == 3){//Comprar casas

		}
		else if(nOpcion == 2){//Ver propiedades

	
		}else if (nOpcion == 1){//Abandonar
			System.out.println("Has abandonado la partida!");
			System.exit(0); 
		}
		
		if(cJugador.obtenerDado1() == cJugador.obtenerDado2()){     
			cJugador.ponerDCuenta(cJugador.obtenerDCuenta() + 1);  
			if(cJugador.obtenerDCuenta() == 3){ 
		        JOptionPane.showMessageDialog(null, "Vas a la carcel!", "Has sacado tres dobles seguidos - a la carcel.", JOptionPane.INFORMATION_MESSAGE);
		        cJugador.moverAIdentidad(11);
		        cJugador.ponerDCuenta(0);
		        cJugador.ponerTiempoCarcel(3);
		        return;
		    }
			turnoFase2(); //starts part two of ones turn
	        JOptionPane.showMessageDialog(null, "Tira de nuevo!", "Has sacado dobles!", JOptionPane.INFORMATION_MESSAGE);
	        turnoFase1();
	      
	    }else{cJugador.ponerDCuenta(0);}    
		
	}
	
	public void turnoFase2(){ 
		
		if(cJugador.obtenerTiempoCarcel()>0){
			return;
		}
		
		int reference = cJugador.obtenerCNode().obtenerPosicion();
		Casilla landed = tablero.obtenerPrimera();
		while(landed.obtenerPosicion() != reference){ 	
			landed = landed.obtenerSiguiente();
		}
		switch(landed.obtenerTipo()){
		case 'p':   
			if(((PropiedadCasilla) landed).obtenerPropiedad() == null){
				String[] yesnoOp = {"SI", "No"};
				String details = "Nombre: " + landed.obtenerNombre() + "\n" + "Tipo: " + ((PropiedadCasilla) landed).obtenerColor() + "\n" + "Alquiler: " + ((PropiedadCasilla) landed).obtenerAlquiler() + "\n" + "Precio: " + ((PropiedadCasilla) landed).obtenerPrecio();
				int yesno = JOptionPane.showOptionDialog (null, details, "La quieres comprar?", 0, JOptionPane.QUESTION_MESSAGE, null, yesnoOp, null);
					if(yesno == 0){
						cJugador.comprar((PropiedadCasilla) landed);
						JOptionPane.showMessageDialog(null, "Ahora la tienes " + landed.obtenerNombre(), "Propiedad comprar!", JOptionPane.INFORMATION_MESSAGE);}
					else{}
					
			}else if(((PropiedadCasilla) landed).obtenerPropiedad() == cJugador){
				
			}else{Jugador pReference = ((PropiedadCasilla) landed).obtenerPropiedad();
				int payed = cJugador.pagarAlquiler(pReference, (PropiedadCasilla) landed);
				JOptionPane.showMessageDialog(null, "Has pagado " + pReference.obtenerNombre() + " un alquiler " + payed + " euros." , "Alquiler pagado!", JOptionPane.INFORMATION_MESSAGE);
			}
			
			refrescarTodo();
		
			break;
			
		case 'c':  
			int accion = baraja.obtenerComunidadCarta().jugarCarta(cJugador, listaJugadores);
			if(accion == 3){
				turnoFase2();
			}else{}
			refrescarTodo();
			break;
		case 'h': 
			int accion1 = baraja.obtenerSuerteCarta().jugarCarta(cJugador, listaJugadores);
			if(accion1 == 3){
				turnoFase2();
			}else{}
			refrescarTodo();
			break;
		case 'u':  
			if(((PropiedadCasilla) landed).obtenerPropiedad() == null){
				String[] yesnoOp = {"Si", "No"};
				String details = "Nombre: " + landed.obtenerNombre() + "\n" + "Tipo: " + ((PropiedadCasilla) landed).obtenerColor() + "\n" + "Alquiler: " + ((PropiedadCasilla) landed).obtenerAlquiler() + "\n" + "Precio: " + ((PropiedadCasilla) landed).obtenerPrecio();
				int yesno = JOptionPane.showOptionDialog (null, details, "La quieres comprar?", 0, JOptionPane.QUESTION_MESSAGE, null, yesnoOp, null);
					if(yesno == 0){
						cJugador.comprar((PropiedadCasilla) landed);
						JOptionPane.showMessageDialog(null, "Ahora la tienes " + landed.obtenerNombre(), "Propiedad comprada!", JOptionPane.INFORMATION_MESSAGE);}
					else{}
					
			}else if(((PropiedadCasilla) landed).obtenerPropiedad() == cJugador){
				
			}else{Jugador pReference = ((PropiedadCasilla) landed).obtenerPropiedad();
				int payed = cJugador.pagarAlquiler(pReference, (PropiedadCasilla) landed);
				JOptionPane.showMessageDialog(null, "Has pagado " + pReference.obtenerNombre() + " un alquiler de " + payed + " euros." , "Alquiler pagado!", JOptionPane.INFORMATION_MESSAGE);
			}
			refrescarTodo();
			break;
		case 'r':  
			if(((PropiedadCasilla) landed).obtenerPropiedad() == null){
				String[] yesnoOp = {"Si", "No"};
				String details = "Nombre: " + landed.obtenerNombre() + "\n" + "Tipo: " + ((PropiedadCasilla) landed).obtenerColor() + "\n" + "Alquiler: " + ((PropiedadCasilla) landed).obtenerAlquiler() + "\n" + "Precio: " + ((PropiedadCasilla) landed).obtenerPrecio();
				int yesno = JOptionPane.showOptionDialog (null, details, "Quieres comprarla?", 0, JOptionPane.QUESTION_MESSAGE, null, yesnoOp, null);
					if(yesno == 0){
						cJugador.comprar((PropiedadCasilla) landed);
						JOptionPane.showMessageDialog(null, "Ahora la tienes " + landed.obtenerNombre(), "Propiedad comprada!", JOptionPane.INFORMATION_MESSAGE);}
					else{}
					
			}else if(((PropiedadCasilla) landed).obtenerPropiedad() == cJugador){
				
			}else{Jugador pReference = ((PropiedadCasilla) landed).obtenerPropiedad();
				int payed = cJugador.pagarAlquiler(pReference, (PropiedadCasilla) landed);
				JOptionPane.showMessageDialog(null, "Has pagado " + pReference.obtenerNombre() + " un alquiler de " + payed + " euros." , "Alquiler pagado!", JOptionPane.INFORMATION_MESSAGE);
			}
			
			refrescarTodo();
			break;
		case 't': 
			JOptionPane.showMessageDialog(null, "Has caido en el impuesto al alquiler: paga 200€ al banco", "Impuesto alquiler", JOptionPane.INFORMATION_MESSAGE);
			cJugador.ponerMCuenta(cJugador.obtenerMCuenta() - 200);
			refrescarTodo();
			
			break;
		case 'l': 
			JOptionPane.showMessageDialog(null, "Has caido en el impuesto de lujo - paga 75€ al banco", "Impuesto de lujo!", JOptionPane.INFORMATION_MESSAGE);
			cJugador.ponerMCuenta(cJugador.obtenerMCuenta() - 75);
		
			refrescarTodo();
			
			break;
		case 'j': 
			JOptionPane.showMessageDialog(null, "Estas visitando la carcel.", "Carcel", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 'g': 
			JOptionPane.showMessageDialog(null, "Casilla de salida - Ganas 200€", "Casilla de salida", JOptionPane.INFORMATION_MESSAGE);
			cJugador.ponerMCuenta(cJugador.obtenerMCuenta() + 200);
			
			refrescarTodo();
			
			break;
		case 'f':
			JOptionPane.showMessageDialog(null, "Está en el estacionamiento gratuito - descanse.", "Estacionamiento gratuito", JOptionPane.INFORMATION_MESSAGE);
			refrescarTodo();
			break;
		case 'w':  
			JOptionPane.showMessageDialog(null, "Vas a la carcel\nDO No puedes salir!", "A la carcel", JOptionPane.INFORMATION_MESSAGE);
			cJugador.moverAIdentidad(11);
			cJugador.ponerTiempoCarcel(3);
			refrescarTodo();
			break;
	}
	
	public void refrescarTodo(){
		oeste.refreshList(listaJugadores.get(0));
		este.refreshList(listaJugadores.get(1));
		oeste.repaint();
		este.repaint();
		
		if(listaJugadores.size() > 2){					
		norte.refreshList(listaJugadores.get(2));
		norte.repaint();
		}
		if(listaJugadores.size() > 3){
		sur.refreshList(listaJugadores.get(3));
		sur.repaint();
		}
		
		centro.repaint();

	}
	
}
