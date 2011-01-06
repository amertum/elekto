package elekto.results;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.View;

public class ClasspathResourceView
        implements View {

    @Override
    public String getContentType()
    {
        this.log.info("getContentType()=" + this.contentType);
        return this.contentType;
    }


    @Override
    public void render(
            final Map<String, ?> model,
            final HttpServletRequest request,
            final HttpServletResponse response)
        throws Exception
    {
        if (model.containsKey(CONTENT_TYPE_MODEL_KEY)) {
            this.contentType = (String) model.get(CONTENT_TYPE_MODEL_KEY);
        }
        if (model.containsKey(CLASSPATH_RESOURCE_MODEL_KEY)) {
            final InputStream inStream = this.getClass().getResourceAsStream(
                    (String) model.get(CLASSPATH_RESOURCE_MODEL_KEY));
            final ServletOutputStream outStream = response.getOutputStream();
            IOUtils.copy(inStream, outStream);
            outStream.flush();
        }
    }

    private final Logger log = Logger.getLogger(this.getClass());

    private String contentType = DEFAULT_CONTENT_TYPE;

    private static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";

    static final String CONTENT_TYPE_MODEL_KEY = "contentType";

    static final String CLASSPATH_RESOURCE_MODEL_KEY = "classpathResource";

}
