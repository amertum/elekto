package elekto.results.dao.xls;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;

class EtablissementRowValueGetter {

    public void updateRow(
            final HSSFRow row)
    {
        this.nom = (row.getCell(0) != null)
                ? row.getCell(0).getStringCellValue()
                : "";
        this.raisonSociale = (row.getCell(1) != null)
                ? row.getCell(1).getStringCellValue()
                : "";
        this.adresse1 = (row.getCell(2) != null)
                ? row.getCell(2).getStringCellValue()
                : "";
        this.adresse2 = (row.getCell(3) != null)
                ? row.getCell(3).getStringCellValue()
                : "";
        this.codePostal = this.getAsString(row.getCell(4), "");
        this.ville = (row.getCell(5) != null)
                ? row.getCell(5).getStringCellValue()
                : "";
        this.siret = this.getAsString(row.getCell(6), "");
        this.idcc = this.getAsString(row.getCell(7), "");
    }


    private String getAsString(
            final HSSFCell cell,
            final String defaultValue)
    {
        if (cell == null) {
            return defaultValue;
        }

        final String asString;

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                asString = String.valueOf(cell.getNumericCellValue());
                this.log.warn("cell[row:" + cell.getRowIndex() + ",col:" + cell.getColumnIndex() + "] = " + asString
                        + " must be of string type, not numeric");
                break;

            case Cell.CELL_TYPE_STRING:
                asString = cell.getStringCellValue();
                break;

            default:
                asString = defaultValue;
        }

        return asString;
    }


    public String getNom()
    {
        return this.nom;
    }


    public String getRaisonSociale()
    {
        return this.raisonSociale;
    }


    public String getAdresse1()
    {
        return this.adresse1;
    }


    public String getAdresse2()
    {
        return this.adresse2;
    }


    public String getCodePostal()
    {
        return this.codePostal;
    }


    public String getVille()
    {
        return this.ville;
    }


    public String getSiret()
    {
        return this.siret;
    }


    public String getIdcc()
    {
        return this.idcc;
    }

    private final Logger log = Logger.getLogger(this.getClass());

    private String nom;

    private String raisonSociale;

    private String adresse1;

    private String adresse2;

    private String codePostal;

    private String ville;

    private String siret;

    private String idcc;

}
