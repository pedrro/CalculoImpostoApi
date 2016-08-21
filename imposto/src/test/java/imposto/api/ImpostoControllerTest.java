package imposto.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static java.util.UUID.fromString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = ImpostoApplication.class)
@WebIntegrationTest(randomPort = true)
@ContextConfiguration(classes = ImpostoApplication.class)
public class ImpostoControllerTest {

    private MockMvc mockMvc;
    @Autowired private WebApplicationContext webApplicationContext;
    @Autowired @Mock private UsuarioRepository usuarioRepository;
    @Autowired @Mock private ImpostoRepository impostoRepository;
    @Autowired @InjectMocks private ImpostoController impostoController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void criaImpostoParaUsuario() throws Exception {
        Usuario usuarioMock = new Usuario(fromString("c2777f8d-0289-4024-9d4f-551ff441b1db"),"Pedro", 1000.0);
        Imposto impostoMock = new Imposto(usuarioMock.getId(),usuarioMock.getSalario());
        when(usuarioRepository.findOne(fromString("c2777f8d-0289-4024-9d4f-551ff441b1db"))).thenReturn(usuarioMock);
        when(impostoRepository.save(impostoMock)).thenReturn(impostoMock);
//        doReturn(usuarioMock).when(usuarioRepository).findOne(fromString("c2777f8d-0289-4024-9d4f-551ff441b1db"));
//        doReturn(impostoMock).when(impostoRepository).save(impostoMock);

        mockMvc.perform(post("/calculaImposto/c2777f8d-0289-4024-9d4f-551ff441b1db"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.vr", is(130.0)))
                .andExpect(jsonPath("$.vt", is(60.0)))
                .andExpect(jsonPath("$.inss", is(110.0)))
                .andExpect(jsonPath("$.segVida", is(200.0)))
                .andExpect(jsonPath("$.custoTotal", is(1500.00)));
    }
}