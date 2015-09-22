import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Login {
	
	private ArrayList<Usuario> usuarios;
	
	public Login() throws IOException{
		 lerArquivo();
	}



	public void lerArquivo() throws IOException{
		Path path = Paths.get("D://contatos/user.txt");
		usuarios = new ArrayList<>();
		List<String> readAllLines = Files.readAllLines(path, Charset.defaultCharset());
		for(String linha : readAllLines){
			String[] posicao = linha.split(";");
			usuarios.add(new Usuario(posicao[0], posicao[1]));
		}
	}
	public boolean verificaLogin(Usuario login){
		for(Usuario usuario : this.usuarios){
			if(usuario.getNome().equals(login.getNome())){
				if(usuario.getSenha().equals(login.getSenha())){
					return true;
				}
			}
		}
		return false;
	}

}
