package com.mantenimiento.spring.models;
public class AuthResponse {
    private String tokenJwt;
    private Long id;
    private String nombre;
    private String apellido;
    private String email;

    public AuthResponse(String tokenJwt, Usuario usuario) {
        this.tokenJwt = tokenJwt;
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.email = usuario.getEmail();
    }

    public String getTokenJwt() {
        return tokenJwt;
    }

    public void setTokenJwt(String tokenJwt) {
        this.tokenJwt = tokenJwt;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }
}
