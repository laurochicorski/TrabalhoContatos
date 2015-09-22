
public class Contato {
	
	private String nome;
	private String telefone;
	private String usuario;
	
	public Contato(){
		
	}

	public Contato(String nome, String telefone, String usuario) {
		this.usuario = usuario;
		this.nome = nome;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
