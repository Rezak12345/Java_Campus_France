package models;

public class Etudiant {
    private String email;
    private String motDePasse;
    private String confirmationMotDePasse;
    private String civilite;
    private String nom;
    private String prenom;
    private String paysResidence;
    private String paysNationalite;
    private String codePostal;
    private String ville;
    private String telephone;
    private String profil;
    private String domaineActivite;
    private String niveauEtude;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public String getConfirmationMotDePasse() { return confirmationMotDePasse; }
    public void setConfirmationMotDePasse(String confirmationMotDePasse) { this.confirmationMotDePasse = confirmationMotDePasse; }

    public String getCivilite() { return civilite; }
    public void setCivilite(String civilite) { this.civilite = civilite; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getPaysResidence() { return paysResidence; }
    public void setPaysResidence(String paysResidence) { this.paysResidence = paysResidence; }

    public String getPaysNationalite() { return paysNationalite; }
    public void setPaysNationalite(String paysNationalite) { this.paysNationalite = paysNationalite; }

    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getProfil() { return profil; }
    public void setProfil(String profil) { this.profil = profil; }

    public String getDomaineActivite() { return domaineActivite; }
    public void setDomaineActivite(String domaineActivite) { this.domaineActivite = domaineActivite; }

    public String getNiveauEtude() { return niveauEtude; }
    public void setNiveauEtude(String niveauEtude) { this.niveauEtude = niveauEtude; }
}
