package com.example.ppe4_android;

import java.util.Date;

/**
 * Created by Erwan on 29/04/2015.
 */
public class Visite {


        /*
         * DonnÃ©es ne pouvant Ãªtre modifiÃ©es
         */
        private String idC,nom,prenom,telephone,CP,adrRue,ville;
       // private Date DatePrevuDeb;
        private boolean sexe;
        /*
         * DonnÃ©es Ã  saisir
         */



        public Visite() {
        }


        public Visite(String vidC, String vnom, String vprenom,
                       String vtelephone, String vCP, String vadrRue, String vville
                       ) {
            idC = vidC;
            nom = vnom;
            prenom = vprenom;
            adrRue = vadrRue;
            CP = vCP;
            sexe = true;
            ville = vville;
            telephone = vtelephone;
           // DatePrevuDeb = vDatePrevuDebu;

        }

        public void recopieVisite(Visite visite)
        {
            idC= visite.idC;
            nom=visite.nom;
            prenom=visite.prenom;
            adrRue=visite.adrRue;
            CP=visite.CP;
            ville=visite.ville;
            sexe = visite.sexe;
            telephone=visite.telephone;

           // DatePrevuDeb=visite.DatePrevuDeb;

        }



   //     public Date getDatePrevuDeb() {
   //     return DatePrevuDeb;
   // }
   //     public void setDatePrevuDeb(Date datePrevDeb) {
   //     this.DatePrevuDeb = datePrevDeb;
   // }
        public String getIdentifiant() {
            return idC;
        }
        public void setIdentifiant(String identifiant) {
            this.idC = identifiant;
        }
        public String getPrenom() {
            return prenom;
        }
        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }
        public String getNom() {
            return nom;
        }
        public void setNom(String nom) {
            this.nom = nom;
        }
        public String getSexe() {
        if(sexe == true)
            return "Mme.";
        else
            return "Mr.";
    }
        public boolean isSexe() { return sexe;}

          public void setSexe(boolean sexe) {
        this.sexe = sexe;
        if (!isSexe())
            this.sexe = false;
    }
        public String getAdresse() {
            return adrRue;
        }
        public void setAdresse(String adresse) {
            this.adrRue = adresse;
        }
        public String getCodePostal() {
            return CP;
        }
        public void setCodePostal(String codePostal) {
            this.CP = codePostal;
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




    }

