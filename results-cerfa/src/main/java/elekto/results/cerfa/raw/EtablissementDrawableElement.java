package elekto.results.cerfa.raw;

import static com.google.common.collect.Iterables.get;
import static com.google.common.collect.Lists.newArrayList;
import static elekto.results.cerfa.raw.EtablissementPageElement.ETABLISSEMENT_ADRESSE1;
import static elekto.results.cerfa.raw.EtablissementPageElement.ETABLISSEMENT_ADRESSE2;
import static elekto.results.cerfa.raw.EtablissementPageElement.ETABLISSEMENT_AUTRES_SIRET;
import static elekto.results.cerfa.raw.EtablissementPageElement.ETABLISSEMENT_CODE_POSTAL;
import static elekto.results.cerfa.raw.EtablissementPageElement.ETABLISSEMENT_IDCC;
import static elekto.results.cerfa.raw.EtablissementPageElement.ETABLISSEMENT_RAISON_SOCIALE;
import static elekto.results.cerfa.raw.EtablissementPageElement.ETABLISSEMENT_SIRET;
import static elekto.results.cerfa.raw.EtablissementPageElement.ETABLISSEMENT_VILLE;

import java.io.IOException;
import java.util.ListIterator;

import org.apache.pdfbox.pdmodel.PDDocument;

import elekto.results.cerfa.model.EtablissementInfos;

/**
 * Dessine les informations sur l'établissement.
 */
class EtablissementDrawableElement
        implements DrawableElement {

    public EtablissementDrawableElement(
            final EtablissementInfos etablissement)
    {
        this.etablissement = etablissement;
    }


    @Override
    public void draw(
            final PDDocument pdDocument)
        throws IOException
    {
        ETABLISSEMENT_RAISON_SOCIALE.drawText(pdDocument, this.etablissement.getRaisonSociale());
        ETABLISSEMENT_ADRESSE1.drawText(pdDocument, get(this.etablissement.getAdresses(), 0));
        ETABLISSEMENT_ADRESSE2.drawText(pdDocument, get(this.etablissement.getAdresses(), 1));
        ETABLISSEMENT_CODE_POSTAL.drawText(pdDocument, this.etablissement.getCodePostal());
        ETABLISSEMENT_VILLE.drawText(pdDocument, this.etablissement.getVille());
        ETABLISSEMENT_SIRET.drawText(pdDocument, this.etablissement.getSiret());
        for (final ListIterator<String> iter = newArrayList(this.etablissement.getAutresSiret()).listIterator(); iter.hasNext();) {
            final int index = iter.nextIndex();
            final String autreSiret = iter.next();

            ETABLISSEMENT_AUTRES_SIRET.drawText(pdDocument, autreSiret, 0, index);
        }
        ETABLISSEMENT_IDCC.drawText(pdDocument, this.etablissement.getIdcc());
    }


    @Override
    public void prepareText()
    {
        // do nothing
    }

    private final EtablissementInfos etablissement;

}
