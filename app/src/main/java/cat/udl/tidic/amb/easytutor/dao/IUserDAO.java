package cat.udl.tidic.amb.easytutor.dao;

import com.google.gson.JsonObject;
import cat.udl.tidic.amb.easytutor.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IUserDAO {

    @POST("account/create_token")
    Call<ResponseBody> createTokenUser(@Header("Authorization") String auth);
    @POST("account/delete_token")
    Call<ResponseBody> deleteTokenUser(@Header("Authorization") String auth);

    /*
     * @Jordi: Molt més simple utilitzar la classe User, així no cal treballar amb json
     * evitem problemes i programem menys. La llibreria GSON de retrofit us fa la feina
     * model està serialitzat.
     * */
    @GET("account/profile")
    Call<User> getProfileUser(@Header("Authorization") String auth);

    @POST("account/update_profile")
    Call<ResponseBody> updateProfileUser(@Header("Authorization") String auth, @Body JsonObject json);
}
