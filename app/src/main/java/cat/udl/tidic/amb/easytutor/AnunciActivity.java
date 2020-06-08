package cat.udl.tidic.amb.easytutor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import cat.udl.tidic.amb.easytutor.models.Anunci;
import cat.udl.tidic.amb.easytutor.viewmodel.AnunciViewModel;

public class AnunciActivity extends AppCompatActivity {

    private String id;
    protected AnunciViewModel anunciViewModel;
    private TextView textViewTitle;
    private TextView textViewDescription;
    private TextView textViewPrice;
    private TextView textViewLevel;
    private TextView textViewDsitance;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        this.id = intent.getStringExtra("id");

        textViewTitle=findViewById(R.id.textViewTitleA);
        textViewDescription=findViewById(R.id.textViewDescriprion);
        textViewPrice=findViewById(R.id.textViewPriceA);
        textViewLevel=findViewById(R.id.textViewLevelA);
        textViewDsitance=findViewById(R.id.textViewDistance);

        anunciViewModel.getAnunci(id);
        anunciViewModel.getResponseLiveDataAnunci().observe(this, new Observer<Anunci>() {
            @Override
            public void onChanged(Anunci anunci) {
                textViewTitle.setText(anunci.getTitle());
                textViewDescription.setText(anunci.getDescription());
                textViewDsitance.setText(Integer.toString(anunci.getDistance_to_serve()));

                if(anunci.isAdmits_negotation()){
                    textViewPrice.setText(Float.toString(anunci.getPrice())+" €/H. Precio negociable");
                }else{
                    textViewPrice.setText(Float.toString(anunci.getPrice())+" €/H. Precio no negociable");
                }
                switch (anunci.getLevel()){
                    case P: textViewLevel.setText("Primaria"); break;
                    case E: textViewLevel.setText("ESO"); break;
                    case B: textViewLevel.setText("Bachillerato"); break;
                    case M: textViewLevel.setText("Grado Medio"); break;
                    case S: textViewLevel.setText("Grado Superior"); break;
                    case U: textViewLevel.setText("Universidad"); break;
                }
            }
        });
    }


}
