package cat.udl.tidic.amb.easytutor.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
        String header= "Token 34" + user + ":" + pass;
        this.repository.createTokenUser(header);
    }

    public LiveData<User> getResponseLiveDataUser() {
        return this.responseLiveUser;
    }
    public LiveData<String> getResponseLiveDataToken() {
        return this.responseLiveDataToken;
    }

}
