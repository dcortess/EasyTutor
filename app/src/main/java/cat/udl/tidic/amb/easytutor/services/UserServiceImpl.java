package cat.udl.tidic.amb.easytutor.services;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cat.udl.tidic.amb.easytutor.dao.IUserDAO;
import cat.udl.tidic.amb.easytutor.dao.UserDAOImpl;
import cat.udl.tidic.amb.easytutor.models.User;
import cat.udl.tidic.amb.easytutor.preferences.PreferencesProvider;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserServiceImpl implements UserServiceI {

    private IUserDAO userDAO;
    private String TAG = "UserServiceImpl";

    public final MutableLiveData<String> token;
    public final MutableLiveData<User> user;
    public final MutableLiveData<String> deleteToken;
    public UserServiceImpl() {
        userDAO = new UserDAOImpl();
        token = new MutableLiveData<>();
        user = new MutableLiveData<>();
        deleteToken = new MutableLiveData<>();
    }
    public MutableLiveData<String> getLiveDataToken(){
        return token;
    }
    public MutableLiveData<User> getLiveDataUser(){
        return user;
    }

    @Override
    public void createTokenUser(String Auth){

        userDAO.createTokenUser(Auth).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200 ){
                    try {
                        String authToken = response.body().string().split(":")[1];
                        authToken = authToken.substring(2,authToken.length() -2);
                        Log.d(TAG, "Rebut el token: " + authToken);
                        PreferencesProvider.providePreferences().edit().putString("token",authToken).apply();
                        token.setValue(authToken);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    String aux = null;
                    try {
                        String r = response.errorBody().string();
                        token.setValue(r);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("UserService", t.getMessage());
                token.setValue(t.getMessage());
            }
        });
    }

    @Override
    public void deleteTokenUser(String auth){
        userDAO.deleteTokenUser(auth).enqueue(new Callback<ResponseBody>(){

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code() == 200 ) {
                    try {

                            PreferencesProvider.providePreferences().edit().putString("token", "").apply();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                else{
                    String aux = null;
                    try {
                        String r = response.errorBody().string();
                        deleteToken.setValue(r);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("UserService", t.getMessage());
                deleteToken.setValue(t.getMessage());
            }
        });
    }

    @Override
    public void getProfileUser(final String Auth){

        userDAO.getProfileUser(Auth).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200 ){
                    User u = response.body();
                    Log.d(TAG, "Rebut el user: " + u.toString());


                    user.setValue(u);

                }
                else{
                    user.setValue(new User());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("getUser", t.getMessage());
                user.setValue(new User());
            }
        });
    }
}
