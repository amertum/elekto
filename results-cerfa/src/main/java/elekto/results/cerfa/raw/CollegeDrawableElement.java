package elekto.results.cerfa.raw;

import static com.google.common.collect.Lists.newArrayList;
import static elekto.results.cerfa.raw.BasePageElement.CHECK_CHAR;
import static elekto.results.cerfa.raw.CollegeCompositionPageElement.COLLEGE_COMPOSITION_AGENTS_DE_MAITRISE;
import static elekto.results.cerfa.raw.CollegeCompositionPageElement.COLLEGE_COMPOSITION_AUTRES;
import static elekto.results.cerfa.raw.CollegeCompositionPageElement.COLLEGE_COMPOSITION_AUTRES_VALUE1;
import static elekto.results.cerfa.raw.CollegeCompositionPageElement.COLLEGE_COMPOSITION_AUTRES_VALUE2;
import static elekto.results.cerfa.raw.CollegeCompositionPageElement.COLLEGE_COMPOSITION_AUTRES_VALUE3;
import static elekto.results.cerfa.raw.CollegeCompositionPageElement.COLLEGE_COMPOSITION_CADRES;
import static elekto.results.cerfa.raw.CollegeCompositionPageElement.COLLEGE_COMPOSITION_EMPLOYES;
import static elekto.results.cerfa.raw.CollegeCompositionPageElement.COLLEGE_COMPOSITION_INGENIEURs;
import static elekto.results.cerfa.raw.CollegeCompositionPageElement.COLLEGE_COMPOSITION_OUVRIERS;
import static elekto.results.cerfa.raw.CollegeCompositionPageElement.COLLEGE_COMPOSITION_TECHNICIENS;
import static elekto.results.cerfa.raw.CollegeDenominationPageElement.COLLEGE_DENOMINATION_AUTRE;
import static elekto.results.cerfa.raw.CollegeDenominationPageElement.COLLEGE_DENOMINATION_DEUXIEME;
import static elekto.results.cerfa.raw.CollegeDenominationPageElement.COLLEGE_DENOMINATION_PREMIER;
import static elekto.results.cerfa.raw.CollegeDenominationPageElement.COLLEGE_DENOMINATION_TROISIEME;
import static elekto.results.cerfa.raw.CollegeDenominationPageElement.COLLEGE_DENOMINATION_UNIQUE;
import static java.util.Collections.nCopies;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;

import elekto.results.cerfa.model.CollegeInfos;

/**
 * Dessine les informations sur la composition et la dénomination du collège.
 */
class CollegeDrawableElement
        implements DrawableElement {

    CollegeDrawableElement(
            final CollegeInfos college)
    {
        this.college = college;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.cerfa.raw.DrawableElement#draw(org.apache.pdfbox.pdmodel.PDDocument)
     */
    @Override
    public void draw(
            final PDDocument pdDocument)
        throws IOException
    {
        COLLEGE_DENOMINATION_UNIQUE.drawText(pdDocument, this.denominationUnique);
        COLLEGE_DENOMINATION_PREMIER.drawText(pdDocument, this.denominationPremier);
        COLLEGE_DENOMINATION_DEUXIEME.drawText(pdDocument, this.denominationDeuxieme);
        COLLEGE_DENOMINATION_TROISIEME.drawText(pdDocument, this.denominationTroisieme);
        COLLEGE_DENOMINATION_AUTRE.drawText(pdDocument, this.denominationAutre);

        COLLEGE_COMPOSITION_OUVRIERS.drawText(pdDocument, this.compositionOuvriers);
        COLLEGE_COMPOSITION_EMPLOYES.drawText(pdDocument, this.compositionEmployes);
        COLLEGE_COMPOSITION_TECHNICIENS.drawText(pdDocument, this.compositionTechniciens);
        COLLEGE_COMPOSITION_AGENTS_DE_MAITRISE.drawText(pdDocument, this.compositionAgentsDeMaitrise);
        COLLEGE_COMPOSITION_INGENIEURs.drawText(pdDocument, this.compositionIngenieurs);
        COLLEGE_COMPOSITION_CADRES.drawText(pdDocument, this.compositionCadres);

        COLLEGE_COMPOSITION_AUTRES.drawText(pdDocument, this.compositionAutres);
        COLLEGE_COMPOSITION_AUTRES_VALUE1.drawText(pdDocument, this.compositionAutresValues.get(0));
        COLLEGE_COMPOSITION_AUTRES_VALUE2.drawText(pdDocument, this.compositionAutresValues.get(1));
        COLLEGE_COMPOSITION_AUTRES_VALUE3.drawText(pdDocument, this.compositionAutresValues.get(2));
    }


    @Override
    public void prepareText()
    {
        this.compositionOuvriers = this.college.isCompositionOuvriers()
                ? CHECK_CHAR
                : "";
        this.compositionEmployes = this.college.isCompositionEmployes()
                ? CHECK_CHAR
                : "";
        this.compositionTechniciens = this.college.isCompositionTechniciens()
                ? CHECK_CHAR
                : "";
        this.compositionAgentsDeMaitrise = this.college.isCompositionAgentsDeMaitrise()
                ? CHECK_CHAR
                : "";
        this.compositionIngenieurs = this.college.isCompositionIngenieurs()
                ? CHECK_CHAR
                : "";
        this.compositionCadres = this.college.isCompositionCadres()
                ? CHECK_CHAR
                : "";
        this.compositionAutres = this.college.isCompositionAutres()
                ? CHECK_CHAR
                : "";
        this.compositionAutresValues = newArrayList(this.college.getCompositionAutresValues());

        this.denominationUnique = this.college.isDenominationUnique()
                ? CHECK_CHAR
                : "";
        this.denominationPremier = this.college.isDenominationPremier()
                ? CHECK_CHAR
                : "";
        this.denominationDeuxieme = this.college.isDenominationDeuxieme()
                ? CHECK_CHAR
                : "";
        this.denominationTroisieme = this.college.isDenominationTroisieme()
                ? CHECK_CHAR
                : "";
        this.denominationAutre = this.college.isDenominationAutre()
                ? CHECK_CHAR
                : "";
    }

    private final CollegeInfos college;

    private String denominationUnique;

    private String denominationPremier;

    private String denominationDeuxieme;

    private String denominationTroisieme;

    private String denominationAutre;

    private String compositionOuvriers;

    private String compositionEmployes;

    private String compositionTechniciens;

    private String compositionAgentsDeMaitrise;

    private String compositionIngenieurs;

    private String compositionCadres;

    private String compositionAutres;

    private List<String> compositionAutresValues = nCopies(3, "");

}
