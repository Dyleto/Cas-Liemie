package com.example.ppe4_android;

import java.util.Date;

/**
 * Created by Erwan on 29/04/2015.
 */
public class Visite {
private int idC;
private String nom;
private String prenom;
private String adrRue;
private String CP;
private String ville;
private String telephone;
private Date dateNaiss;
private String commentaireVisite;
private Boolean sexe;

public Visite(){}
public Visite(int vidC, String vnom, String vprenom,String vadrRue, String vcp, String vville, String vtl,Date vdateNaiss, Boolean vsexe, String vcommentaireVisite) {
        setIdentifiant(vidC);
        setNom(vnom);
        setPrenom(vprenom);
        setAdresse(vadrRue);
        setCodePostal(vcp);
        setVille(vville);
        setTelephone(vtl);
        setDateNaiss(vdateNaiss);
        setSexe(vsexe);
        setCommentaireVisite(vcommentaireVisite);
        }
public void recopieVisite(Visite visite)
        {
        setIdentifiant(visite.getIdentifiant());
        setNom(visite.getNom());
        setPrenom(visite.getPrenom());
        setAdresse(visite.getAdresse());
        setCodePostal(visite.getCodePostal());
        setVille(visite.getVille());
        setTelephone(visite.getTelephone());
        setDateNaiss(visite.getDateNaiss());
        setCommentaireVisite(visite.getCommentaireVisite());
        }
public int getIdentifiant() {
        return idC;
        }
public void setIdentifiant(int idC) {
        this.idC = idC;
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
public Date getDateNaiss() {
        return dateNaiss;
        }
public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
        }
public String getCommentaireVisite() {
        return commentaireVisite;
        }
public void setCommentaireVisite(String commentaireVisite) {
        this.commentaireVisite = commentaireVisite;
        }

    public String getSexe(boolean sexe) {
       String sex;
       if (sexe==true)
       {
           sex = "Mme";
       }
        else sex = "Mr";
     return sex;
    }

    public void setSexe(Boolean sexe) {
        this.sexe = sexe;
    }
}

