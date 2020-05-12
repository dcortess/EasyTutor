package cat.udl.tidic.amb.easytutor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import android.content.Intent;
import android.content.SharedPreferences;
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
import cat.udl.tidic.amb.easytutor.preferences.PreferencesProvider;
import cat.udl.tidic.amb.easytutor.services.UserService;

import cat.udl.tidic.amb.easytutor.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    // Us he d'acostumar a fer servir les eines de log!
    private String TAG = "Main Activity";

    UserViewModel userViewModel;
    Button login;
    EditText user;
    EditText pass;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView register = findViewById(R.id.textView_register);
        TextView forgot = findViewById(R.id.textView_forgot);

        login= findViewById(R.id.button_login);
        user= findViewById(R.id.editText_user);
        pass= findViewById(R.id.editText_pass);

        //@JordiMateoUdL Sense aquesta línia mai funcionarà!!!!!
        mPreferences = PreferencesProvider.providePreferences();

        //@JordiMateoUdL Què passa si l'usuari ja ha aconseguit el token! No s'ha de tornar a registrar!
        String token = mPreferences.getString("token","");
        if (!token.equals("")){
            moveNext();
        }



        userViewModel = new UserViewModel(getApplication());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //@JordiMateoUdL Què passa si no introdueixo texts als formularis!
                String _username= user.getText().toString();
                String _password= pass.getText().toString();

                if (LoginUtils.isAllLogin(_username, _password)){
                    userViewModel.createTokenUser(_username,_password);
                    Intent intent = new Intent (v.getContext(), ProfileActivity.class);
                    startActivityForResult(intent, 0);
                } else {
                    Toast.makeText(MainActivity.this, "ERROR: Rellena todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), RegistrationActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), AddOfferActivity.class);
                startActivityForResult(intent, 0);
            }
        });



        /*
         * @Jordi: S'ha d'observar, si no, el MVVM no té sentit, ningú notifica a la UI.
         * */
        userViewModel.getResponseLiveDataToken().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                // TODO: Assegurar que s conté un token i no un missatge d'error.

                if (s != null){
                    moveNext();
                }else{
                    Log.d(TAG,"Hi ha  errors en el login, intentant aconseguir el token de l'api " +
                            "però API ha retornat error!!!");
                    // Notificar error a l'usuari.
                }

            }
        });
    }

    public void moveNext(){
        Intent intent = new Intent (this, ProfileActivity.class);
        startActivity(intent);
    }


}

