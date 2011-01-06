package elekto.results.cerfa.raw;

import static elekto.results.cerfa.raw.BasePageElement.CHECK_CHAR;
import static elekto.results.cerfa.raw.BasePageElement.DATE_FORMAT;
import static elekto.results.cerfa.raw.ElectionPageElement.ELECTION_COLLEGES_NOMBRE;
import static elekto.results.cerfa.raw.ElectionPageElement.ELECTION_MANDAT_DUREE;
import static elekto.results.cerfa.raw.ElectionPageElement.ELECTION_PARTIELLE;
import static elekto.results.cerfa.raw.ElectionPageElement.ELECTION_SCRUTIN_PRECEDENT_DATE;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

import elekto.results.cerfa.model.ElectionInfos;

/**
 * Dessine les informations sur l'élection.
 */
public class ElectionDrawableElement
        implements DrawableElement {

    ElectionDrawableElement(
            final ElectionInfos election)
    {
        this.election = election;
    }


    @Override
    public void draw(
            final PDDocument pdDocument)
        throws IOException
    {
        ELECTION_MANDAT_DUREE.drawText(pdDocument, this.mandatDureeAnnee);
        ELECTION_COLLEGES_NOMBRE.drawText(pdDocument, this.collegesNombre);
        ELECTION_PARTIELLE.drawText(pdDocument, this.partielle);
        ELECTION_SCRUTIN_PRECEDENT_DATE.drawText(pdDocument, this.scrutinPrecedentDate);
    }


    @Override
    public void prepareText()
    {
        this.collegesNombre = this.election.getCollegesNombre() > 0
                ? Integer.toString(this.election.getCollegesNombre())
                : "";
        this.mandatDureeAnnee = this.election.getMandatDureeAnnee() > 0
                ? Integer.toString(this.election.getMandatDureeAnnee())
                : "";
        this.partielle = this.election.isPartielle()
                ? CHECK_CHAR
                : "";
        this.scrutinPrecedentDate = (this.election.getScrutinPrecedentDate() == null)
                ? null
                : DATE_FORMAT.format(this.election.getScrutinPrecedentDate());
    }

    private final ElectionInfos election;

    private String mandatDureeAnnee;

    private String collegesNombre;

    private String partielle;

    private String scrutinPrecedentDate;

}
