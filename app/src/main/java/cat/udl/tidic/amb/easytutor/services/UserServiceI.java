package cat.udl.tidic.amb.easytutor.services;

import androidx.lifecycle.MutableLiveData;

import cat.udl.tidic.amb.easytutor.models.User;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserServiceI {

    @POST("account/create_token")
    void createTokenUser(@Header("Authorization") String auth);

    @POST("account/delete_token")
    void deleteTokenUser(@Header("Authorization") String auth);

    @POST("account/profile")
    void getProfileUser(@Header("Authorization") String auth);

    MutableLiveData<String> getLiveDataToken();

    MutableLiveData<User> getLiveDataUser();
}
