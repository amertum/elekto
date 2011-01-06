package elekto.results.dao.xls;

import java.io.InputStream;

public class Templates {

    public static InputStream getElectionsTemplateAsStream()
    {
        return Templates.class.getResourceAsStream("/elections-template.xls");
    }

}
