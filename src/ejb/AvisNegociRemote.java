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
	 * @return String Un missatge en forma de String.
	 */
	public String crearAvis(String cifE, String cifR, String tipus, String descripcio);
	/**
	 * M�tode per consultar tots els avisos on la empresa apareix com a emissor o receptor.
	 * @return String Una colecci� amb els missatges.
	 */
	public Collection<AvisJPA> llistarAvisos(String cif)throws PersistenceException;
	/**
	 * M�tode per consultar tots els avisos on la empresa apareix com a emissor o receptor.
	 * @return avisos Una colecci� amb els missatges.
	 */
	public String eliminarAvis (int idAvis);
	/**
	 * M�tode per canviar l'estat d'un av�s.
	 * @return String Un missatge en forma de String.
	 */
	public String canviarEstatAvis(int i);
	/**
	 * M�tode per eliminar un av�s.
	 * * @return String Un missatge en forma de String.
	 */
	public Collection<EmpresaJPA> consultaEmpreses(String cif);
}
