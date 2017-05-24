/**
 * TFG JEE-SimpleSPD - Component: Pacients
 * @author Albert Boix Isern
 */
package ejb;

import java.util.Collection;

import javax.persistence.PersistenceException;

import jpa.EmpresaJPA;
import jpa.PacientJPA;

/**
 * EJB Session Bean Interface
 */
public interface PacientsNegociRemote {
	/**
	 * M�tode que consulta els pacients vinculats a una empresa
	 * @param cif de l'empresa
	 * @return col�lecci� de pacients
	 */
	public Collection<PacientJPA> llistarPacients (String cif);
	/**
	 * M�tode que elimina un pacient
	 * @param cip del pacient i cif de l'empresa
	 * @return una col�lecci� amb els pacients que queden despr�s de la eliminaci�
	 */
	public Collection<PacientJPA> eliminarPacient(String cip, String cif);
	/**
	 * M�tode per modificar les dades personals d'un pacient
	 * @param tots els camps excepte el cip
	 * @return un missatge amb el resultat del proc�s en forma de String
	 */
	public String modificarPacient(String cif, String cip, String nom, String cognom1, String cognom2, String nomFarmacia, String farmacia, String malalties,String alergies, String metge, boolean autoritzacio, boolean spd, boolean hospitalitzat, boolean exitus) throws PersistenceException;
	/**
	 * M�tode per consultar les farm�cies donades d'alta al sistema
	 * @return una col�lecci� de farm�cies
	 */
	public Collection<EmpresaJPA> consultarFarmacies();
	/**
	 * M�tode per donar d'alta un pacient
	 * @param les dades del pacient
	 * @return un missatge amb el resultat del proc�s en forma de String
	 */
	public String crearPacient (String cip, String nom, String cognom1, String cognom2, String malalties,String alergies, String metge, String cifResidencia, String nomResidencia, String cifFarmacia, String nomFarmacia, boolean autoritzacio, boolean spd, boolean hospitalitzat, boolean exitus)throws PersistenceException;
	/**
	 * M�tode per consultar una empresa
	 * @param el cif de l'empresa
	 * @return la empresa
	 */
	public EmpresaJPA consultarEmpresa(String cif);
}
