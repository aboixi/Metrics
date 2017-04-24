/**
 * TFG JEE-SimpleSPD - Component: Usuaris
 * @author Albert Boix Isern
 */

package managedbean;

import java.io.Serializable;
import java.util.Properties;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import ejb.UsuarisNegociRemote;
import jpa.EmpresaJPA;
import jpa.UsuariEmpresaJPA;

/**
 * Bean to login into system
 */
@ManagedBean(name = "loginUsuari")
@SessionScoped
public class LoginMBean implements Serializable{
	
	@EJB(name="UsuarisNegociEJB")
	UsuarisNegociRemote usuarisRemotEJB;
	private String nom;
	private String clau;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Method to login
	 */
	public String login() throws Exception{	
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession activeSession = (HttpSession) facesContext.getExternalContext().getSession(true);
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		usuarisRemotEJB = (UsuarisNegociRemote) ctx.lookup("java:app/SPD.jar/UsuarisNegociEJB!ejb.UsuarisNegociRemote");
		Object usuari = usuarisRemotEJB.login(nom, clau);
		String tipusUsuari=usuari.getClass().getName();

		if (tipusUsuari=="jpa.EmpresaJPA"){ 
			EmpresaJPA empresa = (EmpresaJPA) usuari;
			if (empresa.getCif().equals("invalid")){
				return "accessError";
			}else{
				activeSession.setAttribute("sessioEmpresa", empresa);
				return "vistaPerfilEmpresa";
			}
		}else{
			UsuariEmpresaJPA usuariEmpresa = (UsuariEmpresaJPA) usuari;
			if (usuariEmpresa.getDni().equals("invalid")){
				return "accessError";
			}else{
				activeSession.setAttribute("sessioEmpresa", usuariEmpresa);
				return "vistaPerfilEmpresa";
			}			
		}
	}
	
	/**
	 * Set an error message to be show in the error view.
	 * @param e
	 */
	public void setErrorMsg(String e){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession activeSession = (HttpSession) facesContext.getExternalContext().getSession(true);
		activeSession.setAttribute("errorMsg", e);
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the clau
	 */
	public String getClau() {
		return clau;
	}

	/**
	 * @param clau the clau to set
	 */
	public void setClau(String clau) {
		this.clau = clau;
	}
}
