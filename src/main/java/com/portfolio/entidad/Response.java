
package com.portfolio.entidad;


public class Response {
    String usuario;
    Integer login;

    public Response(String usuario, Integer login) {
        this.usuario = usuario;
        this.login = login;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setLogin(Integer login) {
        this.login = login;
    }

    public String getUsuario() {
        return usuario;
    }

    public Integer getLogin() {
        return login;
    }
}
