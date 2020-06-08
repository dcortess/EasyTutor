package cat.udl.tidic.amb.easytutor.services;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AnunciService {

    //@Headers("Authorization:656e50e154865a5dc469b80437ed2f963b8f58c8857b66c9bf")

    @POST("/anuncis/create")
    Call<Void> createAnunci(@Body JsonObject user);
}
