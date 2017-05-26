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
	 * @param cif El cif de l'empresa
	 * @return pacients Col�lecci� de pacients
	 */
	public Collection<PacientJPA> llistarPacients (String cif);
	/**
	 * M�tode que elimina un pacient
	 * @param cip El cip del pacient
	 * @param cif El cif de l'empresa
	 * @return pacients Una col�lecci� amb els pacients que queden despr�s de la eliminaci�
	 */
	public Collection<PacientJPA> eliminarPacient(String cip, String cif);
	/**
	 * M�tode per modificar les dades personals d'un pacient
	 * @return String un missatge amb el resultat del proc�s en forma de String
	 */
	public String modificarPacient(String cif, String cip, String nom, String cognom1, String cognom2, String nomFarmacia, String farmacia, String malalties,String alergies, String metge, boolean autoritzacio, boolean spd, boolean hospitalitzat, boolean exitus) throws PersistenceException;
	/**
	 * Metode per consultar les farm�cies donades d'alta al sistema
	 * @return farmacies Una col�lecci� de farm�cies
	 */
	public Collection<EmpresaJPA> consultarFarmacies();
	/**
	 * M�tode per donar d'alta un pacient
	 * @return String un missatge amb el resultat del proc�s en forma de String
	 */
	public String crearPacient (String cip, String nom, String cognom1, String cognom2, String malalties,String alergies, String metge, String cifResidencia, String nomResidencia, String cifFarmacia, String nomFarmacia, boolean autoritzacio, boolean spd, boolean hospitalitzat, boolean exitus)throws PersistenceException;
	/**
	 * M�tode per consultar una empresa
	 * @param cif El cif de l'empresa
	 * @return empresa La empresa
	 */
	public EmpresaJPA consultarEmpresa(String cif);
}
