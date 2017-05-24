/**
 * TFG JEE-SimpleSPD - Component: Avis
 * @author Albert Boix Isern
 */

package ejb;

import java.util.Collection;
import javax.persistence.PersistenceException;

import jpa.AvisJPA;
import jpa.EmpresaJPA;

/**
 * EJB Session Bean Interface
 */
public interface AvisNegociRemote {
	/**
	 * M�tode per crear un nou av�s.
	 * @return un missatge en forma de String.
	 */
	public String crearAvis(String cifE, String cifR, String tipus, String descripcio);
	/**
	 * M�tode per consultar tots els avisos on la empresa apareix com a emissor o receptor.
	 * @return una colecci� amb els missatges.
	 */
	public Collection<AvisJPA> llistarAvisos(String cif)throws PersistenceException;
	/**
	 * M�tode per eliminar un av�s.
	 * * @return Un missatge en forma de String.
	 */
	public String eliminarAvis (int idAvis);
	/**
	 * M�tode que consulta totes les empreses i excepte la que realitza la consulta.
	 * @return Una col�lecci� amb les empresses.
	 */
	public Collection<EmpresaJPA> consultaEmpreses(String cif);
	/**
	 * M�tode per canviar l'estat d'un av�s.
	 * @return Un missatge en forma de String.
	 */
	public String canviarEstatAvis(int i);
}
