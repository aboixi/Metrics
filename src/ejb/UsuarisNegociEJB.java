/**
 * TFG JEE-SimpleSPD - Component: Usuaris
 * @author Albert Boix Isern
 */

package ejb;

import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import jpa.EmpresaJPA;
import jpa.UsuariEmpresaJPA;
/**
 * EJB Session Bean Class
 */
@Stateless
public class UsuarisNegociEJB implements UsuarisNegociRemote{
	@PersistenceContext(unitName="SPD") 
	private EntityManager entman;
	@Resource
	private SessionContext sessionContext;
	
	/**
	 *M�tode per registrar una nova empresa al sistema
	 *@return String Un missatge amb el resultat del proc�s en forma de String 
	 */
	public String registrarEmpresa (String cif, String nom, String poblacio, String carrer, String cp, String telefon, String fax,String correu, String clau, String contacte, String tipus)throws PersistenceException{
		String missatge;
		String correuExistent=null;
		EmpresaJPA empresa = entman.find(EmpresaJPA.class, cif);
		try{
			String query = "SELECT u.cif "+"FROM EmpresaJPA u "+"WHERE u.correu = '" + correu +"'";
			correuExistent = entman.createQuery(query, String.class).getSingleResult();
		}catch (PersistenceException e) {
			System.out.println(e);
		}	
		if(!(empresa==null)){								//Es verifica que la empresa no existeix.
			missatge="errorEmpresaExistent";
			return missatge;
		}else{												//Es verifica que el correu est� lliure.
			if(!(correuExistent==null)){
				missatge="errorCorreuRepetit";
				return missatge;
			}else{											//Es registra l'empresa.
				empresa = new EmpresaJPA(cif,nom,poblacio,carrer,cp,telefon,fax,correu,clau,contacte,tipus);
				entman.persist(empresa);
				missatge="registreCorrecte";
				return missatge;
			}
		}
	}
	/**
	 * M�tode per identificar-se al sistema. Es comprova si es tracta d'una empresa o d'un usuari.
	 * @param usuari Nom d'usuari 
	 * @param clau Clau d'acc�s
	 * @return String Si el la identificaci� �s correcta, retorna l'objecte de qui s'ha identificat, usuari o empresa.
	 * @return String Si la identificaci� �s incorrecta, retorna un usuari amb els camps "invalid" 
	 */
	public Object login(String usuari, String clau)throws PersistenceException{	
		EmpresaJPA empresaInvalida = new EmpresaJPA();
		UsuariEmpresaJPA usuariEmpresaInvalid = new UsuariEmpresaJPA();
		empresaInvalida.setCif("invalid");
		usuariEmpresaInvalid.setDni("invalid");
		try{
			if (usuari.contains("@")){
				String query = "SELECT u.cif "+"FROM EmpresaJPA u "+"WHERE u.correu = '" + usuari +"'";
				String loginCif = entman.createQuery(query, String.class).getSingleResult();
				EmpresaJPA empresa = entman.find(EmpresaJPA.class,loginCif);
				if (empresa.getClau().equals(clau)){
					return empresa;
				}else{
					return empresaInvalida;
				}
			}else{
				String query = "SELECT u.dni "+"FROM UsuariEmpresaJPA u "+"WHERE u.usuari = '" + usuari +"'";
				String loginDni = entman.createQuery(query, String.class).getSingleResult();
				UsuariEmpresaJPA usuariEmpresa = entman.find(UsuariEmpresaJPA.class,loginDni);
				if (usuariEmpresa.getClau().equals(clau)){
					return usuariEmpresa;
				}else{
					return usuariEmpresaInvalid;
				}
			}
		}catch (PersistenceException e) {
			System.out.println(e);
		}
		return empresaInvalida;
	}
	/**
	 * M�tode per modificar les dades de l'empresa
	 * @return empresa L'objecte modificat
	 */
	public EmpresaJPA modificarEmpresa(String nif, String nom, String poblacio, String carrer, String cp, String telefon,
			String fax, String correu, String clau, String contacte) {
		
		EmpresaJPA empresa = entman.find(EmpresaJPA.class, nif);
		
		empresa.setNom(nom);
		empresa.setPoblacio(poblacio);
		empresa.setCarrer(carrer);
		empresa.setCp(cp);
		empresa.setTelefon(telefon);
		empresa.setFax(fax);
		empresa.setCorreu(correu);
		empresa.setClau(clau);
		empresa.setContacte(contacte);	
		entman.merge(empresa);
		entman.flush();
		entman.refresh(empresa);		
		return empresa;
	}
	
	/**
	 * M�tode per donar d'alta un usuari d'una empresa al sistema.
	 * @return String Un missatge amb el resultat del proc�s en forma de String
	 */
	public String crearUsuari (String dni, String nom, String cognom1, String cognom2, String telefon, String empresa, String tipus)throws PersistenceException{	
		UsuariEmpresaJPA usuari = entman.find(UsuariEmpresaJPA.class, dni);
		UsuariEmpresaJPA usuariRepetit = null;
		if (usuari==null){
			Character primer = nom.charAt(0);
			String segon = cognom1;
			Character tercer = cognom2.charAt(0);
			String nomUsuari=primer.toString().concat(segon).concat(tercer.toString()).toLowerCase();
			String clau="123456789";
			try{
				usuariRepetit=(UsuariEmpresaJPA) entman.createQuery("FROM UsuariEmpresaJPA a WHERE a.usuari = '" + nomUsuari +"'").getSingleResult();
			}catch (PersistenceException e) {
				System.out.println(e);
				}
			if (usuariRepetit==null){
				usuari = new UsuariEmpresaJPA(dni,nom,cognom1,cognom2,telefon,empresa, nomUsuari, clau, tipus);
				entman.persist(usuari);
				return "procesCorrecte";
			}else{
				nomUsuari=dni.toLowerCase();
				usuari = new UsuariEmpresaJPA(dni,nom,cognom1,cognom2,telefon,empresa, nomUsuari, clau, tipus);
				entman.persist(usuari);
				return "nomUsuariRepetit";
				}
		}else{
			return "usuariExistent";
			}
	}
	/**
	 * M�tode per consultar els usuari vinculats a l'empresa
	 * @param cif El cif de l'empresa
	 * @return usuaris La col�lecci� d'usuaris
	 */
	public Collection<UsuariEmpresaJPA> llistarUsuaris (String cif){
		@SuppressWarnings("unchecked")
		Collection<UsuariEmpresaJPA> usuaris = entman.createQuery("FROM UsuariEmpresaJPA a WHERE a.empresa = '" + cif +"'").getResultList();
	    return usuaris;
	}
	/**
	 * M�tode per eliminar un usuari vinculat a l'empresa
	 * @param cif El cif de l'empresa.
	 * @param dni El dni de l'usuari que es vol eliminar.
	 * @return usuaris Una col�lecci� amb els usuaris de l'empresa despr�s de la eliminaci�.
	 */
	public Collection<UsuariEmpresaJPA> eliminarUsuari(String cif, String dni){
		UsuariEmpresaJPA usuari=null;
		@SuppressWarnings("unchecked")
		Collection<UsuariEmpresaJPA> usuaris = entman.createQuery("FROM UsuariEmpresaJPA a WHERE a.empresa = '" + cif +"'").getResultList();
		Iterator<UsuariEmpresaJPA> iter = usuaris.iterator();
		boolean trobat = false;
		while (iter.hasNext()&&!(trobat)){
			usuari = iter.next();
			if(usuari.getDni().equals(dni)){
				entman.remove(usuari);
				usuaris.remove(usuari);
				trobat=true;
			}
		}
		return usuaris;
	}
	/**
	 * M�tode per modificar les dades d'un usuari. No es pot modificar ni el nom, ni el nom d'usuari
	 * @return String Un missatge amb el resultat del proc�s en forma de String
	 */
	public String modificarUsuari(String dni, String nom, String cognom1, String cognom2, String telefon, String clau)throws PersistenceException{
		UsuariEmpresaJPA usuari = entman.find(UsuariEmpresaJPA.class, dni);
		usuari.setNom(nom);
		usuari.setCognom1(cognom1);
		usuari.setCognom2(cognom2);
		usuari.setTelefon(telefon);
		usuari.setClau(clau);
		try{
			entman.merge(usuari);
			entman.flush();
			entman.refresh(usuari);
			return "canviCorrecte";
		}catch (PersistenceException e) {
			System.out.println(e);
			return "persistenceException";
		}
	}
}

