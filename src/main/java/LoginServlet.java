

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = -5127988891960766922L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Login login = new Login();
		Usuario usuario = new Usuario(req.getParameter("usuario"), req.getParameter("senha"));
		PrintWriter writer = resp.getWriter();	
		if(login.verificaLogin(usuario)){
//			writer.write(geraPagContato(usuario));
			req.getSession().setAttribute("usuario", req.getParameter("usuario"));
			resp.sendRedirect("/contatos/contato");
			
		}else{
			geraPagErroLogin(writer);
		}
	
		
	}

	private void geraPagErroLogin(PrintWriter writer) {
		writer.write("Usuário inválido");
		
	}
	

}
