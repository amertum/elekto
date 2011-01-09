package elekto.results.cerfa.raw;

import static elekto.results.cerfa.raw.SecondTourCandidatPageElement.C00_CANDIDAT_NOM_PRENOM;
import static elekto.results.cerfa.raw.SecondTourCandidatPageElement.C01_CANDIDAT_SEXE;
import static elekto.results.cerfa.raw.SecondTourCandidatPageElement.C02_CANDIDAT_SYNDICAT;
import static elekto.results.cerfa.raw.SecondTourCandidatPageElement.C03_LISTE_BULLETINS_VALABLES_NOMBRE;
import static elekto.results.cerfa.raw.SecondTourCandidatPageElement.C04_CANDIDAT_VOIX_NOMBRE;
import static elekto.results.cerfa.raw.SecondTourCandidatPageElement.C05_LISTE_CANDIDATS_VOIX_TOTAL;
import static elekto.results.cerfa.raw.SecondTourCandidatPageElement.C06_LISTE_CANDIDATS_NOMBRE;
import static elekto.results.cerfa.raw.SecondTourCandidatPageElement.C07_LISTE_VOIX_MOYENNE;
import static elekto.results.cerfa.raw.SecondTourCandidatPageElement.C08_LISTE_SIEGES_ATTRIBUES_QUOTIENT;
import static elekto.results.cerfa.raw.SecondTourCandidatPageElement.C09_LISTE_SIEGES_ATTRIBUES_PFM_SIEGE1;
import static elekto.results.cerfa.raw.SecondTourCandidatPageElement.C10_LISTE_SIEGES_ATTRIBUES_PFM_SIEGE2;
import static elekto.results.cerfa.raw.SecondTourCandidatPageElement.C11_LISTE_SIEGES_ATTRIBUES_PFM_SIEGE3;
import static elekto.results.cerfa.raw.SecondTourCandidatPageElement.C12_CANDIDAT_ELU;
import static elekto.results.cerfa.raw.SecondTourCandidatPageElement.C13_LISTE_ELUS_NOMBRE;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

import elekto.results.cerfa.model.CandidatInfos;

/**
 * Dessine les informations de chaque candidats au second tour ainsi que leurs résultats.
 */
class SecondTourCandidatsDrawableElement
        extends PremierTourCandidatsDrawableElement
        implements DrawableElement {

    SecondTourCandidatsDrawableElement(
            final Iterable<CandidatInfos> candidats)
    {
        super(candidats);
    }


    @Override
    public void draw(
            final PDDocument pdDocument)
        throws IOException
    {
        for (int rowIndex = 0; rowIndex < ROW_COUNT; rowIndex++) {
            int colIndex = 0;

            C00_CANDIDAT_NOM_PRENOM.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
            C01_CANDIDAT_SEXE.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
            C02_CANDIDAT_SYNDICAT.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
            C03_LISTE_BULLETINS_VALABLES_NOMBRE.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
            C04_CANDIDAT_VOIX_NOMBRE.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
            C05_LISTE_CANDIDATS_VOIX_TOTAL.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
            C06_LISTE_CANDIDATS_NOMBRE.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
            C07_LISTE_VOIX_MOYENNE.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
            C08_LISTE_SIEGES_ATTRIBUES_QUOTIENT.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
            C09_LISTE_SIEGES_ATTRIBUES_PFM_SIEGE1.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
            C10_LISTE_SIEGES_ATTRIBUES_PFM_SIEGE2.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
            C11_LISTE_SIEGES_ATTRIBUES_PFM_SIEGE3.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
            C12_CANDIDAT_ELU.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
            C13_LISTE_ELUS_NOMBRE.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
        }
    }

}
