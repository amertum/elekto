package elekto.results.cerfa.model;

import elekto.results.cerfa.raw.Sexe;

/**
 * Informations de chaque candidats ainsi que leurs résultats.
 */
public class CandidatInfos {

    public int getCandidatVoixNombre()
    {
        return this.candidatVoixNombre;
    }


    public int getListeBulletinsValablesNombre()
    {
        return this.listeBulletinsValablesNombre;
    }


    public int getListeCandidatsNombre()
    {
        return this.listeCandidatsNombre;
    }


    public int getListeCandidatsVoixTotal()
    {
        return this.listeCandidatsVoixTotal;
    }


    public int getListeElusNombre()
    {
        return this.listeElusNombre;
    }


    public float getListeSiegesAttribuesPlusForteMoyenneSiege1()
    {
        return this.listeSiegesAttribuesPlusForteMoyenneSiege1;
    }


    public float getListeSiegesAttribuesPlusForteMoyenneSiege2()
    {
        return this.listeSiegesAttribuesPlusForteMoyenneSiege2;
    }


    public float getListeSiegesAttribuesPlusForteMoyenneSiege3()
    {
        return this.listeSiegesAttribuesPlusForteMoyenneSiege3;
    }


    public float getListeSiegesAttribuesQuotient()
    {
        return this.listeSiegesAttribuesQuotient;
    }


    public float getListeVoixMoyenne()
    {
        return this.listeVoixMoyenne;
    }


    public String getNom()
    {
        return this.nom;
    }


    public String getPrenom()
    {
        return this.prenom;
    }


    public Sexe getSexe()
    {
        return this.sexe;
    }


    public String getSyndicat()
    {
        return this.syndicat;
    }


    public boolean isElu()
    {
        return this.elu;
    }


    public void setCandidatVoixNombre(
            final int candidatVoixNombre)
    {
        this.candidatVoixNombre = candidatVoixNombre;
    }


    public void setElu(
            final boolean elu)
    {
        this.elu = elu;
    }


    public void setListeBulletinsValablesNombre(
            final int listeBulletinsValablesNombre)
    {
        this.listeBulletinsValablesNombre = listeBulletinsValablesNombre;
    }


    public void setListeCandidatsNombre(
            final int listeCandidatsNombre)
    {
        this.listeCandidatsNombre = listeCandidatsNombre;
    }


    public void setListeCandidatsVoixTotal(
            final int listeCandidatsVoixTotal)
    {
        this.listeCandidatsVoixTotal = listeCandidatsVoixTotal;
    }


    public void setListeElusNombre(
            final int listeElusNombre)
    {
        this.listeElusNombre = listeElusNombre;
    }


    public void setListeSiegesAttribuesPlusForteMoyenneSiege1(
            final float listeSiegesAttribuesPlusForteMoyenneSiege1)
    {
        this.listeSiegesAttribuesPlusForteMoyenneSiege1 = listeSiegesAttribuesPlusForteMoyenneSiege1;
    }


    public void setListeSiegesAttribuesPlusForteMoyenneSiege2(
            final float listeSiegesAttribuesPlusForteMoyenneSiege2)
    {
        this.listeSiegesAttribuesPlusForteMoyenneSiege2 = listeSiegesAttribuesPlusForteMoyenneSiege2;
    }


    public void setListeSiegesAttribuesPlusForteMoyenneSiege3(
            final float listeSiegesAttribuesPlusForteMoyenneSiege3)
    {
        this.listeSiegesAttribuesPlusForteMoyenneSiege3 = listeSiegesAttribuesPlusForteMoyenneSiege3;
    }


    public void setListeSiegesAttribuesQuotient(
            final float listeSiegesAttribuesQuotient)
    {
        this.listeSiegesAttribuesQuotient = listeSiegesAttribuesQuotient;
    }


    public void setListeVoixMoyenne(
            final float listeVoixMoyenne)
    {
        this.listeVoixMoyenne = listeVoixMoyenne;
    }


    public void setNom(
            final String nom)
    {
        this.nom = nom;
    }


    public void setPrenom(
            final String prenom)
    {
        this.prenom = prenom;
    }


    public void setSexe(
            final Sexe sexe)
    {
        this.sexe = sexe;
    }


    public void setSyndicat(
            final String syndicat)
    {
        this.syndicat = syndicat;
    }

    /**
     * C00-1
     */
    private String nom;

    /**
     * C00-2
     */
    private String prenom;

    /**
     * C01
     */
    private Sexe sexe;

    /**
     * C02
     */
    private String syndicat;

    /**
     * C03
     */
    private int listeBulletinsValablesNombre;

    /**
     * C04
     */
    private int candidatVoixNombre;

    /**
     * C05
     */
    private int listeCandidatsVoixTotal;

    /**
     * C06
     */
    private int listeCandidatsNombre;

    /**
     * C07
     */
    private float listeVoixMoyenne;

    /**
     * C08
     */
    private float listeSiegesAttribuesQuotient;

    /**
     * C09
     */
    private float listeSiegesAttribuesPlusForteMoyenneSiege1;

    /**
     * C10
     */
    private float listeSiegesAttribuesPlusForteMoyenneSiege2;

    /**
     * C11
     */
    private float listeSiegesAttribuesPlusForteMoyenneSiege3;

    /**
     * C12
     */
    private boolean elu;

    /**
     * C13
     */
    private int listeElusNombre;

}
