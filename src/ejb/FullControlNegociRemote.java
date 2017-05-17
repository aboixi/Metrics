package ejb;

import java.util.Collection;

import jpa.FullDeControlJPA;
import jpa.PacientJPA;

public interface FullControlNegociRemote {
	public void creaFullControl(int idFull, String dniP);
	public Collection<FullDeControlJPA> consultarFulls(String cip);
	public Collection<PacientJPA> llistarFulls (String cif);
}
