/*
 Monopoly para cliente Inv2ASâ€�
 Proyectos InformÃ¡ticos II - 2019
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
		

		Scanner inicio = new Scanner(System.in); 
		System.out.println("Qué desea hacer: 1. Cargar partida o 2. Crear nueva partida");
		int nOpcionInicio = inicio.nextInt();
		
		if(nOpcionInicio == 1) {
			cargarPartida();
		} else {	


		
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
			listaJugadores.add(i, new Jugador("Jugador "+(i+1), tablero.obtenerPrimera()));
		} 
		
		cpIndex = 0;  
		cJugador = new Jugador(); 
		cJugador = listaJugadores.get(cpIndex);
		
		//Este bloque mantiene el juego en ejecucion hasta que uno de los jugadores gana
		while(gana == false){ 
			
			turnoFase1(); 
			try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();} 
			turnoFase2(); 
			
			
			cpIndex = (cpIndex + 1) % listaJugadores.size();	
			cJugador = listaJugadores.get(cpIndex);
		}
		}
	}
	
	public void cargarPartida( ) {
		
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
			System.out.println("Por favor, introduce entre 2 y 8 jugadores");
			System.out.println(cJugador.obtenerNombre() + ", Estas encerrado! Sigues en la carcel!");
			if(cJugador.obtenerjfCuenta()>0){
				System.out.println("Salir de la carcel!");
				cJugador.ponerjfCuenta(cJugador.obtenerjfCuenta()-1);
			}else{return;}
		}
		
		Scanner sc = new Scanner(System.in); 
		System.out.println("  ");
		System.out.println(cJugador.obtenerNombre() + " | " + cJugador.obtenerMCuenta() + " Euros");
		System.out.println("Escoge una opción: 1. Abandonar partida; 2. Ver propiedades; 3. Comprar casas; 4. Deshipotecar; 5. Hipotecar; 6. Negociar; 7. Tirar dado");
		nOpcion = sc.nextInt();
		
		if(nOpcion == 7){ //Tirar dado
			cJugador.tirar(); 
			System.out.println("Has sacado: " + cJugador.obtenerRCuenta());
			cJugador.mover();
			
		}else if(nOpcion == 6){ //Negociar
			if(cJugador.obtenerPropPropias().size() < 1){
						System.out.println("Lo siento, no tienes propiedades...");
						turnoFase1(); //En el caso de no tener propiedades vuelvos al inicio
			} 
			else{
			String[] names = new String[listaJugadores.size()];
			for(int i = 0; i < names.length; i++){ 
				names[i] = listaJugadores.get(i).obtenerNombre(); //Realiza una matriz de nombres 
			}
			System.out.println("Con quien quieres negociar?");
		    Jugador trader2 = listaJugadores.get(sc.nextInt());

			if(trader2 == cJugador){turnoFase1();} //si te seleccionas a ti mismo vuelves al inicio
			
			if(trader2.obtenerPropPropias().size() < 1){ 
				System.out.println("Este jugador no tiene propiedades");
				turnoFase1(); //Notifica si el jugador seleccionado no tiene propiedades.
			}
			else{
			String[] t1props = new String[cJugador.obtenerPropPropias().size()];
			for(int i = 0; i < t1props.length; i++){ 
				t1props[i] = cJugador.obtenerPropPropias().get(i).obtenerNombre();
			}			///Que propiedad queremos ofrecer
			
			
			PropiedadCasilla give = cJugador.obtenerPropPropias().get(JOptionPane.showOptionDialog (null, "Que propiedad quiere ofrecer?", "Negociar", 0, JOptionPane.QUESTION_MESSAGE, null, t1props, null));
			
			String[] t2props = new String[trader2.obtenerPropPropias().size()];
			for(int i = 0; i < t2props.length; i++){
				t2props[i] = trader2.obtenerPropPropias().get(i).obtenerNombre();
				System.out.println(t2props[i]);
			}		//Pregunta que propiedad del jugador seleccionado quieres
			System.out.println("Que propiedad quieres comprar?");
			PropiedadCasilla take = trader2.obtenerPropPropias().get(sc.nextInt());
		
			System.out.println("Cuanto dinero te gustaria ofrecer?");
			int cash = sc.nextInt();
			
			String[] yesnoOp = {"Si", "No"};  
			System.out.println("Aceptas esta oferta Si = 1 No = 0");
			int yesno = sc.nextInt();
			if(yesno == 1){
				System.out.println("La oferta ha sido rechazada\", \"Rechazada!");}
			else{System.out.println("La oferta ha sido aceptada\", \"Aceptada!");
			
			cJugador.negociar(trader2, cash);
			
			refrescarTodo(); 
			}
			
			turnoFase1(); 
			}
			
	}	
		}else if(nOpcion == 5){//Hipotecar
			if(
					
			cJugador.obtenerPropPropias().size() < 1){
			  System.out.println("Este jugador no tiene propiedades");
			  turnoFase1();
			}
			
			else{
				  System.out.println("Qué propiedad te gustaría hipotecar?");
					String[] props = new String[cJugador.obtenerPropPropias().size()];
					for(int i = 0; i < props.length; i++){  
						props[i] = cJugador.obtenerPropPropias().get(i).obtenerNombre();
						System.out.println(i + " - " +cJugador.obtenerPropPropias().get(i).obtenerNombre());		
					}
					
					int nEleccion = sc.nextInt();
					PropiedadCasilla eleccion = null;
					for(int i = 0; i < props.length; i++){  
						if (nEleccion == i) {
							eleccion = cJugador.obtenerPropPropias().get(i);
						}
					}
					
					boolean hipotecada = eleccion.estaHipotecada();
					if(hipotecada == true){
						System.out.println("Ya está hipotecada!");
					}else{
						cJugador.hipotecar(eleccion);
						System.out.println("Has hipotecado la propiedad, tu saldo actual es de " + cJugador.obtenerMCuenta() + " Euros");
					}

			refrescarTodo();
			turnoFase1();
			}
		}
		
		else if(nOpcion == 4){//Deshipotecar
			if(
					
			cJugador.obtenerPropPropias().size() < 1){
			  System.out.println("Este jugador no tiene propiedades");
			  turnoFase1();
			}
			
			else{
				  System.out.println("Qué propiedad te gustaría deshipotecar?");
					String[] props = new String[cJugador.obtenerPropPropias().size()];
					for(int i = 0; i < props.length; i++){  
						props[i] = cJugador.obtenerPropPropias().get(i).obtenerNombre();
						System.out.println(i + " - " +cJugador.obtenerPropPropias().get(i).obtenerNombre());		
					}
					
					int nEleccion = sc.nextInt();
					PropiedadCasilla eleccion = null;
					for(int i = 0; i < props.length; i++){  
						if (nEleccion == i) {
							eleccion = cJugador.obtenerPropPropias().get(i);
						}
					}
					
					boolean hipotecada = eleccion.estaHipotecada();
					if(hipotecada == false){
						System.out.println("Ya está deshipotecada!");
					}else{
						cJugador.deshipotecar(eleccion);
						System.out.println("Has deshipotecado la propiedad, tu saldo actual es de " + cJugador.obtenerMCuenta() + " Euros");
					}

			refrescarTodo();
			turnoFase1();
			}
		}
		
		else if(nOpcion == 3){//Comprar casas
			
			if(cJugador.obtenerPropPropias().size() < 1){
				System.out.println("No tienes propiedades! Es necesario tener al menos una propiedad para comprar casas");					
			}
			else{
				
		  System.out.println("Para qué propiedad te gustaría comprar una casa?");
			String[] props = new String[cJugador.obtenerPropPropias().size()];
			for(int i = 0; i < props.length; i++){  
				props[i] = cJugador.obtenerPropPropias().get(i).obtenerNombre();
				System.out.println(cJugador.obtenerPropPropias().get(i).obtenerNombre());		
			}
			
			int nEleccion = sc.nextInt();
			PropiedadCasilla eleccion = null;
			for(int i = 0; i < props.length; i++){  
				if (nEleccion == i) {
					eleccion = cJugador.obtenerPropPropias().get(i);
				}
			}
			
			
			boolean monopoly = cJugador.tieneMonopoly(eleccion);
			if(monopoly == true){
				cJugador.comprarCasa(eleccion);
			}else{
				System.out.println("Todavía no tienes Monopoly!");
			}
			
			refrescarTodo();
			
			turnoFase1();
			}
		}
		else if(nOpcion == 2){//Ver propiedades
			String[] nombres = new String[listaJugadores.size()];
			for(int i = 0; i < nombres.length; i++){ 
				nombres[i] = listaJugadores.get(i).obtenerNombre(); 
			}
			
			System.out.println("Las propiedades de qué jugador te gustaría ver?");
			int nJugador = sc.nextInt();
			nJugador = nJugador - 1;
			
			Jugador negociador2 = listaJugadores.get(nJugador);
			
			if(negociador2.obtenerPropPropias().size() < 1){ 
				System.out.println("Este jugador no tiene propiedades!");
				turnoFase1(); 
			}
			else{
			String[] props = new String[negociador2.obtenerPropPropias().size()];
			for(int i = 0; i < props.length; i++){ 
				
				
				String details = "Nombre: " + negociador2.obtenerPropPropias().get(i).obtenerNombre() + "\n" + "Tipo: " + negociador2.obtenerPropPropias().get(i).obtenerColor() + "\n" + "Alquiler: " + negociador2.obtenerPropPropias().get(i).obtenerAlquiler() + "\n" + "Precio: " + negociador2.obtenerPropPropias().get(i).obtenerPrecio();
				System.out.println(details);
				System.out.println(" ");
			}

			turnoFase1();
			}
		}else if (nOpcion == 1){//Abandonar
			System.out.println("Has abandonado la partida!");
			System.exit(0); 
		}
		
		if(cJugador.obtenerDado1() == cJugador.obtenerDado2()){     
			cJugador.ponerDCuenta(cJugador.obtenerDCuenta() + 1);  
			if(cJugador.obtenerDCuenta() == 3){ 
				System.out.println("Has sacado tres dobles seguidos - a la carcel.");
		        cJugador.moverAIdentidad(11);
		        cJugador.ponerDCuenta(0);
		        cJugador.ponerTiempoCarcel(3);
		        return;
		    }
			turnoFase2(); 
			System.out.println("Tira de nuevo! - Has sacado dobles!");
	        turnoFase1();
	      
	    }else{cJugador.ponerDCuenta(0);}    
		
	}
	
	public void turnoFase2(){ 
		Scanner sc = new Scanner(System.in); 
		if(cJugador.obtenerTiempoCarcel()>0){
			return;
		}
		
		int reference = cJugador.obtenerCNode().obtenerPosicion();
		Casilla landed = tablero.obtenerPrimera();
		while(landed.obtenerPosicion() != reference){ 	
			landed = landed.obtenerSiguiente();
		}
		switch(landed.obtenerTipo()){
		case 'c':  //Calle  
			if(((PropiedadCasilla) landed).obtenerPropiedad() == null){
				String details = "Nombre: " + landed.obtenerNombre() + "\n" + "Tipo: " + ((PropiedadCasilla) landed).obtenerColor() + "\n" + "Alquiler: " + ((PropiedadCasilla) landed).obtenerAlquiler() + "\n" + "Precio: " + ((PropiedadCasilla) landed).obtenerPrecio();
				System.out.println(details);
				System.out.println("Aceptas esta oferta Si = 1 No = 0");
				int yesno = sc.nextInt();
				if(yesno == 1){
						cJugador.comprar((PropiedadCasilla) landed);
						System.out.println("Ahora la tienes " + landed.obtenerNombre() + "," + " tu saldo actual es de "+ cJugador.obtenerMCuenta() + " Euros");}
					else{}
					
			}else if(((PropiedadCasilla) landed).obtenerPropiedad() == cJugador){
				
			}else{Jugador pReference = ((PropiedadCasilla) landed).obtenerPropiedad();
				int payed = cJugador.pagarAlquiler(pReference, (PropiedadCasilla) landed);
				System.out.println("Has pagado " + pReference.obtenerNombre() + " un alquiler " + payed + " euros.");
			}
			
			refrescarTodo();
		
			break;
			
		case 'a':  //Comunidad
			int accion = baraja.obtenerComunidadCarta().jugarCarta(cJugador, listaJugadores);
			if(accion == 3){
				turnoFase2();
			}else{}
			refrescarTodo();
			break;
		case 'h': //Suerte
			int accion1 = baraja.obtenerSuerteCarta().jugarCarta(cJugador, listaJugadores);
			if(accion1 == 3){
				turnoFase2();
			}else{}
			refrescarTodo();
			break;
		case 'u':  //Empresa de servicios
			if(((PropiedadCasilla) landed).obtenerPropiedad() == null){
				String details = "Nombre: " + landed.obtenerNombre() + "\n" + "Tipo: " + ((PropiedadCasilla) landed).obtenerColor() + "\n" + "Alquiler: " + ((PropiedadCasilla) landed).obtenerAlquiler() + "\n" + "Precio: " + ((PropiedadCasilla) landed).obtenerPrecio();
				System.out.println("La quieres comprar? Si = 1 No = 0");
				System.out.println(details);
				int yesno = sc.nextInt();				
					if(yesno == 0){
						cJugador.comprar((PropiedadCasilla) landed);
						System.out.println("Ahora la tienes " + landed.obtenerNombre());}
					else{}
					
			}else if(((PropiedadCasilla) landed).obtenerPropiedad() == cJugador){
				
			}else{Jugador pReference = ((PropiedadCasilla) landed).obtenerPropiedad();
				int payed = cJugador.pagarAlquiler(pReference, (PropiedadCasilla) landed);
				System.out.println("Has pagado " + pReference.obtenerNombre() + " un alquiler de " + payed + " euros.");
			}
			refrescarTodo();
			break;
		case 'f':  //Ferrocarril
			if(((PropiedadCasilla) landed).obtenerPropiedad() == null){
				String details = "Nombre: " + landed.obtenerNombre() + "\n" + "Tipo: " + ((PropiedadCasilla) landed).obtenerColor() + "\n" + "Alquiler: " + ((PropiedadCasilla) landed).obtenerAlquiler() + "\n" + "Precio: " + ((PropiedadCasilla) landed).obtenerPrecio();
				System.out.println("La quieres comprar? Si = 1 No = 0");
				System.out.println(details);					
				int yesno = sc.nextInt();	
				if(yesno == 0){
						cJugador.comprar((PropiedadCasilla) landed);
						System.out.println("Ahora la tienes " + landed.obtenerNombre());}
					else{}
					
			}else if(((PropiedadCasilla) landed).obtenerPropiedad() == cJugador){
				
			}else{Jugador pReference = ((PropiedadCasilla) landed).obtenerPropiedad();
				int payed = cJugador.pagarAlquiler(pReference, (PropiedadCasilla) landed);
				System.out.println("Has pagado " + pReference.obtenerNombre() + " un alquiler de " + payed + " euros.");
			}
			
			refrescarTodo();
			break;
		case 't': //Impuesto al alquiler
			System.out.println("Has caido en el impuesto al alquiler: paga 200 al banco");
			cJugador.ponerMCuenta(cJugador.obtenerMCuenta() - 200);
			refrescarTodo();
			
			break;
		case 'l': //Tasa al lujo
			System.out.println( "Has caido en la tasa de lujo - paga 75 al banco");
			cJugador.ponerMCuenta(cJugador.obtenerMCuenta() - 75);
		
			refrescarTodo();
			
			break;
		case 'j': //Cárcel (visita)
			System.out.println("Estas visitando la carcel.");
			break;
		case 's': //Salida
			System.out.println("Casilla de salida - Ganas 200");
			cJugador.ponerMCuenta(cJugador.obtenerMCuenta() + 200);
			
			refrescarTodo();
			
			break;
		case 'k': //Parking gratuito
			System.out.println("Esta en el estacionamiento gratuito - descanse.");
			refrescarTodo();
			break;
		case 'w':  //A la cárcel
			System.out.println("Vas a la carcel\n No puedes salir!");
			cJugador.moverAIdentidad(11);
			cJugador.ponerTiempoCarcel(3);
			refrescarTodo();
			break;}
	}
	
	public void refrescarTodo(){
		//Poner variables a 0	
		nJugadores = 0;
		nOpcion = 0;
	
	}
	
}
