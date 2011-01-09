package elekto.results.cerfa.raw;

import static elekto.results.cerfa.raw.BasePageElement.CHECK_CHAR;
import static elekto.results.cerfa.raw.BasePageElement.DATE_FORMAT;
import static elekto.results.cerfa.raw.BasePageElement.DECIMAL_FORMAT;
import static elekto.results.cerfa.raw.BasePageElement.INTEGER_FORMAT;
import static elekto.results.cerfa.raw.PremierTourResultatsPageElement.RESULTATS_PREMIER_TOUR_BULLETINS_BLANCS_OU_NULS;
import static elekto.results.cerfa.raw.PremierTourResultatsPageElement.RESULTATS_PREMIER_TOUR_CARENCE_NON;
import static elekto.results.cerfa.raw.PremierTourResultatsPageElement.RESULTATS_PREMIER_TOUR_CARENCE_OUI;
import static elekto.results.cerfa.raw.PremierTourResultatsPageElement.RESULTATS_PREMIER_TOUR_DATE;
import static elekto.results.cerfa.raw.PremierTourResultatsPageElement.RESULTATS_PREMIER_TOUR_ELECTEURS_INSCRITS_NOMBRE;
import static elekto.results.cerfa.raw.PremierTourResultatsPageElement.RESULTATS_PREMIER_TOUR_LISTES_PRESENTEES_NOMBRES;
import static elekto.results.cerfa.raw.PremierTourResultatsPageElement.RESULTATS_PREMIER_TOUR_QUORUM;
import static elekto.results.cerfa.raw.PremierTourResultatsPageElement.RESULTATS_PREMIER_TOUR_QUORUM_ATTEINT_NON;
import static elekto.results.cerfa.raw.PremierTourResultatsPageElement.RESULTATS_PREMIER_TOUR_QUORUM_ATTEINT_OUI;
import static elekto.results.cerfa.raw.PremierTourResultatsPageElement.RESULTATS_PREMIER_TOUR_QUORUM_NUMERATEUR;
import static elekto.results.cerfa.raw.PremierTourResultatsPageElement.RESULTATS_PREMIER_TOUR_QUOTIENT_ELECTORAL;
import static elekto.results.cerfa.raw.PremierTourResultatsPageElement.RESULTATS_PREMIER_TOUR_QUOTIENT_ELECTORAL_DENOMINATEUR;
import static elekto.results.cerfa.raw.PremierTourResultatsPageElement.RESULTATS_PREMIER_TOUR_QUOTIENT_ELECTORAL_NUMERATEUR;
import static elekto.results.cerfa.raw.PremierTourResultatsPageElement.RESULTATS_PREMIER_TOUR_SIEGES_POURVOIR_NOMBRE;
import static elekto.results.cerfa.raw.PremierTourResultatsPageElement.RESULTATS_PREMIER_TOUR_SUFFRAGES_VALABLES;
import static elekto.results.cerfa.raw.PremierTourResultatsPageElement.RESULTATS_PREMIER_TOUR_VOTANTS_NOMBRE;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

import elekto.results.cerfa.model.TourResultatsInfos;

/**
 * Dessine les informations sur les résultats de l'élection au premier tour.
 */
class PremierTourResultatsDrawableElement
        implements DrawableElement {

    PremierTourResultatsDrawableElement(
            final TourResultatsInfos tourInfos)
    {
        this.tourInfos = tourInfos;
    }


    @Override
    public void draw(
            final PDDocument pdDocument)
        throws IOException
    {
        RESULTATS_PREMIER_TOUR_DATE.drawText(pdDocument, this.date);
        RESULTATS_PREMIER_TOUR_ELECTEURS_INSCRITS_NOMBRE.drawText(pdDocument, this.electeursInscritsNombre);
        RESULTATS_PREMIER_TOUR_VOTANTS_NOMBRE.drawText(pdDocument, this.votantsNombre);
        RESULTATS_PREMIER_TOUR_BULLETINS_BLANCS_OU_NULS.drawText(pdDocument, this.bulletinsBlancsOuNulsNombre);
        RESULTATS_PREMIER_TOUR_SUFFRAGES_VALABLES.drawText(pdDocument, this.bulletinsValablesNombre);

        RESULTATS_PREMIER_TOUR_CARENCE_OUI.drawText(pdDocument, this.carenceOui);
        RESULTATS_PREMIER_TOUR_CARENCE_NON.drawText(pdDocument, this.carenceNon);
        RESULTATS_PREMIER_TOUR_QUORUM_NUMERATEUR.drawText(pdDocument, this.electeursInscritsNombre);
        RESULTATS_PREMIER_TOUR_QUORUM.drawText(pdDocument, this.quorum);
        RESULTATS_PREMIER_TOUR_QUORUM_ATTEINT_OUI.drawText(pdDocument, this.quorumAtteintOui);
        RESULTATS_PREMIER_TOUR_QUORUM_ATTEINT_NON.drawText(pdDocument, this.quorumAtteintNon);

        RESULTATS_PREMIER_TOUR_LISTES_PRESENTEES_NOMBRES.drawText(pdDocument, this.listesPresenteesNombre);
        RESULTATS_PREMIER_TOUR_SIEGES_POURVOIR_NOMBRE.drawText(pdDocument, this.siegesPourvoirNombre);
        RESULTATS_PREMIER_TOUR_QUOTIENT_ELECTORAL_NUMERATEUR.drawText(pdDocument, this.quotientElectoralNumerateur);
        RESULTATS_PREMIER_TOUR_QUOTIENT_ELECTORAL_DENOMINATEUR.drawText(pdDocument, this.quotientElectoralDenominateur);
        RESULTATS_PREMIER_TOUR_QUOTIENT_ELECTORAL.drawText(pdDocument, this.quotientElectoral);
    }


    @Override
    public void prepareText()
    {
        this.date = (this.tourInfos.getDate() == null)
                ? ""
                : DATE_FORMAT.format(this.tourInfos.getDate());
        this.electeursInscritsNombre = this.tourInfos.getElecteursInscritsNombre() > 0
                ? INTEGER_FORMAT.format(this.tourInfos.getElecteursInscritsNombre())
                : "";
        this.votantsNombre = this.tourInfos.getVotantsNombre() > 0
                ? INTEGER_FORMAT.format(this.tourInfos.getVotantsNombre())
                : "";
        this.bulletinsBlancsOuNulsNombre = this.tourInfos.getBulletinsBlancsOuNulsNombre() > 0
                ? INTEGER_FORMAT.format(this.tourInfos.getBulletinsBlancsOuNulsNombre())
                : "";
        this.bulletinsValablesNombre = this.tourInfos.getBulletinsValablesNombre() > 0
                ? INTEGER_FORMAT.format(this.tourInfos.getBulletinsValablesNombre())
                : "";

        this.carenceOui = this.tourInfos.isCarenceOui()
                ? CHECK_CHAR
                : "";
        this.carenceNon = this.tourInfos.isCarenceNon()
                ? CHECK_CHAR
                : "";
        this.quorum = this.tourInfos.getQuorum() > 0
                ? DECIMAL_FORMAT.format(this.tourInfos.getQuorum())
                : "";
        this.quorumAtteintNon = this.tourInfos.isQuorumAtteintNon()
                ? CHECK_CHAR
                : "";
        this.quorumAtteintOui = this.tourInfos.isQuorumAtteintOui()
                ? CHECK_CHAR
                : "";

        this.listesPresenteesNombre = this.tourInfos.getListesPresenteesNombre() > 0
                ? INTEGER_FORMAT.format(this.tourInfos.getListesPresenteesNombre())
                : "";
        this.siegesPourvoirNombre = this.tourInfos.getSiegesPourvoirNombre() > 0
                ? INTEGER_FORMAT.format(this.tourInfos.getSiegesPourvoirNombre())
                : "";
        this.quotientElectoralNumerateur = this.tourInfos.getQuotientElectoralNumerateur() > 0
                ? DECIMAL_FORMAT.format(this.tourInfos.getQuotientElectoralNumerateur())
                : "";
        this.quotientElectoralDenominateur = this.tourInfos.getQuotientElectoralDenominateur() > 0
                ? INTEGER_FORMAT.format(this.tourInfos.getQuotientElectoralDenominateur())
                : "";
        this.quotientElectoral = this.tourInfos.getQuotientElectoral() > 0
                ? DECIMAL_FORMAT.format(this.tourInfos.getQuotientElectoral())
                : "";
    }

    private final TourResultatsInfos tourInfos;

    protected String date;

    protected String electeursInscritsNombre;

    protected String votantsNombre;

    protected String bulletinsBlancsOuNulsNombre;

    protected String bulletinsValablesNombre;

    protected String carenceOui;

    protected String carenceNon;

    protected String quorum;

    protected String quorumAtteintNon;

    protected String quorumAtteintOui;

    protected String listesPresenteesNombre;

    protected String siegesPourvoirNombre;

    protected String quotientElectoral;

    protected String quotientElectoralNumerateur;

    protected String quotientElectoralDenominateur;

}
