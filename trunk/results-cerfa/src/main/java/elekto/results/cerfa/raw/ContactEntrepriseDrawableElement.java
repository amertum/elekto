package elekto.results.cerfa.raw;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

import elekto.results.cerfa.model.ContactEntrepriseInfos;

/**
 * Dessine les informations sur la personne à contacter dans l'entreprise.
 */
class ContactEntrepriseDrawableElement
        implements DrawableElement {

    ContactEntrepriseDrawableElement(
            final ContactEntrepriseInfos contactEntrepriseInfos)
    {
        this.contactEntrepriseInfos = contactEntrepriseInfos;
    }


    @Override
    public void draw(
            final PDDocument pdDocument)
        throws IOException
    {
        ContactEntreprisePageElement.NOM.drawText(pdDocument, this.contactEntrepriseInfos.getNom());
        ContactEntreprisePageElement.PRENOM.drawText(pdDocument, this.contactEntrepriseInfos.getPrenom());
        ContactEntreprisePageElement.FONCTION.drawText(pdDocument, this.contactEntrepriseInfos.getFonction());
        ContactEntreprisePageElement.TELEPHONE.drawText(pdDocument, this.contactEntrepriseInfos.getTelephone());
        ContactEntreprisePageElement.TELECOPIE.drawText(pdDocument, this.contactEntrepriseInfos.getTelecopie());
        ContactEntreprisePageElement.EMAIL.drawText(pdDocument, this.contactEntrepriseInfos.getEmail());
    }


    @Override
    public void prepareText()
    {
    }

    private final ContactEntrepriseInfos contactEntrepriseInfos;

}
