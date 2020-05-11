package cat.udl.tidic.amb.easytutor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.Objects;
import cat.udl.tidic.amb.easytutor.network.RetrofitClientInstance;
import cat.udl.tidic.amb.easytutor.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddOfferActivity extends AppCompatActivity {

    EditText edt_title;
    EditText edt_description;
    EditText edt_price;
    Button post;
    boolean offer_posted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer);

        edt_title = findViewById(R.id.editText_title);
        edt_description = findViewById(R.id.editText_description);
        edt_price = findViewById(R.id.editText_price);
        post = findViewById(R.id.button_post);


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String title = edt_title.getText().toString();
                String description = edt_description.getText().toString();
                Long price = Long.parseLong(edt_price.getText().toString());

                UserService userService;
                userService = RetrofitClientInstance.
                        getRetrofitInstance().create(UserService.class);


                JsonObject user_json = new JsonObject();
                user_json.addProperty("title", title);
                user_json.addProperty("description", description);
                user_json.addProperty("price", price);

                Call<Void> call = userService.registerUser(user_json);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            Toast.makeText(AddOfferActivity.this, "Anuncio publicado correctamente", Toast.LENGTH_SHORT).show();
                            offer_posted=true;
                            Intent intent = new Intent (v.getContext(), ProfileActivity.class); //Canviar a HomeActivity quan existeixi
                            startActivityForResult(intent, 0);
                        } else {
                            try {
                                Toast.makeText(AddOfferActivity.this, Objects.requireNonNull(response.errorBody()).string(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("Registro", t.getMessage());
                    }
                });
            }
        });
    }
}
