package com.mantenimiento.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mantenimiento.spring.dao.UsuarioDao;
import com.mantenimiento.spring.models.AuthResponse;
import com.mantenimiento.spring.models.Usuario;
import com.mantenimiento.spring.utils.JWTUtil;

import io.jsonwebtoken.SignatureException;

@CrossOrigin(origins = "http://localhost:3001/")
@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {

        Usuario usuarioLogeado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if (usuarioLogeado != null) {

            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogeado.getId()), usuarioLogeado.getEmail());
            return tokenJwt;
        }
        return "Fail";
    }


    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ResponseEntity<Object> auth(HttpServletRequest request) {
        String token = request.getHeader("token");

        if (token == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay token en la petición");
        }

        String tokenFormato = "^[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]*$";
        if (!token.matches(tokenFormato)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El token no cumple con el formato especificado");
        }

        try {
            String usuarioId = jwtUtil.getKey(token);

            if (usuarioId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Fail");
            }

            Usuario usuario = usuarioDao.getByIdUsuarios(Long.parseLong(usuarioId));
            if (usuario != null) {
                AuthResponse response = new AuthResponse(token, usuario);
                return ResponseEntity.ok(response);
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Fail");
        } catch (SignatureException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
    }
}
