

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ManejadorCliente implements Runnable {

	public static ArrayList<ManejadorCliente> clientes = new ArrayList<ManejadorCliente>();
	private Socket socket;
	private BufferedReader buffederReader;
	private BufferedWriter buffederWriter;
	private String nombreUsuario;
	private String ip;

	public ManejadorCliente(Socket socket) {
		try {
			this.socket = socket;
			this.buffederReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.buffederWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.nombreUsuario = buffederReader.readLine();
			this.ip = socket.getInetAddress().toString();
			this.clientes.add(this);
			boadcastMensaje("Server: " + nombreUsuario + " ha entrado en el chat ");
		} catch (IOException e) {
			cierraTodo(socket, buffederReader, buffederWriter);
		}
	}

	private void boadcastMensaje(String mensaje) {
		for (ManejadorCliente manejadorCliente : clientes) {
			try {
				
				manejadorCliente.buffederWriter.write(mensaje);
				manejadorCliente.buffederWriter.newLine();
				manejadorCliente.buffederWriter.flush();
			} catch (IOException e) {
				cierraTodo(socket, buffederReader, buffederWriter);
			}
		}

	}

	private void cierraTodo(Socket socket, BufferedReader buffederReader, BufferedWriter buffederWriter) {
		borraCliente();
		try {
			if (buffederReader != null) {
				buffederReader.close();
			}
			if (buffederWriter != null) {
				buffederWriter.close();
			}
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	private void borraCliente() {
		clientes.remove(this);
		boadcastMensaje("Server: " + nombreUsuario + " ha abandonado el chat ");
		
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public String getIp() {
		return ip;
	}

public void run() {
	String mensajeDesdeCliente;
	while(socket.isConnected()) {
		try {
			mensajeDesdeCliente = buffederReader.readLine();
			boadcastMensaje(mensajeDesdeCliente);
		} catch (IOException e) {
			cierraTodo(socket, buffederReader, buffederWriter);
			break;
		}
	}

}

}
