package cat.udl.tidic.amb.easytutor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.Objects;
import cat.udl.tidic.amb.easytutor.network.RetrofitClientInstance;
import cat.udl.tidic.amb.easytutor.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddOfferActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{String[] levels = {"Primaria", "ESO", "Bachillerato", "Grado Medio", "Grado Superior", "Universidad"};

    EditText edt_title;
    TextInputEditText edt_description;
    EditText edt_price;
    Button post;
    public String level;
    boolean offer_posted;
    Spinner spin;
    RadioButton yes_negotiation;
    RadioButton no_negotiation;
    EditText distance_to_serve;
    RadioButton doy_clases;
    RadioButton busco_clases;


    @Override
    public void onItemSelected (AdapterView < ? > arg0, View arg1,int position, long id){
        if(levels[position].equals("Primaria")){
            level = "P";
        } else if(levels[position].equals("ESO")){
            level = "E";
        } else if(levels[position].equals("Bachillerato")){
            level = "B";
        } else if(levels[position].equals("Grado Medio")){
            level = "M";
        } else if(levels[position].equals("Grado Superior")){
            level = "S";
        } else if(levels[position].equals("Universidad")){
            level = "U";
        }
    }
    @Override
    public void onNothingSelected (AdapterView < ? > arg0){
        // TODO Auto-generated method stub
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer);


        spin = findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, levels);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);


        edt_title = findViewById(R.id.editText_title);
        edt_description = findViewById(R.id.textInput_description);
        edt_price = findViewById(R.id.editText_price);
        post = findViewById(R.id.button_post);
        yes_negotiation = findViewById(R.id.radioButton_yes);
        no_negotiation = findViewById(R.id.radioButton_no);
        distance_to_serve = findViewById(R.id.editText_distance);
        doy_clases = findViewById(R.id.radioButton_doy);
        busco_clases = findViewById(R.id.radioButton_busco);


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String title = edt_title.getText().toString();
                String description = edt_description.getText().toString();
                Float price = Float.parseFloat(edt_price.getText().toString());
                String send_level = level;
                Boolean admits_negotiation = false;
                Integer distance = Integer.parseInt(distance_to_serve.getText().toString());
                String type = "";

                if(yes_negotiation.isChecked()){
                    admits_negotiation = true;
                } else if(no_negotiation.isChecked()){
                    admits_negotiation = false;
                }

                if(doy_clases.isChecked()){
                    type = "D";
                } else if(busco_clases.isChecked()){
                    type = "B";
                }

                UserService userService;
                userService = RetrofitClientInstance.
                        getRetrofitInstance().create(UserService.class);


                JsonObject user_json = new JsonObject();
                user_json.addProperty("title", title);
                user_json.addProperty("description", description);
                user_json.addProperty("price", price);
                user_json.addProperty("level", send_level);
                user_json.addProperty("admits_negotiation", admits_negotiation);
                user_json.addProperty("distance_to_serve", distance);
                user_json.addProperty("type", type);

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
