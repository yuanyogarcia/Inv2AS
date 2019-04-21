/*
 Monopoly para cliente Inv2AS”
 Proyectos Informáticos II - 2019
 Grupo C (Juan Jose Garcia, Manuel Angel Mateos, Jaime Ojeda)
 */

import java.util.*;
import java.io.*;


public class Baraja {

	private LinkedList<Carta> suerte = new LinkedList<Carta>();  //lista enlazada para cartas de azar
	private LinkedList<Carta> comunidad = new LinkedList<Carta>(); //lista enlazada para cartas de cofre de la comunidad

	public Baraja(){
		try{
			File cartas = new File("Cartas.txt");	//lee el archivo de texto de cartas de oportunidad / comunidad
			Scanner lectorCarta = new Scanner(cartas);
			
			for(int i = 0; i <= 24; i++){ //Big O(1)
	  			switch(lectorCarta.nextLine().charAt(0)){
	  			case 'h':
	  				String mensaje = lectorCarta.nextLine();		//lee cada línea y almacena los valores necesarios, luego crea nodos con valores
	  				int accion = Integer.parseInt(lectorCarta.nextLine());
	  				int valor = Integer.parseInt(lectorCarta.nextLine());
	  				int pos = Integer.parseInt(lectorCarta.nextLine());
	  				Carta chcarta = new Carta('h', mensaje, accion, valor, pos);
	  				suerte.add(chcarta); //añade al final de la lista
	  				break;
	  			case 'c':
	  				String mensaje1 = lectorCarta.nextLine();					//el caso h recurrirá al azar, mientras que el caso c llamará a la comunidad
	  				int accion1 = Integer.parseInt(lectorCarta.nextLine());
	  				int valor1 = Integer.parseInt(lectorCarta.nextLine());	//se asegura de analizar a int
	  				int pos1 = Integer.parseInt(lectorCarta.nextLine());
	  				Carta commcarta = new Carta('c', mensaje1, accion1, valor1, pos1);
	  				comunidad.add(commcarta); //añade al final de la lista
	  				break;
	  			default:
	  				break;

	  			}
			}
		}catch(IOException e){}
		
		Collections.shuffle(suerte);		//utiliza la función aleatoria incorporada para mezclar cartas al comienzo del juego
		Collections.shuffle(comunidad);
	}
	
	public Carta obtenerSuerteCarta(){
		Carta newChance = suerte.removeFirst();	//Volver carta de oportunidad desde arriba, y se suma a la parte inferior
		suerte.addLast(newChance);
		return newChance;
	}
	
	public Carta obtenerComunidadCarta(){
		Carta newCommunity = comunidad.removeFirst();	//devuelve la carta de la comunidad desde arriba, y también la agrega a la parte inferior (para que las cartas no se agoten)
		suerte.addLast(newCommunity);
		return newCommunity;
	}
	
}
