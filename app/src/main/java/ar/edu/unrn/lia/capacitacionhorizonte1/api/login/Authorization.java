package ar.edu.unrn.lia.capacitacionhorizonte1.api.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Authorization {

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("token")
    @Expose
    private String token;

    public Authorization(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
