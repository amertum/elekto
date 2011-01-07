package elekto.results.dao.xls;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import elekto.results.model.Candidat;
import elekto.results.model.CandidatBuilder;
import elekto.results.model.Election;
import elekto.results.model.ElectionBuilder;
import elekto.results.model.Liste;
import elekto.results.model.ListeBuilder;
import elekto.results.model.Operation;
import elekto.results.model.OperationBuilder;

/**
 * Load an {@link Operation} from an excel file {@link InputStream}.
 */
public class OperationLoader {
    
    /**
     * Return the {@link Operation} read from the provided excel file {@link InputStream}. The
     * stream will be closed.
     * 
     * @param excelFileInputStream
     *            an excel file {@link InputStream}.
     * 
     * @return the {@link Operation} read from the provided excel file {@link InputStream}.
     * 
     * @throws IOException
     *             if unable to read the excel file {@link InputStream}.
     */
    public Operation loadOperation(
            final InputStream excelFileInputStream)
        throws IOException
    {
        final HSSFWorkbook workbook = new HSSFWorkbook(excelFileInputStream);
        return this.loadOperation(workbook);
    }
    

    /**
     * Return the {@link Operation} from the provided excel {@link HSSFWorkbook}.
     * 
     * @param resultsWorkbook
     *            elections results {@link HSSFWorkbook}.
     * 
     * @return the {@link Operation} from the provided excel {@link HSSFWorkbook}.
     */
    public Operation loadOperation(
            final HSSFWorkbook resultsWorkbook)
    {
        final HSSFSheet etablissements = resultsWorkbook.getSheetAt(0);
        final HSSFSheet candidats = resultsWorkbook.getSheetAt(1);
        
        this.operationBuilder = new OperationBuilder();
        this.loadCandidats(candidats, etablissements);
        
        final Operation operation = this.operationBuilder.toOperation();
        return operation;
    }
    

    private void loadCandidats(
            final HSSFSheet candidats,
            final HSSFSheet etablissements)
    {
        final EtablissementsSheet etablissementsSheet = new EtablissementsSheet(etablissements);
        etablissementsSheet.load();
        
        final TrailingCandidatRowValueGetter rowValues = new TrailingCandidatRowValueGetter();
        
        ElectionBuilder electionBuilder = this.operationBuilder.addElection();
        boolean electionInitialized = false;
        ListeBuilder listeBuilder = electionBuilder.addListe();
        boolean listeInitialized = false;
        CandidatBuilder candidatBuilder = listeBuilder.addCandidat();
        boolean candidatInitialized = false;
        
        // TODO stop on first empty candidat.nom
        final int lastRowIndex = candidats.getLastRowNum();
        for (int i = 2; (i <= lastRowIndex); i++) {
            final HSSFRow row = candidats.getRow(i);
            this.logRow(row);
            
            rowValues.updateRow(row);
            
            if (electionInitialized
                    && this.isRowNewElection(electionBuilder, rowValues, etablissementsSheet)) {
                electionBuilder = candidatBuilder.endCandidat().endListe().endElection().addElection();
                listeBuilder = electionBuilder.addListe();
                candidatBuilder = listeBuilder.addCandidat();
                
                electionInitialized = false;
                listeInitialized = false;
                candidatInitialized = false;
            }
            else if (listeInitialized && this.isRowNewListe(listeBuilder, rowValues)) {
                listeBuilder = candidatBuilder.endCandidat().endListe().addListe();
                candidatBuilder = listeBuilder.addCandidat();
                
                listeInitialized = false;
                candidatInitialized = false;
            }
            else if (candidatInitialized && this.isRowNewCandidat(candidatBuilder, rowValues)) {
                candidatBuilder = candidatBuilder.endCandidat().addCandidat();
                
                candidatInitialized = false;
            }
            
            if (!electionInitialized) {
                new RowElectionBuilder(rowValues, etablissementsSheet).build(electionBuilder);
                electionInitialized = true;
            }
            if (!listeInitialized) {
                new RowListeBuilder(rowValues).build(listeBuilder);
                listeInitialized = true;
            }
            if (!candidatInitialized) {
                new RowCandidatBuilder(rowValues).build(candidatBuilder);
                candidatInitialized = true;
            }
        }
        
        candidatBuilder.endCandidat().endListe().endElection();
    }
    

    private boolean isRowNewElection(
            final ElectionBuilder electionBuilder,
            final CandidatRowValueGetter row,
            final EtablissementsSheet etablissementsSheet)
    {
        final Election rowElection = new RowElectionBuilder(row, etablissementsSheet).build(
                new ElectionBuilder()).toElection();
        final Election currentElection = electionBuilder.toElection();
        
        final boolean newElection = !currentElection.equals(rowElection);
        this.logger.debug("isRowNewElection=" + newElection + " => currentElection : "
                + currentElection + "; rowElection : " + rowElection);
        return newElection;
    }
    

    private boolean isRowNewListe(
            final ListeBuilder listeBuilder,
            final CandidatRowValueGetter row)
    {
        final Liste rowListe = new RowListeBuilder(row).build(new ListeBuilder()).toListe();
        final Liste currentListe = listeBuilder.toListe();
        
        final boolean newListe = !currentListe.equals(rowListe);
        this.logger.debug("isRowNewListe=" + newListe + " => currentListe : " + currentListe
                + "; rowListe : " + rowListe);
        return newListe;
    }
    

    private boolean isRowNewCandidat(
            final CandidatBuilder candidatBuilder,
            final CandidatRowValueGetter row)
    {
        final Candidat rowCandidat = new RowCandidatBuilder(row).build(new CandidatBuilder()).toCandidat();
        final Candidat currentCandidat = candidatBuilder.toCandidat();
        this.logger.debug("isRowNewCandidat=" + true + " => currentCandidat : " + currentCandidat
                + "; rowCandidat : " + rowCandidat);
        return true;
    }
    

    private void logRow(
            final HSSFRow row)
    {
        if (this.logger.isDebugEnabled()) {
            String rowStr = "";
            
            for (int i = 0; i < row.getLastCellNum(); i++) {
                final HSSFCell cell = row.getCell(i);
                
                if (cell == null) {
                    rowStr += "~";
                }
                else {
                    rowStr += cell.toString() + "(" + cell.getCellType() + ")";
                }
                
                rowStr += ";";
            }
            
            this.logger.debug(rowStr);
        }
    }
    

    private final Logger logger = Logger.getLogger(this.getClass());
    
    private OperationBuilder operationBuilder;
    

    static class RowElectionBuilder {
        
        RowElectionBuilder(
                final CandidatRowValueGetter row,
                final EtablissementsSheet etablissementsSheet)
        {
            if (row == null) {
                throw new IllegalArgumentException("row must not be null");
            }
            if (etablissementsSheet == null) {
                throw new IllegalArgumentException("etablissementsSheet must not be null");
            }
            
            this.row = row;
            this.etablissementsSheet = etablissementsSheet;
        }
        

        public ElectionBuilder build(
                final ElectionBuilder builder)
        {
            // election key
            builder.tour(this.row.getTourIndex(), this.row.getTourDate());
            builder.type(this.row.getElectionType());
            builder.categorie(this.row.getElectionCategorie());
            final EtablissementRowValueGetter etablissementRow = this.etablissementsSheet.find(this.row.getEtablissement());
            builder.etablissement(
                    etablissementRow.getRaisonSociale(),
                    etablissementRow.getAdresse1(),
                    etablissementRow.getAdresse2(),
                    etablissementRow.getCodePostal(),
                    etablissementRow.getVille(),
                    etablissementRow.getSiret(),
                    etablissementRow.getIdcc());
            builder.college(this.row.getColleges());
            
            // election infos
            builder.scrutinPrecedentDate(this.row.getScrutinPrecedentDate());
            if (this.row.isPartielle()) {
                builder.partielle();
            }
            builder.electeursInscritsCount(this.row.getElecteursInscritsCount());
            builder.electeursVotantsCount(this.row.getElecteursVotantsCount());
            builder.bulletinsBlancsOuNulsCount(this.row.getBulletinsBlancsOuNulsCount());
            builder.siegesCount(this.row.getSiegesCount());
            
            return builder;
        }
        

        private final CandidatRowValueGetter row;
        
        private final EtablissementsSheet etablissementsSheet;
        
    }
    
    static class RowListeBuilder {
        
        RowListeBuilder(
                final CandidatRowValueGetter row)
        {
            if (row == null) {
                throw new IllegalArgumentException("row must not be null");
            }
            
            this.row = row;
        }
        

        public ListeBuilder build(
                final ListeBuilder builder)
        {
            builder.nom(this.row.getListeNom());
            builder.bulletinsValablesCount(this.row.getListeBulletinsValablesCount());
            
            return builder;
        }
        

        private final CandidatRowValueGetter row;
        
    }
    
    static class RowCandidatBuilder {
        
        RowCandidatBuilder(
                final CandidatRowValueGetter row)
        {
            if (row == null) {
                throw new IllegalArgumentException("row must not be null");
            }
            
            this.row = row;
        }
        

        public CandidatBuilder build(
                final CandidatBuilder builder)
        {
            builder.nom(this.row.getCandidatNom());
            builder.prenom(this.row.getCandidatPrenom());
            builder.dateNaissance(this.row.getCandidatNaissanceDate());
            builder.sexe(this.row.getCandidatSexe());
            builder.voixCount(this.row.getCandidatVoixCount());
            
            return builder;
        }
        

        private final CandidatRowValueGetter row;
        
    }
    
}
