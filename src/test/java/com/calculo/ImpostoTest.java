package com.calculo;


import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class ImpostoTest {

    @Test
    public void calculaImpostoParaUsuario() {
        UUID usuarioId = UUID.randomUUID();
        Imposto imposto = new Imposto(usuarioId, 1000.00);
        assertThat(imposto.getId(), is(notNullValue()));
        assertEquals(imposto.getInss(), 110.0, 0);
        assertEquals(imposto.getSegVida(), 200.0, 0);
        assertEquals(imposto.getVr(), 130.0, 0);
        assertEquals(imposto.getVt(), 60.0, 0);
        assertEquals(imposto.getCustoTotal(), 1500.0, 0);
    }
}