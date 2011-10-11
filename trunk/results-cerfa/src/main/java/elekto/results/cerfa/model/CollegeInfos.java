package elekto.results.cerfa.model;

import static java.util.Collections.nCopies;
import static org.apache.commons.lang.StringUtils.defaultString;

import java.util.ArrayList;
import java.util.List;

/**
 * Informations sur la composition et la dénomination du collège.
 */
public class CollegeInfos {

    public Iterable<String> getCompositionAutresValues()
    {
        return this.compositionAutresValues;
    }


    public boolean isCompositionAgentsDeMaitrise()
    {
        return this.compositionAgentsDeMaitrise;
    }


    public boolean isCompositionAutres()
    {
        return this.compositionAutres;
    }


    public boolean isCompositionCadres()
    {
        return this.compositionCadres;
    }


    public boolean isCompositionEmployes()
    {
        return this.compositionEmployes;
    }


    public boolean isCompositionIngenieurs()
    {
        return this.compositionIngenieurs;
    }


    public boolean isCompositionOuvriers()
    {
        return this.compositionOuvriers;
    }


    public boolean isCompositionTechniciens()
    {
        return this.compositionTechniciens;
    }


    public boolean isDenominationAutre()
    {
        return this.denominationAutre;
    }


    public boolean isDenominationDeuxieme()
    {
        return this.denominationDeuxieme;
    }


    public boolean isDenominationPremier()
    {
        return this.denominationPremier;
    }


    public boolean isDenominationTroisieme()
    {
        return this.denominationTroisieme;
    }


    public boolean isDenominationUnique()
    {
        return this.denominationUnique;
    }


    public void setCompositionAutres(
            final boolean autres,
            final String value1,
            final String value2,
            final String value3)
    {
        this.compositionAutres = autres;

        this.compositionAutresValues = new ArrayList<String>();
        this.compositionAutresValues.add(defaultString(value1));
        this.compositionAutresValues.add(defaultString(value2));
        this.compositionAutresValues.add(defaultString(value3));
    }


    public void setCompositionAgentsDeMaitrise(
            final boolean agentsDeMaitrise)
    {
        this.compositionAgentsDeMaitrise = agentsDeMaitrise;
    }


    public void setCompositionCadres(
            final boolean cadres)
    {
        this.compositionCadres = cadres;
    }


    public void setCompositionEmployes(
            final boolean employes)
    {
        this.compositionEmployes = employes;
    }


    public void setCompositionIngenieurs(
            final boolean ingenieurs)
    {
        this.compositionIngenieurs = ingenieurs;
    }


    public void setCompositionOuvriers(
            final boolean ouvriers)
    {
        this.compositionOuvriers = ouvriers;
    }


    public void setCompositionTechniciens(
            final boolean techniciens)
    {
        this.compositionTechniciens = techniciens;
    }


    public void setDenominationAutre(
            final boolean autre)
    {
        this.denominationAutre = autre;
    }


    public void setDenominationDeuxieme(
            final boolean deuxieme)
    {
        this.denominationDeuxieme = deuxieme;
    }


    public void setDenominationPremier(
            final boolean premier)
    {
        this.denominationPremier = premier;
    }


    public void setDenominationTroisieme(
            final boolean troisieme)
    {
        this.denominationTroisieme = troisieme;
    }


    public void setDenominationUnique(
            final boolean collegeUnique)
    {
        this.denominationUnique = collegeUnique;
    }

    private static final int COMPOSITION_AUTRES_VALUES_COUNT = 3;

    private boolean denominationUnique;

    private boolean denominationPremier;

    private boolean denominationDeuxieme;

    private boolean denominationTroisieme;

    private boolean denominationAutre;

    private boolean compositionOuvriers;

    private boolean compositionEmployes;

    private boolean compositionTechniciens;

    private boolean compositionAgentsDeMaitrise;

    private boolean compositionIngenieurs;

    private boolean compositionCadres;

    private boolean compositionAutres;

    private List<String> compositionAutresValues = nCopies(COMPOSITION_AUTRES_VALUES_COUNT, "");

}
