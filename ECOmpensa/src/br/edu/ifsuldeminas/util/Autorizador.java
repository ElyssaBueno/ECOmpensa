package br.edu.ifsuldeminas.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.edu.ifsuldeminas.modelo.Funcionalidades;
import br.edu.ifsuldeminas.modelo.Usuarios;

public class Autorizador implements PhaseListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		// Obtém contexto da aplicação 
		FacesContext context = event.getFacesContext();
		// Obtém o nome da página que está sendo chamada
	    String nomePagina = context.getViewRoot().getViewId();

	    // System.out.println(nomePagina);

	    // se for a página de login, o usuário pode acessar 
	    if ("/login.xhtml".equals(nomePagina)) {
	        return;
	    }

	    // Obtém usuário da sessão
	    Usuarios usuarioLogado = 
	    	(Usuarios) context.getExternalContext().getSessionMap().get("usuarioLogado");

	    // se há usuário logado, ele pode acessar as páginas
	    if(usuarioLogado != null) {
	    	if(nomePagina.equals("/index.xhtml")){
	    		return;
	    	}
	    	Funcionalidades func = (Funcionalidades)
	    	context.getExternalContext().getSessionMap().get(nomePagina);
	    	if(func!=null){
	    		return;
	    	}
	    	NavigationHandler handler = context.getApplication().getNavigationHandler();
	    	handler.handleNavigation(context, null, "/index?faces-redirect=true");
	 	    context.renderResponse();
	        return;
	    }

	    // se não há, o usuário é redirecionado para o login
	    NavigationHandler handler = context.getApplication().getNavigationHandler();
	    handler.handleNavigation(context, null, "/login?faces-redirect=true");
	    context.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW; // o autorizador será executado na fase restore_view
	}

}
