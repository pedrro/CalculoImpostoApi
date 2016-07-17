package com.calculo.imposto.controller;

import com.calculo.imposto.ApiCalculoImpostoApplication;
import com.calculo.imposto.model.Usuario;
import com.calculo.imposto.repository.UsuarioRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApiCalculoImpostoApplication.class)
@WebIntegrationTest(randomPort = true)
@ContextConfiguration(classes = ApiCalculoImpostoApplication.class)
public class UsuarioControllerTest {

    public static final String JSON = "{\"nome\":\"Pedro\",\"salario\":1000.00}";
    private MockMvc mockMvc;
    @Autowired private WebApplicationContext webApplicationContext;
    @Autowired @Mock private UsuarioRepository usuarioRepository;
    @Autowired @InjectMocks private UsuarioController usuarioController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void criaUmNovoUsuario() throws Exception {
        Usuario mock = new Usuario("Pedro",1000.00);
        when(usuarioRepository.save(mock)).thenReturn(mock);

        mockMvc.perform(post("/usuario")
        .content(JSON)
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.nome", is("Pedro")))
        .andExpect(jsonPath("$.vr", is(130.0)))
        .andExpect(jsonPath("$.vt", is(60.0)))
        .andExpect(jsonPath("$.inss", is(110.0)))
        .andExpect(jsonPath("$.segVida", is(200.0)))
        .andExpect(jsonPath("$.custoTotal", is(1500.00)));
    }

    @Test
    public void retornaTodosOsUsuarios() throws Exception {
        Usuario mock1 = new Usuario("Pedro1",1000.00);
        Usuario mock2 = new Usuario("Pedro2",1000.00);
        List<Usuario> usuariosMock = new ArrayList<Usuario>();
        usuariosMock.add(mock1);
        usuariosMock.add(mock2);
        when(usuarioRepository.findAll()).thenReturn(usuariosMock);

        mockMvc.perform(get("/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].nome", is("Pedro1")))
                .andExpect(jsonPath("[1].nome", is("Pedro2")));
    }
}