package elekto.results.dao.xls;

import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;

import elekto.results.model.Categorie;
import elekto.results.model.Sexe;
import elekto.results.model.Type;

class TrailingCandidatRowValueGetter
        implements CandidatRowValueGetter {

    public void updateRow(
            final HSSFRow row)
    {
        // tour index
        if (row.getCell(0) != null) {
            final HSSFCell cell = row.getCell(0);
            if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                this.tourIndex = (int) cell.getNumericCellValue();
            }
            else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
                try {
                    this.tourIndex = Integer.parseInt(cell.getStringCellValue());
                }
                catch (final NumberFormatException e) {
                    // keep previous value

                    // TODO keep all parsing errors to retrieve them later for ui
                    this.log.info(String.format("Unable to parse tourIndex int '%s'", cell.getStringCellValue()), e);
                }
            }
        }

        // tour date
        if (row.getCell(1) != null) {
            final String cellValue = row.getCell(1).getStringCellValue();
            try {
                if (isNotEmpty(cellValue)) {
                    this.tourDate = DateUtils.parseDateStrictly(cellValue, TOUR_DATE_PATTERNS);
                }
            }
            catch (final ParseException e) {
                // keep previous value

                this.log.info(String.format(
                        "Unable to parse tourDate '%s' with patterns : %s",
                        cellValue,
                        Joiner.on(", ").join(TOUR_DATE_PATTERNS)), e);
            }
        }

        if (row.getCell(2) != null) {
            this.type = Type.forCode(row.getCell(2).getStringCellValue());
        }
        if (row.getCell(3) != null) {
            this.categorie = Categorie.forCode(row.getCell(3).getStringCellValue());
        }
        if (row.getCell(4) != null) {
            this.etablissement = row.getCell(4).getStringCellValue();
        }
        if ((row.getCell(5) != null) && !row.getCell(5).getStringCellValue().isEmpty()) {
            final String cellValue = row.getCell(5).getStringCellValue();
            System.out.println("== " + cellValue);
            this.colleges = Splitter.on(",").trimResults().split(cellValue);
        }
        if (row.getCell(6) != null) {
            final String cellValue = row.getCell(6).getStringCellValue();
            try {
                if (isNotEmpty(cellValue)) {
                    this.scrutinPrecedentDate = DateUtils.parseDateStrictly(cellValue, TOUR_DATE_PATTERNS);
                }
            }
            catch (final ParseException e) {
                // keep previous value

                this.log.info(String.format(
                        "Unable to parse scrutinPrecedentDate '%s' with patterns : %s",
                        cellValue,
                        Joiner.on(", ").join(TOUR_DATE_PATTERNS)), e);
            }
        }

        // election infos
        if (row.getCell(7) != null) {
            final String cellValue = row.getCell(7).getStringCellValue();
            this.partielle = BooleanUtils.toBoolean(cellValue.toLowerCase(), "oui", "non");
        }
        if (row.getCell(8) != null) {
            this.electeursInscritsCount = (int) row.getCell(8).getNumericCellValue();
        }
        if (row.getCell(9) != null) {
            this.electeursVotantsCount = (int) row.getCell(9).getNumericCellValue();
        }
        if (row.getCell(10) != null) {
            this.bulletinsBlancsOuNulsCount = (int) row.getCell(10).getNumericCellValue();
        }
        if (row.getCell(11) != null) {
            this.siegesCount = (int) row.getCell(11).getNumericCellValue();
        }

        // liste
        if (row.getCell(12) != null) {
            this.listeNom = row.getCell(12).getStringCellValue();
        }
        if (row.getCell(13) != null) {
            this.listeBulletinsValablesCount = (int) row.getCell(13).getNumericCellValue();
        }

        // candidat
        this.candidatNom = (row.getCell(14) != null)
                ? row.getCell(14).getStringCellValue()
                : "";
        this.candidatPrenom = (row.getCell(15) != null)
                ? row.getCell(15).getStringCellValue()
                : "";
        if (row.getCell(16) != null) {
            final String cellValue = row.getCell(16).getStringCellValue();
            try {
                if (isNotEmpty(cellValue)) {
                    this.candidatNaissanceDate = DateUtils.parseDateStrictly(cellValue, TOUR_DATE_PATTERNS);
                }
                else {
                    this.candidatNaissanceDate = null;
                }
            }
            catch (final ParseException e) {
                // keep previous value

                this.log.info(String.format(
                        "Unable to parse candidatNaissanceDate '%s' with patterns : %s",
                        cellValue,
                        Joiner.on(", ").join(TOUR_DATE_PATTERNS)), e);
            }
        }
        else {
            this.candidatNaissanceDate = null;
        }
        if (row.getCell(17) != null) {
            final String cellValue = row.getCell(17).getStringCellValue();
            final Sexe sexe = ImmutableMap.of("H", Sexe.HOMME, "F", Sexe.FEMME).get(cellValue);
            this.candidatSexe = (sexe == null)
                    ? Sexe.NONE
                    : sexe;
        }
        else {
            this.candidatSexe = Sexe.NONE;
        }
        if (row.getCell(18) != null) {
            this.candidatVoixCount = (int) row.getCell(18).getNumericCellValue();
        }
        else {
            this.candidatVoixCount = 0;
        }
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#getTourIndex()
     */
    @Override
    public int getTourIndex()
    {
        return this.tourIndex;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#getTourDate()
     */
    @Override
    public Date getTourDate()
    {
        return this.tourDate;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#getElectionType()
     */
    @Override
    public Type getElectionType()
    {
        return this.type;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#getElectionCategorie()
     */
    @Override
    public Categorie getElectionCategorie()
    {
        return this.categorie;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#getEtablissement()
     */
    @Override
    public String getEtablissement()
    {
        return this.etablissement;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#getColleges()
     */
    @Override
    public Iterable<String> getColleges()
    {
        return this.colleges;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#getScrutinPrecedentDate()
     */
    @Override
    public Date getScrutinPrecedentDate()
    {
        return this.scrutinPrecedentDate;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#isPartielle()
     */
    @Override
    public boolean isPartielle()
    {
        return this.partielle;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#getElecteursInscritsCount()
     */
    @Override
    public int getElecteursInscritsCount()
    {
        return this.electeursInscritsCount;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#getElecteursVotantsCount()
     */
    @Override
    public int getElecteursVotantsCount()
    {
        return this.electeursVotantsCount;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#getBulletinsBlancsOuNulsCount()
     */
    @Override
    public int getBulletinsBlancsOuNulsCount()
    {
        return this.bulletinsBlancsOuNulsCount;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#getSiegesCount()
     */
    @Override
    public int getSiegesCount()
    {
        return this.siegesCount;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#getListeNom()
     */
    @Override
    public String getListeNom()
    {
        return this.listeNom;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#getListeBulletinsValablesCount()
     */
    @Override
    public int getListeBulletinsValablesCount()
    {
        return this.listeBulletinsValablesCount;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#getCandidatNom()
     */
    @Override
    public String getCandidatNom()
    {
        return this.candidatNom;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#getCandidatPrenom()
     */
    @Override
    public String getCandidatPrenom()
    {
        return this.candidatPrenom;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#getCandidatNaissanceDate()
     */
    @Override
    public Date getCandidatNaissanceDate()
    {
        return this.candidatNaissanceDate;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#getCandidatSexe()
     */
    @Override
    public Sexe getCandidatSexe()
    {
        return this.candidatSexe;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.dao.xls.CandidatRowValueGetter#getCandidatVoixCount()
     */
    @Override
    public int getCandidatVoixCount()
    {
        return this.candidatVoixCount;
    }

    private static final String[] TOUR_DATE_PATTERNS = {
        "dd/MM/yyyy"
    };

    private final Logger log = Logger.getLogger(this.getClass());

    private int tourIndex;

    private Date tourDate;

    private Type type;

    private Categorie categorie;

    private String etablissement;

    private Iterable<String> colleges;

    private Date scrutinPrecedentDate;

    private boolean partielle;

    private int electeursInscritsCount;

    private int electeursVotantsCount;

    private int bulletinsBlancsOuNulsCount;

    private int siegesCount;

    private String listeNom;

    private int listeBulletinsValablesCount;

    private String candidatNom;

    private String candidatPrenom;

    private Date candidatNaissanceDate;

    private Sexe candidatSexe;

    private int candidatVoixCount;

}
