package com.usuario;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UsuarioTest {


    @Test
    public void deveCriarUmNovoUsuario() throws Exception {
        Usuario usuario = new Usuario("Pedro", 1000.00);

        assertEquals(usuario.getNome(),"Pedro");
        assertEquals(usuario.getSalario(), 1000.00, 0);

    }
}