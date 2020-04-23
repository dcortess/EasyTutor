package cat.udl.tidic.amb.easytutor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Objects;

import cat.udl.tidic.amb.easytutor.network.RetrofitClientInstance;
import cat.udl.tidic.amb.easytutor.services.UserService;
import cat.udl.tidic.amb.easytutor.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    EditText edt_nombre;
    EditText edt_apellido;
    EditText edt_usuario;
    EditText edt_email;
    EditText edt_password;
    EditText edt_genero;
    EditText edt_telefono;
    Button registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edt_nombre = findViewById(R.id.editText_nombre);
        edt_apellido = findViewById(R.id.editText_apellido);
        edt_usuario = findViewById(R.id.editText_username);
        edt_email = findViewById(R.id.editText_email);
        edt_password = findViewById(R.id.editText_pass);
        edt_genero = findViewById(R.id.editText_genero);
        edt_telefono = findViewById(R.id.editText_telefono);
        registrarse = findViewById(R.id.button_registrarse);



        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = edt_nombre.getText().toString();
                String apellido = edt_apellido.getText().toString();
                String usuario = edt_usuario.getText().toString();
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();
                String genero = edt_genero.getText().toString();
                String telefono = edt_telefono.getText().toString();

                if (!nombre.equals("") && !apellido.equals("") && !usuario.equals("") && !email.equals("") && !password.equals("") && !genero.equals("") && !telefono.equals("")){
                    UserService userService;
                    userService = RetrofitClientInstance.
                            getRetrofitInstance().create(UserService.class);

                    String encode_hash = Utils.encode(password,"16",29000);

                    JsonObject user_json = new JsonObject();
                    user_json.addProperty("username", usuario);
                    user_json.addProperty("name", nombre);
                    user_json.addProperty("surname", apellido);
                    user_json.addProperty("email", email);
                    user_json.addProperty("phone", telefono);
                    user_json.addProperty("genere", genero);
                    user_json.addProperty("password", encode_hash);

                    Call<Void> call = userService.registerUser(user_json);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 200){
                                Toast.makeText(RegistrationActivity.this,"User registered", Toast.LENGTH_SHORT).show();
                            }else{
                                try {
                                    Toast.makeText(RegistrationActivity.this, Objects.requireNonNull(response.errorBody()).string(), Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.d("Registro",t.getMessage());
                        }
                    });
                } else {
                    Toast.makeText(RegistrationActivity.this,"ERROR: Todos los campos tienen que rellenarse", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
