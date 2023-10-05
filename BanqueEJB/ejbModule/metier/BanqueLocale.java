package metier;

import java.util.List;

import javax.ejb.Local;

import metier.entities.Compte;

@Local
public interface BanqueLocale {
	public Compte addCompte(Compte cp);

	public Compte getCompte(Long code);

	public List<Compte> listComptes();

	public void verser(Long code, double mnt);

	public void retirer(Long code, double mnt);

	public void virement(Long code1, Long code2, double mnt);

}
