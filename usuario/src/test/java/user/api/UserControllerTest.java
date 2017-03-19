package user.api;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.fromString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    public static final String JSON = "{\"name\":\"Pedro\",\"salary\":1000.00}";
    private MockMvc mockMvc;
    @Autowired @Mock private UserRepository userRepository;
    @InjectMocks private UserController userController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void shouldCreateNewUser() throws Exception {
        User mock = new User("Pedro", 1000.00);
        when(userRepository.save(mock)).thenReturn(mock);

        mockMvc.perform(post("/usuario")
                .content(JSON)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is("Pedro")));
    }

    @Test
    public void shouldReturnAllUsers() throws Exception {
        User mock1 = new User("Pedro1", 1000.00);
        User mock2 = new User("Pedro2", 1000.00);
        List<User> userMock = new ArrayList<User>();
        userMock.add(mock1);
        userMock.add(mock2);
        doReturn(userMock).when(userRepository).findAll();

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name", is("Pedro1")))
                .andExpect(jsonPath("[1].name", is("Pedro2")));
    }

    @Test
    public void shouldDeleteUserById() throws Exception {
        User user = new User(fromString("c2777f8d-0289-4024-9d4f-551ff441b1db"), "Pedro", 1000.00);
        doReturn(user).when(userRepository).findOne(fromString("c2777f8d-0289-4024-9d4f-551ff441b1db"));

        mockMvc.perform(delete("/usuario/c2777f8d-0289-4024-9d4f-551ff441b1db"))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.id", is("c2777f8d-0289-4024-9d4f-551ff441b1db")));
    }

    @Test
    public void shouldReturnAnUser() throws Exception {
        User user = new User(fromString("c2777f8d-0289-4024-9d4f-551ff441b1db"), "Pedro", 1000.00);
        doReturn(user).when(userRepository).findOne(fromString("c2777f8d-0289-4024-9d4f-551ff441b1db"));

        mockMvc.perform(get("/usuario/c2777f8d-0289-4024-9d4f-551ff441b1db"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("c2777f8d-0289-4024-9d4f-551ff441b1db")));
    }
}