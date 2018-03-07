package com.example.ethan.loyaltycards;

/**
 * Created by ethan on 2018/02/12.
 */

public class LoyaltyCards {
    private int Id;
    private String Store;
    private String Programme;
    private String Thumbnail;
    private Boolean Favourite;
    private Boolean Active;
    private String Barcode;

    public LoyaltyCards(){}

    public LoyaltyCards(int id,String storeName,String loyaltyProgrammeName,String thumbnail,Boolean Favourite, Boolean Active, String Barcode){
        this.setId(id);
        this.setStore(storeName);
        this.setProgramme(loyaltyProgrammeName);
        this.setThumbnail(thumbnail);
        this.setFavourite(Favourite);
        this.setActive(Active);
        this.setBarcode(Barcode);
    }


    public int getId() {
        return Id;
    }

    public String getIdString() { return new StringBuilder().append(Id).toString(); }

    public void setId(int id) {
        Id = id;
    }

    public String getStore() {
        return Store;
    }

    public void setStore(String store) {
        Store = store;
    }

    public String getProgramme() {
        return Programme;
    }

    public void setProgramme(String programme) {
        Programme = programme;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public Boolean getFavourite() {
        return Favourite;
    }

    public void setFavourite(Boolean favourite) {
        Favourite = favourite;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }

    public String getBarcode() { return Barcode; }

    public void setBarcode(String barcode) { Barcode = barcode; }
}
