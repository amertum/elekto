package elekto.results.cerfa.core;

import elekto.results.cerfa.raw.CerfaResource;
import elekto.results.model.Operation;

public class CerfaDocumentFactory {

    public CerfaDocument create(
            final Operation operation)
    {
        final CerfaDocument cerfa = new CompleteCerfaDocument(
                CerfaResource.CERFA_COMITE_ENTREPRISE_MEMBRES_TITULAIRES,
                null);
        return cerfa;
    }

}
