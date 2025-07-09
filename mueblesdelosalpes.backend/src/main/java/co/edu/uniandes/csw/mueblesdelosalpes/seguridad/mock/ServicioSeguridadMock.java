/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lokci
 */
package co.edu.uniandes.csw.mueblesdelosalpes.seguridad.mock;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.TipoUsuario;
import co.edu.uniandes.csw.mueblesdelosalpes.dto.Usuario;
import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.AutenticacionException;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioSeguridadMockLocal;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioSeguridadMockRemote;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de los servicios mock de seguridad para autenticación de
 * usuarios.
 *
 * @author Santiago Chávez
 */
@Stateless
public class ServicioSeguridadMock implements IServicioSeguridadMockLocal, IServicioSeguridadMockRemote {

    private static List<Usuario> usuarios;

    /**
     * Constructor que inicializa la lista de usuarios si no ha sido creada.
     */
    public ServicioSeguridadMock() {
        if (usuarios == null) {
            usuarios = new ArrayList();

            usuarios.add(new Usuario("admin", "adminadmin", TipoUsuario.Administrador));
            usuarios.add(new Usuario("cliente", "cliente123", TipoUsuario.Cliente));
        }
    }

    /**
     * Registra el ingreso de un usuario al sistema validando login y
     * contraseña.
     */
    @Override
    public Usuario ingresar(String nombre, String contraseña) throws AutenticacionException {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(nombre) && usuario.getContraseña().equals(contraseña)) {
                return usuario;
            }
        }
        throw new AutenticacionException("Usuario o contraseña inválidos");
    }

    // Métodos auxiliares si decides extender funcionalidad (ej. en REST)
    /**
     * Retorna todos los usuarios registrados (solo para pruebas o
     * administración).
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Crea un nuevo usuario en el sistema.
     */
    public void create(Usuario usuario) {
        usuarios.add(usuario);
    }

    /**
     * Actualiza la información de un usuario existente.
     */
    public void update(Usuario usuarioActualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getLogin().equals(usuarioActualizado.getLogin())) {
                usuarios.set(i, usuarioActualizado);
                return;
            }
        }
    }

    /**
     * Elimina un usuario por su login.
     */
    public void delete(String login) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getLogin().equals(login)) {
                usuarios.remove(i);
                break;
            }
        }
    }
}
