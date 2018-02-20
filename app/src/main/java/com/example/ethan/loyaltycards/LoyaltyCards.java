package com.example.ethan.loyaltycards;

/**
 * Created by ethan on 2018/02/12.
 */

public class LoyaltyCards {
    private String Id;
    private String Store;
    private String Programme;
    private String Thumbnail;
    private Boolean Favourite;

    public LoyaltyCards(){}

    public LoyaltyCards(String id,String storeName,String loyaltyProgrammeName,String thumbnail,Boolean Favourite){
        this.setId(id);
        this.setStore(storeName);
        this.setProgramme(loyaltyProgrammeName);
        this.setThumbnail(thumbnail);
        this.setFavourite(Favourite);
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
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
}
