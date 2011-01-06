package elekto.results.cerfa.model;

import java.util.Date;

/**
 * Informations sur l'élection.
 */
public class ElectionInfos {

    public int getCollegesNombre()
    {
        return this.collegesNombre;
    }


    public int getMandatDureeAnnee()
    {
        return this.mandatDureeAnnee;
    }


    public Date getScrutinPrecedentDate()
    {
        return this.scrutinPrecedentDate;
    }


    public boolean isPartielle()
    {
        return this.partielle;
    }


    public void setCollegesNombre(
            final int collegesNombre)
    {
        this.collegesNombre = collegesNombre;
    }


    public void setMandatDureeAnnee(
            final int mandatDureeAnnee)
    {
        this.mandatDureeAnnee = mandatDureeAnnee;
    }


    public void setPartielle(
            final boolean partielle)
    {
        this.partielle = partielle;
    }


    public void setScrutinPrecedentDate(
            final Date date)
    {
        this.scrutinPrecedentDate = date;
    }

    private int mandatDureeAnnee;

    private int collegesNombre;

    private boolean partielle;

    private Date scrutinPrecedentDate;
}
