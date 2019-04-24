package com.wenlong.hope;

public class HighlightModel {
    String cardName;
    int imageID;
    int isTurned;
    int isFav;

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    public String getCardName() {
        return cardName;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
    public int getImageID() {
        return imageID;
    }

    public void setIsTurned(int isTurned) {
        this.isTurned = isTurned;
    }
    public int getIsTurned() {
        return isTurned;
    }

    public void setIsFav(int isFav) {
        this.isFav = isFav;
    }
    public int getIsFav() {
        return isFav;
    }
}
