package elekto.results.cerfa.raw;

import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;
import static elekto.results.cerfa.raw.BasePageElement.PERCENT_FORMAT;
import static elekto.results.cerfa.raw.ListesCommunesPageElement.C00_LISTE_NOM;
import static elekto.results.cerfa.raw.ListesCommunesPageElement.C01_ORGANISATIONS;
import static elekto.results.cerfa.raw.ListesCommunesPageElement.C02_SUFFRAGES_REPARTITION;

import java.io.IOException;
import java.util.ListIterator;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.google.common.base.Function;
import com.google.common.base.Joiner;

import elekto.results.cerfa.model.ListeCommuneInfos;

/**
 * Dessine les informations sur les listes communes.
 */
class ListesCommunesDrawableElement
        extends AbstractTableData
        implements DrawableElement {

    ListesCommunesDrawableElement(
            final Iterable<ListeCommuneInfos> listesCommunes)
    {
        super(ROW_COUNT, COLUMN_COUNT);

        this.listesCommunes = listesCommunes;
    }


    @Override
    public void draw(
            final PDDocument pdDocument)
        throws IOException
    {
        for (int rowIndex = 0; rowIndex < this.getRowCount(); rowIndex++) {
            int colIndex = 0;

            C00_LISTE_NOM.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
            C01_ORGANISATIONS.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
            C02_SUFFRAGES_REPARTITION.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
        }
    }


    @Override
    public void prepareText()
    {
        for (final ListIterator<ListeCommuneInfos> iter = newArrayList(this.listesCommunes).listIterator(); iter.hasNext();) {
            final int index = iter.nextIndex();
            final ListeCommuneInfos listeCommune = iter.next();

            // nom
            this.setValue(index, INDEX_C00_LISTE_NOM, listeCommune.getNom());

            // organisations
            final String joinedOrganisations = Joiner.on(" ; ").join(listeCommune.getOrganisations());
            this.setValue(index, INDEX_C01_ORGANISATIONS, joinedOrganisations);

            // repartitions
            final Iterable<String> formattedRepartitions = transform(
                    listeCommune.getSuffragesRepartitionPourcentage(),
                    new Function<Float, String>() {

                        @Override
                        public String apply(
                                final Float input)
                        {
                            return PERCENT_FORMAT.format(input);
                        }
                    });
            final String joinedRepartitions = Joiner.on(" ; ").join(formattedRepartitions);
            this.setValue(index, INDEX_C02_SUFFRAGES_REPARTITION, joinedRepartitions);
        }
    }

    static final int ROW_COUNT = 7;

    private static final int COLUMN_COUNT = 3;

    private static final int INDEX_C00_LISTE_NOM = 0;

    private static final int INDEX_C01_ORGANISATIONS = 1;

    private static final int INDEX_C02_SUFFRAGES_REPARTITION = 2;

    private final Iterable<ListeCommuneInfos> listesCommunes;

}
