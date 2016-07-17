package com.calculo.imposto.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UsuarioTest {


    @Test
    public void deveCriarUmNovoUsuario() throws Exception {
        Usuario usuario = new Usuario("Pedro", 1000.00);

        assertEquals(usuario.getNome(),"Pedro");
        assertEquals(usuario.getSalario(), 1000.00, 0);
        assertEquals(usuario.getInss(), 110,0);
        assertEquals(usuario.getSegVida(),200,0);
        assertEquals(usuario.getVr(),130,0);
        assertEquals(usuario.getVt(),60,0);
        assertEquals(usuario.getCustoTotal(),1500,0);
    }
}