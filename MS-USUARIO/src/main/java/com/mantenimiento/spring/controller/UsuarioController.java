package com.mantenimiento.spring.controller;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mantenimiento.spring.dao.UsuarioDao;
import com.mantenimiento.spring.models.Usuario;
import com.mantenimiento.spring.utils.JWTUtil;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001/")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/usuarios/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        return usuarioDao.getByIdUsuarios(id);
    }

    @RequestMapping(value = "/usuarios")
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) {

        /* String usuarioId = jwtUtil.getKey(token); */

        if (!validarToken(token)) {
            return null;
        }
        return usuarioDao.getUsuarios();
    }

    public boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario) {
        // * Encriptar contraseña
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        boolean correoExistente = usuarioDao.verificarExistenciaCorreo(usuario.getEmail());

        if (correoExistente) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario ya existe");

        } else {
            usuarioDao.crear(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente");
        }
    }

    @RequestMapping(value = "/usuario789")
    public Usuario editarUsuario() {
        Usuario usuario = new Usuario();

        return usuario;
    }

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminarUsuario(@RequestHeader(value = "Authorization") String token,
            @PathVariable Long id) {
        if (!validarToken(token)) {
            return;
        }
        usuarioDao.eliminar(id);
    }

}