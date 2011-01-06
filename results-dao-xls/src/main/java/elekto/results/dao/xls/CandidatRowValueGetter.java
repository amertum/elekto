package elekto.results.dao.xls;

import java.util.Date;

import elekto.results.model.Categorie;
import elekto.results.model.Sexe;
import elekto.results.model.Type;

interface CandidatRowValueGetter {

    int getTourIndex();


    Date getTourDate();


    Type getElectionType();


    Categorie getElectionCategorie();


    String getEtablissement();


    Iterable<String> getColleges();


    Date getScrutinPrecedentDate();


    boolean isPartielle();


    int getElecteursInscritsCount();


    int getElecteursVotantsCount();


    int getBulletinsBlancsOuNulsCount();


    int getSiegesCount();


    String getListeNom();


    int getListeBulletinsValablesCount();


    String getCandidatNom();


    String getCandidatPrenom();


    Date getCandidatNaissanceDate();


    Sexe getCandidatSexe();


    int getCandidatVoixCount();

}
