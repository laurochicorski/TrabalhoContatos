import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/contato")
public class ContatoServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter writer = resp.getWriter();
		
		if(req.getParameter("nome") == null){
			writer.write(geraPagContato(new Usuario((String) req.getSession().getAttribute("usuario"))));
		}else{
			ListaContato listaContato = new ListaContato(new Usuario((String) req.getSession().getAttribute("usuario")));
			listaContato.adicionarContato(req.getParameter("nome"), req.getParameter("telefone"));
			resp.sendRedirect("/contatos/contato");
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ListaContato listaContato = new ListaContato(new Usuario((String) req.getSession().getAttribute("usuario")));
		listaContato.removerContato(req.getParameter("nome"));
		resp.sendRedirect("/contatos/contato");
	}
	
	private String geraPagContato(Usuario usuario) throws IOException{
		StringBuilder sb = new StringBuilder();
		ListaContato listaContato = new ListaContato(usuario);
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>Contatos</title>");
		sb.append("<link rel='stylesheet' href='resources/bootstrap-3.3.5-dist/css/bootstrap.min.css'>");
		sb.append("<link rel='stylesheet' href='resources/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css'>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<div class='container'>");
		sb.append("<div class='page-header'>");
		sb.append("<h1>Contatos</h1>");
		sb.append("</div>");
		sb.append("<form action='contato'>");
		sb.append("<input name='nome' placeholder = 'Digite o nome'>");
		sb.append("<input name='telefone' placeholder = 'Digite o telefone'>");
		sb.append("<button type='submit' class='btn btn-success'>");
		sb.append("Adicionar");
		sb.append("</button>");
		sb.append("</form>");
		sb.append("<table class='table table-striped'>");
		sb.append("<tr>");
		sb.append("<td>Nome</td>");
		sb.append("<td>Telefone</td>");
		sb.append("<td></td>");
		sb.append("</tr>");
		for(Contato contato : listaContato.getListaContatos()){
			if(contato.getUsuario().equals(usuario.getNome())){
				sb.append("<tr>");
				sb.append("<form action='contato' method='post'>");
				sb.append("<td>");
				sb.append("<input name='nome' value="+ contato.getNome() + ">");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<input name='telefone' value="+ contato.getTelefone() + ">");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<button type='submit' class='btn btn-danger'>");
				sb.append("Excluir");
				sb.append("</button>");
				sb.append("</td>");
				sb.append("</form>");
				sb.append("</tr>");
			}
		}
		sb.append("</table>");
		sb.append("</div>");
		sb.append("</body>");
		sb.append("</html>");
		
		return sb.toString();
		
	}

}
