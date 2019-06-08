/*
 Monopoly para cliente Inv2AS”
 Proyectos Informáticos II - 2019
 Grupo C (Juan Jose Garcia, Manuel Angel Mateos, Jaime Ojeda)
 */

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Carta
{
	private String mensaje;
	private int accion; 
	private int valor;
	private int pos;
	private char tipo;
	
	public Carta(char tipo, String mensaje, int accion, int valor, int pos)
	{
		this.mensaje = mensaje;
		this.accion = accion;
		this.valor = valor;		
		this.pos = pos;
		this.tipo = tipo;
	}
	
	public int jugarCarta(Jugador jugador, ArrayList<Jugador> jugadores)
	{
		
		String titulo;
		
		if(this.tipo == 'c'){
			titulo = "Caja de la comunidad!";		
		}
		else{titulo = "Suerte!";}
			
		System.out.println("***CARTA***");
		System.out.println(titulo);
		System.out.println(mensaje);
		
		//promulga la tarjeta, las cuales tienen tipos de acción de:
		// 1 (ganancia / pérdida de dinero)
		// 2 (ganancia / pérdida de dinero que involucran a todos los jugadores)
		// 3 (movimiento a una posición)
		// 4 (una tarjeta para salir de la cárcel)
		switch (accion)
		{
	        case 1:
	        	jugador.dineroNeto(valor);
	        	break;						
	        case 2:
	        	jugador.dineroNeto(jugadores.size()*valor);
	        	for(Jugador j: jugadores)
	        		j.dineroNeto(-valor);
	        	break;
	        case 3:
	        	jugador.moverAIdentidad(pos);
	        	break;
	        case 4:
	        	jugador.ponerjfCuenta(jugador.obtenerjfCuenta()+1);
	        default:
	       		break;		
		}
		
		return this.accion;
	}
}

