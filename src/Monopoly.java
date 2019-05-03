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
			JOptionPane.showMessageDialog(null, cJugador.obtenerNombre() + ", you're locked In!", "You're still in jail!", JOptionPane.INFORMATION_MESSAGE);
			if(cJugador.obtenerjfCuenta()>0){
				JOptionPane.showMessageDialog(null, "Get out of jail free!", "Use you're carta!", JOptionPane.INFORMATION_MESSAGE);
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
		        JOptionPane.showMessageDialog(null, "Go To Jail!", "You have rolled three doubles in a row - go to jail.", JOptionPane.INFORMATION_MESSAGE);
		        cJugador.moverAIdentidad(11);
		        cJugador.ponerDCuenta(0);
		        cJugador.ponerTiempoCarcel(3);
		        return;
		    }
			turnoFase2(); //starts part two of ones turn
	        JOptionPane.showMessageDialog(null, "Roll again!", "You rolled doubles!", JOptionPane.INFORMATION_MESSAGE);
	        turnoFase1();
	      
	    }else{cJugador.ponerDCuenta(0);}    
		
	}
	
	public void turnoFase2(){ 

	}
	
	public void refrescarTodo(){

	}
	
}
