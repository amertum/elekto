package elekto.results.cerfa.model;

import static com.google.common.collect.Iterables.limit;
import static java.util.Collections.nCopies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Les informations sur l'établissement.
 */
public class EtablissementInfos {

    public Iterable<String> getAdresses()
    {
        return this.adresses;
    }


    public Iterable<String> getAutresSiret()
    {
        return this.autresSiret;
    }


    public String getCodePostal()
    {
        return this.codePostal;
    }


    public String getIdcc()
    {
        return this.idcc;
    }


    public String getRaisonSociale()
    {
        return this.raisonSociale;
    }


    public String getSiret()
    {
        return this.siret;
    }


    public String getVille()
    {
        return this.ville;
    }


    public void setAdresse(
            final String adresse1,
            final String adresse2)
    {
        this.adresses = new ArrayList<String>();

        this.adresses.add(adresse1);
        this.adresses.add(adresse2);
    }


    public void setAutresSiret(
            final Iterable<String> autres)
    {
        this.autresSiret = new ArrayList<String>();

        final Iterable<String> autresIter = (autres == null)
                ? Collections.<String> emptyList()
                : limit(autres, AUTRES_SIRET_COUNT);
        for (final String autre : autresIter) {
            this.autresSiret.add(autre);
        }
    }


    public void setCodePostal(
            final String codePostal)
    {
        this.codePostal = codePostal;
    }


    public void setIdcc(
            final String idcc)
    {
        this.idcc = idcc;
    }


    public void setRaisonSociale(
            final String raisonSociale)

    {
        this.raisonSociale = raisonSociale;
    }


    public void setSiret(
            final String siret)
    {
        this.siret = siret;
    }


    public void setVille(
            final String ville)
    {
        this.ville = ville;
    }

    private static final int AUTRES_SIRET_COUNT = 11;

    private String raisonSociale = "";

    private List<String> adresses = nCopies(2, "");

    private String codePostal = "";

    private String ville = "";

    private String siret = "";

    private List<String> autresSiret = Collections.<String> emptyList();

    private String idcc = "";

}
