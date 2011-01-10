package elekto.results.cerfa.core;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

import elekto.results.cerfa.model.ElectionModel;
import elekto.results.model.Election;
import elekto.results.model.Operation;

class OperationElectionModelFactory {

    public Iterable<ElectionModel> create(
            final Operation operation)
    {
        return Iterables.transform(operation.getElections(), new ElectionModelFunction());
    }

    private static class ElectionModelFunction
            implements Function<Election, ElectionModel> {
        @Override
        public ElectionModel apply(
                final Election input)
        {
            final ElectionModel model = new ElectionModel();

            model.getEtablissement().setRaisonSociale(input.getKey().getEtablissement().getRaisonSociale());
            model.getEtablissement().setAdresse(
                    input.getKey().getEtablissement().getAdresse1(),
                    input.getKey().getEtablissement().getAdresse2());
            model.getEtablissement().setCodePostal(input.getKey().getEtablissement().getCodePostal());
            model.getEtablissement().setVille(input.getKey().getEtablissement().getVille());
            model.getEtablissement().setSiret(input.getKey().getEtablissement().getSiret());
            //model.getEtablissement().setAutresSiret();
            model.getEtablissement().setIdcc(input.getKey().getEtablissement().getIdcc());

            return model;
        }
    }

}
