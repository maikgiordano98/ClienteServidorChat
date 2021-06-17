package ClienteChat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;

/**
 * Esta clase gestiona el envio de datos entre el cliente y el servidor.
 * 
 */

public class ConexionServidor implements ActionListener {
    
    private Socket socket; 
    private JTextField tfMensaje;
    private String usuario;
    private DataOutputStream salidaDatos;
    
    public ConexionServidor(Socket socket, JTextField tfMensaje, String usuario) {
        this.socket = socket;
        this.tfMensaje = tfMensaje;
        this.usuario = usuario;
        try {
            this.salidaDatos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
        	System.out.println("Error al crear el stream de salida: " + e.getMessage());
        } catch (NullPointerException e) {
        	System.out.println("El socket no se creo correctamente.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            salidaDatos.writeUTF(usuario + " " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("d/MM/YYYY HH:mm"))+ ": " + tfMensaje.getText() );
            tfMensaje.setText("");
        } catch (IOException ex) {
        	System.out.println("Error al intentar enviar un mensaje: " + ex.getMessage());
        }
    }
}