package elekto.results.cerfa.raw;

import static elekto.results.cerfa.raw.SecondTourResultatsPageElement.RESULTATS_SECOND_TOUR_BULLETINS_BLANCS_OU_NULS;
import static elekto.results.cerfa.raw.SecondTourResultatsPageElement.RESULTATS_SECOND_TOUR_CARENCE_NON;
import static elekto.results.cerfa.raw.SecondTourResultatsPageElement.RESULTATS_SECOND_TOUR_CARENCE_OUI;
import static elekto.results.cerfa.raw.SecondTourResultatsPageElement.RESULTATS_SECOND_TOUR_DATE;
import static elekto.results.cerfa.raw.SecondTourResultatsPageElement.RESULTATS_SECOND_TOUR_ELECTEURS_INSCRITS_NOMBRE;
import static elekto.results.cerfa.raw.SecondTourResultatsPageElement.RESULTATS_SECOND_TOUR_LISTES_PRESENTEES_NOMBRES;
import static elekto.results.cerfa.raw.SecondTourResultatsPageElement.RESULTATS_SECOND_TOUR_QUOTIENT_ELECTORAL;
import static elekto.results.cerfa.raw.SecondTourResultatsPageElement.RESULTATS_SECOND_TOUR_QUOTIENT_ELECTORAL_DENOMINATEUR;
import static elekto.results.cerfa.raw.SecondTourResultatsPageElement.RESULTATS_SECOND_TOUR_QUOTIENT_ELECTORAL_NUMERATEUR;
import static elekto.results.cerfa.raw.SecondTourResultatsPageElement.RESULTATS_SECOND_TOUR_SIEGES_POURVOIR_NOMBRE;
import static elekto.results.cerfa.raw.SecondTourResultatsPageElement.RESULTATS_SECOND_TOUR_SUFFRAGES_VALABLES;
import static elekto.results.cerfa.raw.SecondTourResultatsPageElement.RESULTATS_SECOND_TOUR_VOTANTS_NOMBRE;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

import elekto.results.cerfa.model.TourResultatsInfos;

/**
 * Dessine les informations sur les résultats de l'élection au second tour.
 */
class SecondTourResultatsDrawableElement
        extends PremierTourResultatsDrawableElement
        implements DrawableElement {

    SecondTourResultatsDrawableElement(
            final TourResultatsInfos tourInfos)
    {
        super(tourInfos);
    }


    @Override
    public void draw(
            final PDDocument pdDocument)
        throws IOException
    {
        RESULTATS_SECOND_TOUR_DATE.drawText(pdDocument, this.date);
        RESULTATS_SECOND_TOUR_ELECTEURS_INSCRITS_NOMBRE.drawText(pdDocument, this.electeursInscritsNombre);
        RESULTATS_SECOND_TOUR_VOTANTS_NOMBRE.drawText(pdDocument, this.votantsNombre);
        RESULTATS_SECOND_TOUR_BULLETINS_BLANCS_OU_NULS.drawText(pdDocument, this.bulletinsBlancsOuNulsNombre);
        RESULTATS_SECOND_TOUR_SUFFRAGES_VALABLES.drawText(pdDocument, this.bulletinsValablesNombre);

        RESULTATS_SECOND_TOUR_CARENCE_OUI.drawText(pdDocument, this.carenceOui);
        RESULTATS_SECOND_TOUR_CARENCE_NON.drawText(pdDocument, this.carenceNon);

        RESULTATS_SECOND_TOUR_LISTES_PRESENTEES_NOMBRES.drawText(pdDocument, this.listesPresenteesNombre);
        RESULTATS_SECOND_TOUR_SIEGES_POURVOIR_NOMBRE.drawText(pdDocument, this.siegesPourvoirNombre);
        RESULTATS_SECOND_TOUR_QUOTIENT_ELECTORAL_NUMERATEUR.drawText(pdDocument, this.quotientElectoralNumerateur);
        RESULTATS_SECOND_TOUR_QUOTIENT_ELECTORAL_DENOMINATEUR.drawText(pdDocument, this.quotientElectoralDenominateur);
        RESULTATS_SECOND_TOUR_QUOTIENT_ELECTORAL.drawText(pdDocument, this.quotientElectoral);
    }

}
