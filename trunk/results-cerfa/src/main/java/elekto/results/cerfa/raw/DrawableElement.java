package elekto.results.cerfa.raw;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

interface DrawableElement {

    void draw(
            PDDocument pdDocument)
        throws IOException;


    void prepareText();

}
