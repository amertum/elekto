package elekto.results.model;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;

import elekto.util.Builder;

public class OperationBuilder
        implements Builder<Operation> {

    public ElectionBuilder addElection()
    {
        return new ElectionBuilder(this);
    }


    OperationBuilder addElection(
            final Election election)
    {
        this.elections.add(election);

        return this;
    }


    public Operation toOperation()
    {
        final Operation operation = new Operation(this.elections);

        return operation;
    }

    private final Collection<Election> elections = newArrayList();

}
