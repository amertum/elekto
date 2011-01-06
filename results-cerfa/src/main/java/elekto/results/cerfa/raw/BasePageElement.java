package elekto.results.cerfa.raw;

import static org.apache.commons.lang.StringUtils.isEmpty;

import java.awt.Color;
import java.io.IOException;
import java.text.CharacterIterator;
import java.text.NumberFormat;
import java.text.StringCharacterIterator;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.encoding.EncodingManager;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * Elements de page PDF permettant d'afficher du texte selon plusieurs critères d'affichage.
 */
class BasePageElement
        implements PageElement {

    BasePageElement(
            final int pageIndex,
            final PDFont pdFont,
            final float fontSize,
            final Color fontColor,
            final float textPositionX,
            final float textPositionY,
            final float horizontalStep,
            final float verticalStep,
            final float maxTextWidth,
            final TextSpacingBuilder textSpacingBuilder,
            final boolean center)
    {
        this.pageIndex = pageIndex;
        this.pdFont = pdFont;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
        this.textPositionX = textPositionX;
        this.textPositionY = textPositionY;
        this.horizontalStep = horizontalStep;
        this.verticalStep = verticalStep;
        this.maxTextWidth = maxTextWidth;
        this.textSpacingBuilder = textSpacingBuilder;
        this.center = center;
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.cerfa.raw.PageElement#drawText(org.apache.pdfbox.pdmodel.PDDocument, java.lang.String)
     */
    @Override
    public void drawText(
            final PDDocument pdDocument,
            final String text)
        throws IOException
    {
        this.drawText(pdDocument, text, this.textPositionX, this.textPositionY);
    }


    /**
     * {@inheritDoc}
     * 
     * @see elekto.results.cerfa.raw.PageElement#drawText(org.apache.pdfbox.pdmodel.PDDocument, java.lang.String, int,
     *      int)
     */
    @Override
    public void drawText(
            final PDDocument pdDocument,
            final String text,
            final int horizontalIndex,
            final int verticalIndex)
        throws IOException
    {
        this.drawText(
                pdDocument,
                text,
                this.textPositionX + (this.horizontalStep * horizontalIndex),
                this.textPositionY + (this.verticalStep * verticalIndex));
    }


    private void drawText(
            final PDDocument pdDocument,
            final String text,
            final float textPositionX,
            final float textPositionY)
        throws IOException
    {
        if (isEmpty(text)) {
            return;
        }

        final PDPage pdPage = (PDPage) pdDocument.getDocumentCatalog().getAllPages().get(this.pageIndex);
        final PDPageContentStream contentStream = new PDPageContentStream(pdDocument, pdPage, true, true);

        final String textToDraw = this.getTextToDrawByDichotomy(text);

        if (this.textSpacingBuilder != null) {
            float positionX = textPositionX;
            for (final TextSpacing spacing : this.textSpacingBuilder.build(textToDraw)) {
                final StringCharacterIterator textIter = new StringCharacterIterator(spacing.getText());
                for (char c = textIter.first(); c != CharacterIterator.DONE; c = textIter.next()) {
                    contentStream.beginText();
                    contentStream.setFont(this.pdFont, this.fontSize);
                    contentStream.setNonStrokingColor(this.fontColor);
                    contentStream.moveTextPositionByAmount(
                            positionX + spacing.getSpacing() * textIter.getIndex(),
                            textPositionY);
                    contentStream.drawString(String.valueOf(c));
                    contentStream.endText();
                }

                positionX += spacing.getSpacing() * spacing.getText().length();
            }
        }
        else {
            final float textPositionXCenter = (this.center)
                    ? ((this.maxTextWidth - this.getTextWidth(textToDraw)) / 3f)
                    : 0f;

            contentStream.beginText();
            contentStream.setFont(this.pdFont, this.fontSize);
            contentStream.setNonStrokingColor(this.fontColor);
            contentStream.moveTextPositionByAmount(textPositionX + textPositionXCenter, textPositionY);
            contentStream.drawString(textToDraw);
            contentStream.endText();
        }

        contentStream.close();
    }


    private String getTextToDrawByDichotomy(
            final String text)
        throws IOException
    {
        String textToDraw = text;
        int middle = text.length();
        int lastMiddle = -1;
        int min = 0;
        int max = text.length();

        for (int i = 0; (i < 10) && (lastMiddle != middle); i++) {
            final float textWidth = this.getTextWidth(textToDraw);
            lastMiddle = middle;

            if (textWidth > this.maxTextWidth) {
                max = middle;
                middle = (middle + min) / 2;
            }
            else {
                min = middle;
                middle = (max + middle) / 2;
            }

            textToDraw = StringUtils.left(text, middle);
        }

        return textToDraw;
    }


    private float getTextWidth(
            final String text)
        throws IOException
    {
        final float textWidth = (this.pdFont.getStringWidth(text) * this.fontSize) / 1000f;

        return textWidth;
    }

    private final int pageIndex;

    private final PDFont pdFont;

    private final float fontSize;

    private final Color fontColor;

    private final float textPositionX;

    private final float textPositionY;

    private final float horizontalStep;

    private final float verticalStep;

    private final float maxTextWidth;

    private final boolean center;

    private final TextSpacingBuilder textSpacingBuilder;

    static final PDFont FONT_HELVETICA = PDType1Font.HELVETICA;

    static final PDFont FONT_HELVETICA_BOLD = PDType1Font.HELVETICA_BOLD;

    /**
     * The character '3' in ZAPF_DINGBATS font is a check.
     */
    static final String CHECK_CHAR = "3";

    static final Locale DRAWING_LOCALE = Locale.FRANCE;

    static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("ddMMyyyy", DRAWING_LOCALE);

    // TODO verify thread safety
    static final NumberFormat INTEGER_FORMAT = NumberFormat.getIntegerInstance(DRAWING_LOCALE);

    static final NumberFormat DECIMAL_FORMAT = NumberFormat.getNumberInstance(DRAWING_LOCALE);

    static final NumberFormat PERCENT_FORMAT = NumberFormat.getPercentInstance(DRAWING_LOCALE);

    static {
        final EncodingManager encodingManager = new EncodingManager();
        try {
            FONT_HELVETICA.setEncoding(encodingManager.getEncoding(COSName.WIN_ANSI_ENCODING));
            FONT_HELVETICA_BOLD.setEncoding(encodingManager.getEncoding(COSName.WIN_ANSI_ENCODING));
        }
        catch (final IOException e) {
            throw new IllegalArgumentException(e);
        }

        DECIMAL_FORMAT.setMaximumFractionDigits(2);
        PERCENT_FORMAT.setMaximumFractionDigits(2);
    }

}
