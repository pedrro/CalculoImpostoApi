package user.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void deveCriarUmNovoUsuario() throws Exception {
        User user = new User("Pedro", 1000.00);

        assertEquals(user.getName(),"Pedro");
        assertEquals(user.getSalary(), 1000.00,0);

    }
}