package cat.udl.tidic.amb.easytutor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;

import cat.udl.tidic.amb.easytutor.models.User;
import cat.udl.tidic.amb.easytutor.preferences.PreferencesProvider;
import cat.udl.tidic.amb.easytutor.viewmodel.UserViewModel;

public class ProfileActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private SharedPreferences mPreferences;
    private TextView name;
    private TextView username;
    private TextView email;
    private TextView phone;
    private TextView gender;
    private String TAG ="ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.mPreferences = PreferencesProvider.providePreferences();
        userViewModel = new UserViewModel(getApplication());

        name = findViewById(R.id.textView20);
        username = findViewById(R.id.textView21);
        email = findViewById(R.id.textView22);
        phone = findViewById(R.id.textView24);
        gender = findViewById(R.id.textView23);


        //Falta fer la petició
        userViewModel.getProfileUser();

        userViewModel.getResponseLiveDataUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User u) {
                Log.d(TAG, "Usuari: " + u.toString());

                String completeName = u.getName() + " " + u.getSurname();
                name.setText(completeName);
                username.setText(u.getUsername());
                email.setText(u.getEmail());
                phone.setText(u.getPhone());
                gender.setText(u.getGender());

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemLogout:
                userViewModel.deleteTokenUser();
                this.mPreferences.edit().remove("token").apply();
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // @JordiMateoUdl -> El token esta guardat, per tant ja no s'ha de tornar a login/registre, fins
    // que l'usuari decideixi tancar la sessió. //Quan modifiqueu la vista principal, això també s'ha de moure!!!!!
    @Override
    public void onBackPressed(){
        Log.d(TAG, "BackPressed -> Good bye, but we not ask log again when return!");
        ActivityCompat.finishAffinity(this);
    }
}
