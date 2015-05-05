package com.example.ppe4_android;

import java.util.Date;

/**
 * Created by Erwan on 29/04/2015.
 */
public class Visite {
private String nom;
private String prenom;
private String adrRue;
private String CP;
private String ville;
private String telephone;
private String DatePrevuDeb;
private String commentaireVisite;
private Boolean sexe;
private String idV;

public Visite(){}
public Visite(String vidV,String vnom, String vprenom,String vadrRue, String vcp, String vville, String vtl,String vDatePrevuDeb, Boolean vsexe, String vcommentaireVisite) {
        setNom(vnom);
        setPrenom(vprenom);
        setAdresse(vadrRue);
        setCodePostal(vcp);
        setVille(vville);
        setTelephone(vtl);
        setDatePrevuDeb(vDatePrevuDeb);
        setSexe(vsexe);
        setCommentaireVisite(vcommentaireVisite);
       setIdV(vidV);
        }
public void recopieVisite(Visite visite)
        {
            setIdV(visite.getIdV());
        setNom(visite.getNom());
        setPrenom(visite.getPrenom());
        setAdresse(visite.getAdresse());
        setCodePostal(visite.getCodePostal());
        setVille(visite.getVille());
        setTelephone(visite.getTelephone());
        setDatePrevuDeb(visite.getDatePrevuDeb());
        setCommentaireVisite(visite.getCommentaireVisite());
        }

public String getNom() {
        return nom;
        }
public void setNom(String nom) {
        this.nom = nom;
        }
public String getPrenom() {
        return prenom;
        }
public void setPrenom(String prenom) {
        this.prenom = prenom;
        }
public String getAdresse() {
        return adrRue;
        }
public void setAdresse(String adrRue) {
        this.adrRue = adrRue;
        }
public String getCodePostal() {
        return CP;
        }
public void setCodePostal(String CP) {
        this.CP = CP;
        }
public String getVille() {
        return ville;
        }
public void setVille(String ville) {
        this.ville = ville;
        }
public String getTelephone() {
        return telephone;
        }
public void setTelephone(String telephone) {
        this.telephone = telephone;
        }

public String getCommentaireVisite() {
        return commentaireVisite;
        }
public void setCommentaireVisite(String commentaireVisite) {
        this.commentaireVisite = commentaireVisite;
        }

    public String getSexe() {
       String sex;
       if (sexe==true)
       {
           sex = "Mme.";
       }
        else sex = "Mr.";
     return sex;
    }

    public void setSexe(Boolean sexe) {
        this.sexe = sexe;
    }

    public String getDatePrevuDeb() {
        return DatePrevuDeb;
    }

    public void setDatePrevuDeb(String datePrevuDeb) {
        DatePrevuDeb = datePrevuDeb;
    }

    public String getIdV() {
        return idV;
    }

    public void setIdV(String idV) {
        this.idV = idV;
    }
}

