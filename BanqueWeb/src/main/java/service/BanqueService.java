package service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import metier.BanqueLocale;
import metier.entities.Compte;

//*********************SOAP web service************************ client web service will be soapUI
@WebService //interpreté 
public class BanqueService {
	@EJB // equivalent the autowired on spring
	private BanqueLocale metier;
	
	@WebMethod
	public Compte addCompte(@WebParam(name = "solde") double solde) {
		Compte cpt = new Compte();
		cpt.setSolde(solde);
		cpt.setDateCreation(new Date());
		return metier.addCompte(cpt);
	}

	@WebMethod
	public Compte getCompte(@WebParam(name = "code")Long code) {
		return metier.getCompte(code);
	}

	@WebMethod
	public List<Compte> listComptes() {
		return metier.listComptes();
	}

	@WebMethod
	public void verser(@WebParam(name = "code") Long code,
			           @WebParam(name = "montant") double mnt) {

		metier.verser(code, mnt);
	}

	@WebMethod
	public void retirer(
			@WebParam(name = "code") Long code,
			@WebParam(name = "montant") double mnt) {

		metier.retirer(code, mnt);
	}
	
	
	@WebMethod //optional
	public void virement(
			@WebParam(name = "cpte1") Long code1, 
			@WebParam(name = "cpte2") Long code2, 
			@WebParam(name = "montant") double mnt) {

		metier.virement(code1, code2, mnt);
	}

}
