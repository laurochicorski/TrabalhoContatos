import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ListaContato {
	
	private Usuario usuario;
	private ArrayList<Contato> listaContatosUsuario;
	
	public ListaContato(){
		this.listaContatosUsuario = new ArrayList<>();
	}
	
	public ListaContato(Usuario usuario) throws IOException{
		this.usuario = usuario;
		this.listaContatosUsuario = new ArrayList<>();
		carregarListaUsuario();
	}
	
	private void carregarListaUsuario() throws IOException{
		Path path = Paths.get("D://contatos/contatos.txt");
		List<String> readAllLines = Files.readAllLines(path, Charset.defaultCharset());
		for(String linha : readAllLines){
			String[] posicao = linha.split(";");
			listaContatosUsuario.add(new Contato(posicao[1], posicao[2], posicao[0]));
		}
	}

	private void salvarLista() throws IOException{
		Path path = Paths.get("D://contatos/contatos.txt");
		StringBuilder conteudo = new StringBuilder();
		for(int i = 0; i < this.listaContatosUsuario.size(); i++){
			conteudo.append(listaContatosUsuario.get(i).getUsuario());
			conteudo.append(";");
			conteudo.append(listaContatosUsuario.get(i).getNome());
			conteudo.append(";");
			conteudo.append(listaContatosUsuario.get(i).getTelefone());
			if(i != this.listaContatosUsuario.size() - 1){
				conteudo.append("\r\n");
			}
		}
		Files.delete(path);
		OpenOption modoDeAbertura = Files.exists(path) ? WRITE : CREATE;
		Files.write(path, conteudo.toString().getBytes(), modoDeAbertura);
	}
	
	public void removerContato(String nomeContato) throws IOException{
		
		for(int i = 0; i < this.listaContatosUsuario.size(); i++){
			if(listaContatosUsuario.get(i).getNome().equals(nomeContato)){
				this.listaContatosUsuario.remove(i);
				break;
			}
		}
		salvarLista();
	}
	
	public void adicionarContato(String nome, String telefone) throws IOException{
		Contato contato  = new Contato();
		contato.setNome(nome);
		contato.setTelefone(telefone);
		contato.setUsuario(this.usuario.getNome());
		this.listaContatosUsuario.add(contato);
		salvarLista();
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArrayList<Contato> getListaContatos() {
		return listaContatosUsuario;
	}

	public void setListaContatos(ArrayList<Contato> listaContatos) {
		this.listaContatosUsuario = listaContatos;
	}
}
