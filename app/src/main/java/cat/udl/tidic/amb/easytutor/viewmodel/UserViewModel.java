package cat.udl.tidic.amb.easytutor.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import cat.udl.tidic.amb.easytutor.services.UserServiceI;
import cat.udl.tidic.amb.easytutor.services.UserServiceImpl;

public class UserViewModel extends AndroidViewModel {
    private UserServiceI repository;

    public UserViewModel(Application application) {
        super(application);
        repository = new UserServiceImpl();



    }

    public void createTokenUser(String user, String pass){
        String header= "Token 34" + user + ":" + pass;
        this.repository.createTokenUser(header);
    }

}
