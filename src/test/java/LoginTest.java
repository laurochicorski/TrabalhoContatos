import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class LoginTest {
	
	Usuario u1 = new Usuario("Lauro", "123");
	Usuario u2 = new Usuario("Marcelo", "568");
	Usuario u3 = new Usuario("Daniel", "123");
	
	Login login;
	
	@Before
	public void setUp() throws IOException{
		login = new Login();
		login.lerArquivo();
	}
	
//	@Test
//	public void teste1(){
//		Usuario u1 = new Usuario("Lauro", "123");
//		assertThat(login.verificaLogin(u1), is(true));
//	}

}
