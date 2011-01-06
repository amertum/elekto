package elekto.results.dao.xls;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

class EtablissementsSheet {

    EtablissementsSheet(
            final HSSFSheet etablissementsSheet)
    {
        this.etablissementsSheet = etablissementsSheet;
        this.etablissements = newHashMap();
    }


    public void load()
    {
        final int lastRowIndex = this.etablissementsSheet.getLastRowNum();
        for (int i = 1; (i <= lastRowIndex); i++) {
            final HSSFRow row = this.etablissementsSheet.getRow(i);
            this.logRow(row);

            final EtablissementRowValueGetter rowValues = new EtablissementRowValueGetter();
            rowValues.updateRow(row);

            this.etablissements.put(rowValues.getNom(), rowValues);
        }
    }


    public EtablissementRowValueGetter find(
            final String nom)
    {
        return this.etablissements.get(nom);
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

    private final HSSFSheet etablissementsSheet;

    private final Map<String, EtablissementRowValueGetter> etablissements;

}
