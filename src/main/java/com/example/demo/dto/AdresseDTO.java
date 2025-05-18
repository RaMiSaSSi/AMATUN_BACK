// AdresseDTO.java
package com.example.demo.dto;

import com.example.demo.model.Adresse;

public class AdresseDTO {
    private long id;
    private String rue;
    private String codePostal;
    private String ville;
    private String pays;
    private Double latitude;
    private Double longitude;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Adresse toEntity() {
        Adresse adresse = new Adresse();
        adresse.setId(this.id);
        adresse.setRue(this.rue);
        adresse.setCodePostal(this.codePostal);
        adresse.setVille(this.ville);
        adresse.setPays(this.pays);
        adresse.setLatitude(this.latitude);
        adresse.setLongitude(this.longitude);
        return adresse;
    }


}