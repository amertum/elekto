package elekto.results.cerfa.raw;

import static com.google.common.collect.Lists.newArrayList;
import static elekto.results.cerfa.raw.MembresBureauVoteSignaturesPageElement.C00_NOM_PRENOM;
import static elekto.results.cerfa.raw.MembresBureauVoteSignaturesPageElement.C01_ORGANISATIONS;

import java.io.IOException;
import java.util.ListIterator;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.google.common.base.Joiner;

import elekto.results.cerfa.model.MembreBureauVoteInfos;

/**
 * Dessine les informations sur les membres du bureau de vote en vue de leur signature.
 */
class MembresBureauVoteSignaturesDrawableElement
        extends AbstractTableData
        implements DrawableElement {

    MembresBureauVoteSignaturesDrawableElement(
            final Iterable<MembreBureauVoteInfos> membresBureauVoteInfos)
    {
        super(ROW_COUNT, COLUMN_COUNT);

        this.membresBureauVoteInfos = membresBureauVoteInfos;
    }


    @Override
    public void draw(
            final PDDocument pdDocument)
        throws IOException
    {
        for (int rowIndex = 0; rowIndex < this.getRowCount(); rowIndex++) {
            int colIndex = 0;

            C00_NOM_PRENOM.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
            C01_ORGANISATIONS.drawText(pdDocument, this.getValue(rowIndex, colIndex++), 0, rowIndex);
        }
    }


    @Override
    public void prepareText()
    {
        for (final ListIterator<MembreBureauVoteInfos> iter = newArrayList(this.membresBureauVoteInfos).listIterator(); iter.hasNext();) {
            final int index = iter.nextIndex();
            final MembreBureauVoteInfos membreBureauVote = iter.next();

            // nomPenom
            final String joinedNomPrenom = Joiner.on(" ").skipNulls().join(
                    membreBureauVote.getNom(),
                    membreBureauVote.getPrenom());
            this.setValue(index, INDEX_C00_NOM_PRENOM, joinedNomPrenom);

            // organisation
            this.setValue(index, INDEX_C01_ORGANISATIONS, membreBureauVote.getOrganisation());
        }
    }

    static final int ROW_COUNT = 7;

    private static final int COLUMN_COUNT = 2;

    private static final int INDEX_C00_NOM_PRENOM = 0;

    private static final int INDEX_C01_ORGANISATIONS = 1;

    private final Iterable<MembreBureauVoteInfos> membresBureauVoteInfos;

}
