package imposto.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import imposto.api.Client.Usuario;
import imposto.api.Client.UsuarioClient;

import static java.util.UUID.fromString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ImpostoControllerTest {

    private MockMvc mockMvc;
    @Autowired @Mock private UsuarioClient usuarioClient;
    @Autowired @Mock private ImpostoRepository impostoRepository;
    @InjectMocks private ImpostoController impostoController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(impostoController).build();
    }

    @Test
    public void criaImpostoParaUsuario() throws Exception {
        Usuario usuarioMock = new Usuario(fromString("c2777f8d-0289-4024-9d4f-551ff441b1db"),"Pedro", 1000.0);
        Imposto impostoMock = new Imposto(usuarioMock.getId(),usuarioMock.getSalario());

        doReturn(usuarioMock).when(usuarioClient).pegaUsuarioPorId(fromString("c2777f8d-0289-4024-9d4f-551ff441b1db"));
        doReturn(impostoMock).when(impostoRepository).save(impostoMock);

        mockMvc.perform(post("/calculaImposto/c2777f8d-0289-4024-9d4f-551ff441b1db"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.vr", is(130.0)))
                .andExpect(jsonPath("$.vt", is(60.0)))
                .andExpect(jsonPath("$.inss", is(110.0)))
                .andExpect(jsonPath("$.segVida", is(200.0)))
                .andExpect(jsonPath("$.custoTotal", is(1500.00)));
    }
}