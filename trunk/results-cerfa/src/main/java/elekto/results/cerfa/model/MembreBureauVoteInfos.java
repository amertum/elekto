package elekto.results.cerfa.model;

/**
 * Informations sur les membres du bureau de vote en vue de leur signature.
 */
public class MembreBureauVoteInfos {

    public String getNom()
    {
        return this.nom;
    }


    public String getOrganisation()
    {
        return this.organisation;
    }


    public String getPrenom()
    {
        return this.prenom;
    }


    public void setNom(
            final String nom)
    {
        this.nom = nom;
    }


    public void setOrganisation(
            final String organisation)
    {
        this.organisation = organisation;
    }


    public void setPrenom(
            final String prenom)
    {
        this.prenom = prenom;
    }

    private String nom;

    private String prenom;

    private String organisation;

}
