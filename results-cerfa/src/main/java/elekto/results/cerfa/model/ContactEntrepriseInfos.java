package elekto.results.cerfa.model;

/**
 * Informations sur la personne à contacter dans l'entreprise.
 */
public class ContactEntrepriseInfos {

    public String getEmail()
    {
        return this.email;
    }


    public String getFonction()
    {
        return this.fonction;
    }


    public String getNom()
    {
        return this.nom;
    }


    public String getPrenom()
    {
        return this.prenom;
    }


    public String getTelecopie()
    {
        return this.telecopie;
    }


    public String getTelephone()
    {
        return this.telephone;
    }


    public void setEmail(
            final String email)
    {
        this.email = email;
    }


    public void setFonction(
            final String fonction)
    {
        this.fonction = fonction;
    }


    public void setNom(
            final String nom)
    {
        this.nom = nom;
    }


    public void setPrenom(
            final String prenom)
    {
        this.prenom = prenom;
    }


    public void setTelecopie(
            final String telecopie)
    {
        this.telecopie = telecopie;
    }


    public void setTelephone(
            final String telephone)
    {
        this.telephone = telephone;
    }

    private String nom;

    private String prenom;

    private String fonction;

    private String telephone;

    private String telecopie;

    private String email;

}
