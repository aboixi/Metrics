/**
 * TFG JEE-SimpleSPD - Component: Full de Control
 * @author Albert Boix Isern
 */
package ejb;

import java.util.Collection;
import javax.persistence.PersistenceException;

import jpa.BlisterJPA;
import jpa.EmpresaJPA;
import jpa.PacientJPA;

/**
 * EJB Session Bean Interface
 */
public interface FullControlNegociRemote {
	/**
	 * M�tode que crea un full de control
	 * @param La id del full de treball associat, i el dni del treballador que el prepara.
	 */
	public void creaFullControl(int idFull, String dniP);
	/**
	 * M�tode que consulta els bl�sters associats a un pacient
	 * @param el cip del pacient
	 * @return una col�lecci� amb els bl�sters
	 */
	public Collection<BlisterJPA> consultarBlisters (String cip);
	/**
	 * M�tode per consultar els fulls de treball vinculats a una empresa
	 * @param el cif de l'empresa
	 * @return una col�lecci� de pacients
	 */
	public Collection<PacientJPA> llistarFulls (String cif);
	/**
	 * M�tode per validar un full de treball
	 * @param la id del full de treball i el dni del treballador que realitza la validaci�
	 * @return Un missatge amb el resultat del proc�s en forma de String
	 */
	public String validar(int idFull, String dniV)throws PersistenceException;
	/**
	 * M�tode que consulta un bl�ster a la base de dades
	 * @param idBlister
	 * @return blister
	 */
	public BlisterJPA imprimir(String idBlister)throws PersistenceException;
	/**
	 * M�tode per consultar una empresa
	 * @param el cif de l'empresa
	 * @return empresa La empresa
	 */
	public EmpresaJPA consultarEmpresa(String cif);
}
