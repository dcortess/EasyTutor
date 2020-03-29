package cat.udl.tidic.amb.easytutor.viewmodel;

import android.app.Application;
import android.util.Base64;

import androidx.lifecycle.AndroidViewModel;

import java.nio.charset.StandardCharsets;

import cat.udl.tidic.amb.easytutor.services.UserServiceI;
import cat.udl.tidic.amb.easytutor.services.UserServiceImpl;

public class UserViewModel extends AndroidViewModel {
    private UserServiceI repository;

    public UserViewModel(Application application) {
        super(application);
        repository = new UserServiceImpl();



    }

    public void createTokenUser(String user, String pass){
        String header = "34" + user + ":" + pass;
        byte[] data = header.getBytes(StandardCharsets.UTF_8);
        header = Base64.encodeToString(data, Base64.DEFAULT);
        header = ("Authentication " + header).trim();
        repository.createTokenUser(header);
    }

}
