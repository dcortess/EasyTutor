package cat.udl.tidic.amb.easytutor.dao;

import java.util.List;

import cat.udl.tidic.amb.easytutor.models.Anunci;
import cat.udl.tidic.amb.easytutor.models.AnunciType;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IAnunciDAO {

    @GET("/anuncis/list")
    Call<List<Anunci>> getAnuncis(@Header("Authorization") String auth);

    @GET("/anuncis/show/{id}")
    Call<Anunci> getAnunci(@Header("Authorization") String auth, @Path("id") String id);

    @GET("/anuncis/list")
    Call<List<Anunci>> getAnuncisFiltered(@Header("Authorization") String auth, @Query("type")String type);
}
