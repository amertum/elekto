package elekto.results.cerfa.model;

/**
 * Informations sur les numéros de page.
 */
public class PageNumeroInfos {

    public int getNombreTotalPages()
    {
        return this.nombreTotalPages;
    }


    public int getNumeroPremierePage()
    {
        return this.numeroPremierePage;
    }


    public void setNombreTotalPages(
            final int nombreTotalPages)
    {
        this.nombreTotalPages = nombreTotalPages;
    }


    public void setNumeroPremierePage(
            final int numeroPremierePage)
    {
        this.numeroPremierePage = numeroPremierePage;
    }

    private int numeroPremierePage;

    private int nombreTotalPages;

}
