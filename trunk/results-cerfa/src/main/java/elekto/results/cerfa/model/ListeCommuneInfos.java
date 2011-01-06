package elekto.results.cerfa.model;

import static com.google.common.collect.Iterators.forArray;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.primitives.Floats.asList;

import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

/**
 * Informations d'une liste commune.
 */
public class ListeCommuneInfos {

    public String getNom()
    {
        return this.nom;
    }


    public Iterable<String> getOrganisations()
    {
        return this.organisations;
    }


    public Iterable<Float> getSuffragesRepartitionPourcentage()
    {
        return this.suffragesRepartitionPourcentage;
    }


    public void setNom(
            final String nom)
    {
        this.nom = nom;
    }


    public void setOrganisations(
            final String... organisations)
    {
        this.organisations = newArrayList(Iterators.limit(forArray(organisations), LIMIT_COUNT));
    }


    public void setSuffragesRepartitionPourcentage(
            final float... repartitions)
    {
        this.suffragesRepartitionPourcentage = newArrayList(Iterables.limit(asList(repartitions), LIMIT_COUNT));
    }

    private static final int LIMIT_COUNT = 3;

    private String nom;

    private List<String> organisations = newArrayList();

    private List<Float> suffragesRepartitionPourcentage = newArrayList();

}
