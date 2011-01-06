package elekto.results.cerfa.raw;

import java.io.InputStream;

/**
 * Enumération des modèles de document CERFA disponibles.
 */
public enum CerfaResource {

    CERFA_COMITE_ENTREPRISE_MEMBRES_TITULAIRES("/cerfa/elections-ce-titulaires-cerfa-10114-03.pdf"),
    CERFA_COMITE_ENTREPRISE_MEMBRES_SUPPLEANTS("/cerfa/elections-ce-suppleants-cerfa-10114-03.pdf"),
    CERFA_DELEGUES_DU_PERSONNEL_MEMBRES_TITULAIRES(""),
    CERFA_DELEGUES_DU_PERSONNEL_MEMBRES_SUPPLEANTS(""),
    CERFA_DELEGATION_UNIQUE_DU_PERSONNEL_MEMBRES_TITULAIRES(""),
    CERFA_DELEGATION_UNIQUE_DU_PERSONNEL_MEMBRES_SUPPLEANTS("");

    CerfaResource(
            final String pdfResourceClasspath)
    {
        this.pdfResourceClasspath = pdfResourceClasspath;
    }


    InputStream getPdfResourceAsStream()
    {
        final InputStream inputStream = this.getClass().getResourceAsStream(this.pdfResourceClasspath);

        return inputStream;
    }

    final String pdfResourceClasspath;

}
