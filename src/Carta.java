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
		
		String title;
		
		
		return this.accion;
	}
}

