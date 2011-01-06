package elekto.results.cerfa.model;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;

public class ElectionModel {

    public ElectionModel()
    {
        this.etablissement = new EtablissementInfos();
        this.election = new ElectionInfos();
        this.college = new CollegeInfos();
        this.premierTourResultatsInfos = new TourResultatsInfos();
        this.premierTourCandidatsInfos = newArrayList();
        this.secondTourResultatsInfos = new TourResultatsInfos();
        this.secondTourCandidatsInfos = newArrayList();
        this.listesCommunes = newArrayList();
        this.membresBureauVoteInfos = newArrayList();
        this.contactEntrepriseInfos = new ContactEntrepriseInfos();
        this.pageNumeroInfos = new PageNumeroInfos();
    }


    public void addListeCommune(
            final ListeCommuneInfos listeCommune)
    {
        this.listesCommunes.add(listeCommune);
    }


    public void addMembreBureauVote(
            final MembreBureauVoteInfos membreBureauVote)
    {
        this.membresBureauVoteInfos.add(membreBureauVote);
    }


    public void addPremierTourCandidat(
            final CandidatInfos candidat)
    {
        this.premierTourCandidatsInfos.add(candidat);
    }


    public void addSecondTourCandidat(
            final CandidatInfos candidat)
    {
        this.secondTourCandidatsInfos.add(candidat);
    }


    public CollegeInfos getCollege()
    {
        return this.college;
    }


    public ContactEntrepriseInfos getContactEntreprise()
    {
        return this.contactEntrepriseInfos;
    }


    public ElectionInfos getElection()
    {
        return this.election;
    }


    public EtablissementInfos getEtablissement()
    {
        return this.etablissement;
    }


    public Iterable<ListeCommuneInfos> getListesCommunes()
    {
        return this.listesCommunes;
    }


    public Iterable<MembreBureauVoteInfos> getMembresBureauVoteSignatures()
    {
        return this.membresBureauVoteInfos;
    }


    public PageNumeroInfos getPageNumeros()
    {
        return this.pageNumeroInfos;
    }


    public Iterable<CandidatInfos> getPremierTourCandidats()
    {
        return this.premierTourCandidatsInfos;
    }


    public TourResultatsInfos getPremierTourResultats()
    {
        return this.premierTourResultatsInfos;
    }


    public Iterable<CandidatInfos> getSecondTourCandidats()
    {
        return this.secondTourCandidatsInfos;
    }


    public TourResultatsInfos getSecondTourResultats()
    {
        return this.secondTourResultatsInfos;
    }

    private final EtablissementInfos etablissement;

    private final ElectionInfos election;

    private final CollegeInfos college;

    private final TourResultatsInfos premierTourResultatsInfos;

    private final Collection<CandidatInfos> premierTourCandidatsInfos;

    private final TourResultatsInfos secondTourResultatsInfos;

    private final Collection<CandidatInfos> secondTourCandidatsInfos;

    private final Collection<ListeCommuneInfos> listesCommunes;

    private final Collection<MembreBureauVoteInfos> membresBureauVoteInfos;

    private final ContactEntrepriseInfos contactEntrepriseInfos;

    private final PageNumeroInfos pageNumeroInfos;

}
