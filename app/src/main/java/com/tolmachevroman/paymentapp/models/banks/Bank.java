package com.tolmachevroman.paymentapp.models.banks;

import com.google.gson.annotations.SerializedName;

/**
 * Created by romantolmachev on 13/12/2017.
 */

public class Bank {

    @SerializedName("id")
    private String id;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getName() {
        return name;
    }
}
