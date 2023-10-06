package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import metier.BanqueLocale;
import metier.entities.Compte;

//*********************Rest web service************************ client web service will be postman or advanced rest client google chrome

// sans sserver d'application, si vous voulez deployer votre service web rest, il faut deployer "jersy"
// il faut la delarer dans web.xml
//ie , deployer une servlet qui va representer le conteneur de jax-rs
//ici on a pas besoin de ça , car le serveur d'application jboss implemente de jaw-rs (qui est rest easy)
//il faut juste creer un context de l'application , une class qui etend "javax.ws.rs.core.Application"
@Stateless // if not used this , @Ejb not well work
@Path("/")
public class BanqueRestService {
	@EJB // equivalent the autowired on spring
	private BanqueLocale metier;

	@POST
	@Path("/comptes")
	@Produces(MediaType.APPLICATION_JSON)
	public Compte addCompte(Compte cp) {
		return metier.addCompte(cp);
	}

	@GET
	@Path("/comptes/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Compte getCompte(@PathParam(value = "code") Long code) {
		return metier.getCompte(code);
	}

	@GET
	@Path("/comptes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Compte> listComptes() {
		return metier.listComptes();
	}

	@PUT
	@Path("/comptes/verser")
	@Produces(MediaType.APPLICATION_JSON)
	public void verser(@FormParam(value = "code") Long code, @FormParam(value = "montant") double mnt) {
		metier.verser(code, mnt);
	}

	@PUT
	@Path("/comptes/retirer")
	@Produces(MediaType.APPLICATION_JSON)
	public void retirer(@FormParam(value = "code") Long code, @FormParam(value = "montant") double mnt) {
		metier.retirer(code, mnt);
	}

	@PUT
	@Path("/comptes/retirer")
	@Produces(MediaType.APPLICATION_JSON)
	public void virement(@FormParam(value = "code1") Long code1, @FormParam(value = "code2") Long code2,
			@FormParam(value = "montant") double mnt) {
		metier.virement(code1, code2, mnt);
	}

}
