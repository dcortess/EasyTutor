package cat.udl.tidic.amb.easytutor.services;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cat.udl.tidic.amb.easytutor.dao.IUserDAO;
import cat.udl.tidic.amb.easytutor.dao.UserDAOImpl;
import cat.udl.tidic.amb.easytutor.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserServiceImpl implements UserServiceI {

    private IUserDAO userDAO;
    public final MutableLiveData<String> token;
    public final MutableLiveData<User> user;
    public UserServiceImpl() {
        userDAO = new UserDAOImpl();
        token = new MutableLiveData<>();
        user = new MutableLiveData<>();
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
                        String authToken = response.body().string();

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
    public void getProfileUser(final String Auth){

        userDAO.getProfileUser(Auth).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200 ){
                    try {

                        String respuestaBody = response.body().string();
                        JSONObject mUserjson = new JSONObject(respuestaBody);

                        User u = new User();

                        u.setUsername(mUserjson.getString("username"));
                        u.setName(mUserjson.getString("name"));
                        u.setSurname(mUserjson.getString("surname"));
                        u.setPhone(mUserjson.getString("phone"));
                        u.setGender(mUserjson.getString("gender"));
                        u.setEmail(mUserjson.getString("email"));


                        Log.d("getUser", u.getUsername());

                        user.setValue(u);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    user.setValue(new User());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("getUser", t.getMessage());
                user.setValue(new User());
            }
        });
    }
}
