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


private ArrayList<Jugador> listaJugadores;	
private Jugador cJugador;
private ListaTablero tablero;
private boolean gana = false;
private int cpIndex;
private Baraja baraja;
private int nJugadores;

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
		
		
		cpIndex = 0;  
		cJugador = new Jugador(); 
		cJugador = listaJugadores.get(cpIndex);
		
		
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
	
	public void turnoFase1(){

		
	}
	
	public void turnoFase2(){ 

	}
	
	public void refrescarTodo(){

	}
	
}
