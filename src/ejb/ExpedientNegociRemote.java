/**
 * TFG JEE-SimpleSPD - Component: Expedient Assistencial
 * @author Albert Boix Isern
 */
package ejb;

import java.util.Collection;
import java.util.Date;
import javax.persistence.PersistenceException;

import jpa.ExpedientJPA;
import jpa.MedicamentJPA;
import jpa.PacientJPA;

/**
 * EJB Session Bean Interface 
 */
public interface ExpedientNegociRemote {

	/**
	 * M�tode que consulta, donat el cif d'una empresa, els pacients que t� vinculats.
	 * Ja sigui com a resident d'una resid�ncia o estigui assignat a la farm�cia.
	 * @param cif de l'empresa
	 * @return Una col�lecci� amb els pacients.
	 */
	public Collection<PacientJPA> llistarExpedients(String cif);
	/**
	 * M�tode que consulta l'expedient donada la seva id
	 * @param id de l'expedient
	 * @return L'expedient 
	 */
	public ExpedientJPA consultarExpedient(int idExpedient);
	/**
	 * M�tode que agrega un tractament a un pacient
	 * Un missatge en forma de String amb el resultat de la operaci�
	 */
	public String agregarTractament(String idExpedient, String cn,Date dInici, String qEntera, String qFraccio, boolean esmorcar, boolean dinar, boolean sopar, boolean dormir,boolean dill, boolean dima, boolean dime, boolean dijo, boolean dive, boolean diss, boolean dium, boolean foraBlister) throws PersistenceException;
	/**
	 * M�tode que elimina el tractament d'un pacient donada la seva id
	 * @param La id del tractament
	 */
	public void eliminarTractament(int id)throws PersistenceException;
	/**
	 * M�tode que busca a la base de dades els medicaments
	 * @param La paraula clau per realitzar la recerca
	 * @return Una col�lecci� amb els medicaments trobats.
	 */
	public Collection<MedicamentJPA> buscarMedicaments(String paraula); 
}
