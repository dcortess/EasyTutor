package cat.udl.tidic.amb.easytutor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
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
}
