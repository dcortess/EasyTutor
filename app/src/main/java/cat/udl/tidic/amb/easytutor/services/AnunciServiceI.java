package cat.udl.tidic.amb.easytutor.services;


import androidx.lifecycle.MutableLiveData;

import java.util.List;

import cat.udl.tidic.amb.easytutor.models.Anunci;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface AnunciServiceI {

    @GET("/anuncis/list")
    void getAnuncis(@Header("Authorization") String auth);

    @GET("/anuncis/show/{id}")
    void getAnunci(@Header("Authorization") String auth, @Path("id") String id);

    MutableLiveData<List<Anunci>> getResponseLiveDataAnuncis();
    MutableLiveData<Anunci> getResponseLiveDataAnunci();
}
