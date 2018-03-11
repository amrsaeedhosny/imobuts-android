package com.example.amrshosny.imobuts;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonResponse<ResponseItem> {
    @SerializedName("response")
    @Expose
    private ResponseItem response;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("token")
    @Expose
    private String token;

    public ResponseItem getResponse() {
        return response;
    }

    public void setResponse(ResponseItem response) {
        this.response = response;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
