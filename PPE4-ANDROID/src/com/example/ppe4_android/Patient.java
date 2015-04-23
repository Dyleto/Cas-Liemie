package com.example.ppe4_android;

import java.util.Date;

public class Patient {
	
	/*
	 * DonnÃ©es ne pouvant Ãªtre modifiÃ©es
	 */
	private String idC,nom,prenom,adrRue,CP,ville,telephone;
	private Date dateNaiss;
	/*
	 * DonnÃ©es Ã  saisir
	 */

	
	
	public Patient() {
	}

    public String getIdC() {
        return idC;
    }
    public void setIdC(String idC) {
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
    public String getAdrRue() {
        return adrRue;
    }
    public void setAdrRue(String adrRue) {
        this.adrRue = adrRue;
    }
    public String getCP() {
        return CP;
    }
    public void setCP(String CP) {
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

    public Patient(String videntifiant, String vnom, String vprenom,
			String vadresse, String vcp, String vville, String vtl,
			Date vdateNaiss) {
		idC = videntifiant;
		nom = vnom;
		prenom = vprenom;
		adrRue = vadresse;
		CP = vcp;
		ville = vville;
		telephone = vtl;
		dateNaiss = vdateNaiss;

	}

	public void recopiePatient(Patient patient)
	{
        idC= patient.idC;
		nom=patient.nom;
		prenom=patient.prenom;
        adrRue=patient.adrRue;
		CP=patient.CP;
		ville=patient.ville;
		telephone=patient.telephone;
		dateNaiss=patient.dateNaiss;

	}





}

