package managedbean.expedient;

import java.io.Serializable;
import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

import ejb.ExpedientNegociRemote;
import jpa.TractamentJPA;


@ManagedBean (name="eliminarTractament")
@SessionScoped
public class EliminarTractamentMBean implements Serializable{
	
	@EJB (name="ExpedientsNegociEJB")
	ExpedientNegociRemote expedientRemotEJB;
	private TractamentJPA tractament;
	@SuppressWarnings("unused")
	private boolean sessionOK=false;
	private static final long serialVersionUID = 1L;
	
	public String eliminarTractament()throws Exception{
		if (checkSession()){
			Properties props = System.getProperties();
			Context ctx = new InitialContext(props);
			expedientRemotEJB = (ExpedientNegociRemote) ctx.lookup("java:app/SPD.jar/ExpedientNegociEJB!ejb.ExpedientNegociRemote");
			try{
				expedientRemotEJB.eliminarTractament(tractament.getIdTractament());
			}catch (Exception e){
				System.out.println(e);
			}
			return "vistaUsuariModificarExpedient";
		}else{
			return "accessError";
		}
	}

	public boolean checkSession(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession activeSession = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		if (activeSession!=null && activeSession.getAttribute("sessioUsuari")!=null){
			return (this.sessionOK=true);
		}else{
			return (this.sessionOK=false);
		}
	}

	/**
	 * @return the tractament
	 */
	public TractamentJPA getTractament() {
		return tractament;
	}

	/**
	 * @param tractament the tractament to set
	 */
	public void setTractament(TractamentJPA tractament) {
		this.tractament = tractament;
	}
}

