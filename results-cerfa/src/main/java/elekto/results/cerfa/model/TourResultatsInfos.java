package elekto.results.cerfa.model;

import java.util.Date;

/**
 * Informations sur les résultats de l'élection.
 */
public class TourResultatsInfos {

    public int getBulletinsBlancsOuNulsNombre()
    {
        return this.bulletinsBlancsOuNulsNombre;
    }


    public int getBulletinsValablesNombre()
    {
        return this.bulletinsValablesNombre;
    }


    public Date getDate()
    {
        return this.date;
    }


    public int getElecteursInscritsNombre()
    {
        return this.electeursInscritsNombre;
    }


    public int getListesPresenteesNombre()
    {
        return this.listesPresenteesNombre;
    }


    public float getQuorum()
    {
        return this.quorum;
    }


    public float getQuotientElectoral()
    {
        return this.quotientElectoral;
    }


    public int getQuotientElectoralDenominateur()
    {
        return this.quotientElectoralDenominateur;
    }


    public float getQuotientElectoralNumerateur()
    {
        return this.quotientElectoralNumerateur;
    }


    public int getSiegesPourvoirNombre()
    {
        return this.siegesPourvoirNombre;
    }


    public int getVotantsNombre()
    {
        return this.votantsNombre;
    }


    public boolean isCarenceNon()
    {
        return this.carenceNon;
    }


    public boolean isCarenceOui()
    {
        return this.carenceOui;
    }


    public boolean isQuorumAtteintNon()
    {
        return this.quorumAtteintNon;
    }


    public boolean isQuorumAtteintOui()
    {
        return this.quorumAtteintOui;
    }


    public void setBulletinsBlancsOuNulsNombre(
            final int bulletinsBlancsOuNulsNombre)
    {
        this.bulletinsBlancsOuNulsNombre = bulletinsBlancsOuNulsNombre;
    }


    public void setBulletinsValablesNombre(
            final int bulletinsValablesNombre)
    {
        this.bulletinsValablesNombre = bulletinsValablesNombre;
    }


    public void setCarenceNon(
            final boolean carenceNon)
    {
        this.carenceNon = carenceNon;
    }


    public void setCarenceOui(
            final boolean carenceOui)
    {
        this.carenceOui = carenceOui;
    }


    public void setDate(
            final Date date)
    {
        this.date = date;
    }


    public void setElecteursInscritsNombre(
            final int electeursInscritsNombre)
    {
        this.electeursInscritsNombre = electeursInscritsNombre;
    }


    public void setListesPresenteesNombre(
            final int listePresenteesNombre)
    {
        this.listesPresenteesNombre = listePresenteesNombre;
    }


    public void setQuorum(
            final float quorum)
    {
        this.quorum = quorum;
    }


    public void setQuorumAtteintNon(
            final boolean quorumAtteintNon)
    {
        this.quorumAtteintNon = quorumAtteintNon;
    }


    public void setQuorumAtteintOui(
            final boolean quorumAtteintOui)
    {
        this.quorumAtteintOui = quorumAtteintOui;
    }


    public void setQuotientElectoral(
            final float quotientElectoral)
    {
        this.quotientElectoral = quotientElectoral;
    }


    public void setQuotientElectoralDenominateur(
            final int quotientElectoralDenominateur)
    {
        this.quotientElectoralDenominateur = quotientElectoralDenominateur;
    }


    public void setQuotientElectoralNumerateur(
            final float quotientElectoralNumerateur)
    {
        this.quotientElectoralNumerateur = quotientElectoralNumerateur;
    }


    public void setSiegesPourvoirNombre(
            final int siegesPourvoirNombre)
    {
        this.siegesPourvoirNombre = siegesPourvoirNombre;
    }


    public void setVotantsNombre(
            final int votantsNombre)
    {
        this.votantsNombre = votantsNombre;
    }

    private Date date;

    private int electeursInscritsNombre;

    private int votantsNombre;

    private int bulletinsBlancsOuNulsNombre;

    private int bulletinsValablesNombre;

    private boolean carenceOui;

    private boolean carenceNon;

    private float quorum;

    private boolean quorumAtteintNon;

    private boolean quorumAtteintOui;

    private int listesPresenteesNombre;

    private int siegesPourvoirNombre;

    private float quotientElectoral;

    private float quotientElectoralNumerateur;

    private int quotientElectoralDenominateur;

}
