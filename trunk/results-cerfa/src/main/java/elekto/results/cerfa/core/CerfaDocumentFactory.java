package elekto.results.cerfa.core;

import static elekto.results.cerfa.raw.CerfaResource.CERFA_COMITE_ENTREPRISE_MEMBRES_TITULAIRES;
import static elekto.results.cerfa.raw.RawCerfaDocument.makeCerfa;

import java.io.IOException;

import elekto.results.cerfa.raw.RawCerfaDocument;
import elekto.results.model.College;
import elekto.results.model.College.CollegeEnum;
import elekto.results.model.College.Denomination;
import elekto.results.model.Election;
import elekto.results.model.Etablissement;
import elekto.results.model.Operation;

public class CerfaDocumentFactory {

    public CerfaDocument create(
            final Operation operation)
        throws IOException
    {
        this.cerfa = makeCerfa(CERFA_COMITE_ENTREPRISE_MEMBRES_TITULAIRES);

        for (final Election election : operation.getElections()) {
            this.setEtablissementInfos(election.getKey().getEtablissement());
            this.setCollegeInfos(election.getKey().getCollege());
            this.setElectionInfos(election);
        }

        return this.cerfa;
    }


    private void setEtablissementInfos(
            final Etablissement etablissement)
    {
        this.cerfa.getEtablissement().setRaisonSociale(etablissement.getRaisonSociale());
        this.cerfa.getEtablissement().setAdresse(etablissement.getAdresse1(), etablissement.getAdresse2());
        this.cerfa.getEtablissement().setCodePostal(etablissement.getCodePostal());
        this.cerfa.getEtablissement().setVille(etablissement.getVille());
        this.cerfa.getEtablissement().setSiret(etablissement.getSiret());
        // TODO this.cerfa.getEtablissement().setAutresSiret(null);
        this.cerfa.getEtablissement().setIdcc(etablissement.getIdcc());
    }


    private void setCollegeInfos(
            final College college)
    {
        switch (college.getDenomination()) {
            case UNIQUE:
                this.cerfa.getCollege().setDenominationUnique(true);
                break;
            case PREMIER:
                this.cerfa.getCollege().setDenominationPremier(true);
                break;
            case DEUXIEME:
                this.cerfa.getCollege().setDenominationDeuxieme(true);
                break;
            case TROISIEME:
                this.cerfa.getCollege().setDenominationTroisieme(true);
                break;
            case AUTRE:
                this.cerfa.getCollege().setDenominationAutre(true);
                break;

            default:
                throw new EnumConstantNotPresentException(Denomination.class, college.getDenomination().name());
        }

        for (final CollegeEnum collegeEnum : college.getCategories()) {
            switch (collegeEnum) {
                case OUVRIERS:
                    this.cerfa.getCollege().setCompositionOuvriers(true);
                    break;
                case EMPLOYES:
                    this.cerfa.getCollege().setCompositionEmployes(true);
                    break;
                case TECHNICIENS:
                    this.cerfa.getCollege().setCompositionTechniciens(true);
                    break;
                case AGENTS_DE_MAITRISE:
                    this.cerfa.getCollege().setCompositionAgentsDeMaitrise(true);
                    break;
                case INGENIEURS:
                    this.cerfa.getCollege().setCompositionIngenieurs(true);
                    break;
                case CADRES:
                    this.cerfa.getCollege().setCompositionCadres(true);
                    break;
                case AUTRES:
                    this.cerfa.getCollege().setCompositionAutres(true, "", "", "");
                    break;

                default:
                    throw new EnumConstantNotPresentException(CollegeEnum.class, collegeEnum.name());
            }
        }
    }


    private void setElectionInfos(
            final Election election)
    {
        this.cerfa.getElection();
    }

    private RawCerfaDocument cerfa;

}
