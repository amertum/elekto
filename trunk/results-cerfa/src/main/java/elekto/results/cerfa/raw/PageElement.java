package elekto.results.cerfa.raw;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

interface PageElement {

    void drawText(
            final PDDocument pdDocument,
            final String text)
        throws IOException;


    void drawText(
            final PDDocument pdDocument,
            final String text,
            final int horizontalIndex,
            final int verticalIndex)
        throws IOException;

}
