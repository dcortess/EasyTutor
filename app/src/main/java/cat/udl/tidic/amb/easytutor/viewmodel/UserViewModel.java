package cat.udl.tidic.amb.easytutor.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.nio.charset.StandardCharsets;

import cat.udl.tidic.amb.easytutor.models.User;
import cat.udl.tidic.amb.easytutor.preferences.PreferencesProvider;
import cat.udl.tidic.amb.easytutor.services.UserServiceI;
import cat.udl.tidic.amb.easytutor.services.UserServiceImpl;

public class UserViewModel extends AndroidViewModel {
    private UserServiceI repository;
    private MutableLiveData<String> responseLiveDataToken;
    private MutableLiveData<User> responseLiveUser;
    private SharedPreferences mPreferences;


    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserServiceImpl();

        responseLiveDataToken = repository.getLiveDataToken();
        responseLiveUser = repository.getLiveDataUser();

        this.mPreferences = PreferencesProvider.providePreferences();
    }

    public void getProfileUser(){
        String header = this.mPreferences.getString("token","");
        repository.getProfileUser(header);
    }

    public void createTokenUser(String user, String pass){
        String header= user + ":" + pass;
        byte[] bytes = header.getBytes(StandardCharsets.UTF_8);
        String _token = Base64.encodeToString(bytes, Base64.DEFAULT);
        _token = ("Authentication: " + _token).trim();
        this.repository.createTokenUser(_token);
    }

    public void deleteTokenUser(){
        String auth = this.mPreferences.getString("token","");
        this.repository.deleteTokenUser(auth);
    }
    public LiveData<User> getResponseLiveDataUser() {
        return this.responseLiveUser;
    }
    public LiveData<String> getResponseLiveDataToken() {
        return this.responseLiveDataToken;
    }

}
