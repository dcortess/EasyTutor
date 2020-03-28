package cat.udl.tidic.amb.easytutor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Objects;

import cat.udl.tidic.amb.easytutor.network.RetrofitClientInstance;
import cat.udl.tidic.amb.easytutor.services.UserService;
import cat.udl.tidic.amb.easytutor.viewmodel.UserViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    UserViewModel userViewModel;
    Button login;
    EditText user;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView register = findViewById(R.id.textView_register);

        login= findViewById(R.id.button_login);
        user= findViewById(R.id.editText_user);
        pass= findViewById(R.id.editText_pass);

        userViewModel = new UserViewModel(getApplication());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _username= user.getText().toString();
                String _password= pass.getText().toString();
                userViewModel.createTokenUser(_username,_password);

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), RegistrationActivity.class);
                startActivityForResult(intent, 0);
            }
        });


    }
}
