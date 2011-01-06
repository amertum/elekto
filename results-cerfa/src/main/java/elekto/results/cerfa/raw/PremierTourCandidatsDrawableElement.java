package elekto.results.cerfa.raw;

import static com.google.common.collect.Lists.newArrayList;
import static elekto.results.cerfa.raw.BasePageElement.DECIMAL_FORMAT;
import static elekto.results.cerfa.raw.PremierTourCandidatPageElement.C00_CANDIDAT_NOM_PRENOM;
import static elekto.results.cerfa.raw.PremierTourCandidatPageElement.C01_CANDIDAT_SEXE;
import static elekto.results.cerfa.raw.PremierTourCandidatPageElement.C02_CANDIDAT_SYNDICAT;
import static elekto.results.cerfa.raw.PremierTourCandidatPageElement.C03_LISTE_BULLETINS_VALABLES_NOMBRE;
import static elekto.results.cerfa.raw.PremierTourCandidatPageElement.C04_CANDIDAT_VOIX_NOMBRE;
import static elekto.results.cerfa.raw.PremierTourCandidatPageElement.C05_LISTE_CANDIDATS_VOIX_TOTAL;
import static elekto.results.cerfa.raw.PremierTourCandidatPageElement.C06_LISTE_CANDIDATS_NOMBRE;
import static elekto.results.cerfa.raw.PremierTourCandidatPageElement.C07_LISTE_VOIX_MOYENNE;
import static elekto.results.cerfa.raw.PremierTourCandidatPageElement.C08_LISTE_SIEGES_ATTRIBUES_QUOTIENT;
import static elekto.results.cerfa.raw.PremierTourCandidatPageElement.C09_LISTE_SIEGES_ATTRIBUES_PFM_SIEGE1;
import static elekto.results.cerfa.raw.PremierTourCandidatPageElement.C10_LISTE_SIEGES_ATTRIBUES_PFM_SIEGE2;
import static elekto.results.cerfa.raw.PremierTourCandidatPageElement.C11_LISTE_SIEGES_ATTRIBUES_PFM_SIEGE3;
import static elekto.results.cerfa.raw.PremierTourCandidatPageElement.C12_CANDIDAT_ELU;
import static elekto.results.cerfa.raw.PremierTourCandidatPageElement.C13_LISTE_ELUS_NOMBRE;
import static org.apache.commons.lang.ObjectUtils.defaultIfNull;

import java.io.IOException;
import java.util.ListIterator;

import org.apache.commons.lang.BooleanUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

import com.google.common.base.Joiner;

import elekto.results.cerfa.model.CandidatInfos;

/**
 * Dessine les informations de chaque candidats au premier tour ainsi que leurs résultats.
 */
public class PremierTourCandidatsDrawableElement
        extends AbstractTableData
        implements DrawableElement {

    PremierTourCandidatsDrawableElement(
            final Iterable<CandidatInfos> candidats)
    {
        super(ROW_COUNT, COLUMN_COUNT);

        this.candidats = candidats;
    }


    @Override
    public void draw(
            final PDDocument pdDocument)
        throws IOException
    {
        for (int rowIndex = 0; rowIndex < this.getRowCount(); rowIndex++) {
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


    @Override
    public void prepareText()
    {
        for (final ListIterator<CandidatInfos> iter = newArrayList(this.candidats).listIterator(); iter.hasNext();) {
            final int index = iter.nextIndex();
            final CandidatInfos candidat = iter.next();

            // nomPrenom
            final String joinedNomPrenom = Joiner.on(" ").skipNulls().join(candidat.getNom(), candidat.getPrenom());
            this.setValue(index, INDEX_C00_NOM_PRENOM, joinedNomPrenom);

            // sexe
            final String sexeStr = ((Sexe) defaultIfNull(candidat.getSexe(), Sexe.NONE)).getCode();
            this.setValue(index, INDEX_C01_SEXE, sexeStr);

            // syndicat
            this.setValue(index, INDEX_C02_SYNDICAT, candidat.getSyndicat());

            // candidatVoixNombre
            this.setValue(index, INDEX_C04_CANDIDAT_VOIX_NOMBRE, Integer.toString(candidat.getCandidatVoixNombre()));

            // listeCandidatsVoixTotal
            this.setValue(
                    index,
                    INDEX_C05_LISTE_CANDIDATS_VOIX_TOTAL,
                    Integer.toString(candidat.getListeCandidatsVoixTotal()));

            // listeBulletinsValablesNombre
            this.setValue(
                    index,
                    INDEX_C03_LISTE_BULLETINS_VALABLES_NOMBRE,
                    Integer.toString(candidat.getListeBulletinsValablesNombre()));

            // listeCandidatsNombre
            this.setValue(index, INDEX_C06_LISTE_CANDIDATS_NOMBRE, Integer.toString(candidat.getListeCandidatsNombre()));

            // listeVoixMoyenne
            this.setValue(index, INDEX_C07_LISTE_VOIX_MOYENNE, DECIMAL_FORMAT.format(candidat.getListeVoixMoyenne()));

            // listeSiegesAttribuesQuotient
            this.setValue(
                    index,
                    INDEX_C08_LISTE_SIEGES_ATTRIBUES_QUOTIENT,
                    DECIMAL_FORMAT.format(candidat.getListeSiegesAttribuesQuotient()));

            // listeSiegesAttribuesPlusForteMoyenneSiege1
            this.setValue(
                    index,
                    INDEX_C09_LISTE_SIEGES_ATTRIBUES_PFM_SIEGE_1,
                    DECIMAL_FORMAT.format(candidat.getListeSiegesAttribuesPlusForteMoyenneSiege1()));

            // listeSiegesAttribuesPlusForteMoyenneSiege2
            this.setValue(
                    index,
                    INDEX_C10_LISTE_SIEGES_ATTRIBUES_PFM_SIEGE_2,
                    DECIMAL_FORMAT.format(candidat.getListeSiegesAttribuesPlusForteMoyenneSiege2()));

            // listeSiegesAttribuesPlusForteMoyenneSiege3
            this.setValue(
                    index,
                    INDEX_C11_LISTE_SIEGES_ATTRIBUES_PFM_SIEGE_3,
                    DECIMAL_FORMAT.format(candidat.getListeSiegesAttribuesPlusForteMoyenneSiege3()));

            // elu
            this.setValue(index, INDEX_C12_CANDIDAT_ELU, BooleanUtils.toString(candidat.isElu(), ELU, ""));

            // listeElusNombre
            this.setValue(index, INDEX_C13_LISTE_ELUS_NOMBRE, Integer.toString(candidat.getListeElusNombre()));
        }
    }

    static final int ROW_COUNT = 20;

    private static final int COLUMN_COUNT = 14;

    private static final int INDEX_C00_NOM_PRENOM = 0;

    private static final int INDEX_C01_SEXE = 1;

    private static final int INDEX_C02_SYNDICAT = 2;

    private static final int INDEX_C03_LISTE_BULLETINS_VALABLES_NOMBRE = 3;

    private static final int INDEX_C04_CANDIDAT_VOIX_NOMBRE = 4;

    private static final int INDEX_C05_LISTE_CANDIDATS_VOIX_TOTAL = 5;

    private static final int INDEX_C06_LISTE_CANDIDATS_NOMBRE = 6;

    private static final int INDEX_C07_LISTE_VOIX_MOYENNE = 7;

    private static final int INDEX_C08_LISTE_SIEGES_ATTRIBUES_QUOTIENT = 8;

    private static final int INDEX_C09_LISTE_SIEGES_ATTRIBUES_PFM_SIEGE_1 = 9;

    private static final int INDEX_C10_LISTE_SIEGES_ATTRIBUES_PFM_SIEGE_2 = 10;

    private static final int INDEX_C11_LISTE_SIEGES_ATTRIBUES_PFM_SIEGE_3 = 11;

    private static final int INDEX_C12_CANDIDAT_ELU = 12;

    private static final int INDEX_C13_LISTE_ELUS_NOMBRE = 13;

    private static final String ELU = "Elu";

    private final Iterable<CandidatInfos> candidats;

}
