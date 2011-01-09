package elekto.results.cerfa.core;

import elekto.results.cerfa.model.ElectionModel;
import elekto.results.cerfa.raw.CerfaResource;
import elekto.results.model.Operation;

public class CerfaDocumentFactory {

    public CerfaDocument create(
            final Operation operation)
    {
        final ElectionModel electionModel = new ElectionModel();

        final CerfaDocument cerfa = new CompleteCerfaDocument(
                CerfaResource.CERFA_COMITE_ENTREPRISE_MEMBRES_TITULAIRES,
                electionModel);
        return cerfa;
    }

}
