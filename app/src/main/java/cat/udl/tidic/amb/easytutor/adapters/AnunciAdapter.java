package cat.udl.tidic.amb.easytutor.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cat.udl.tidic.amb.easytutor.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import cat.udl.tidic.amb.easytutor.models.Anunci;

public class AnunciAdapter extends ListAdapter <Anunci, AnunciAdapter.AnunciHolder> {

    private OnItemClickListener anunciItemListener;
    private final static String TAG ="AnunciAdapter";

    public AnunciAdapter(@NonNull DiffUtil.ItemCallback<Anunci> diffCallback) {
        super(diffCallback);

    }

    @NonNull
    @Override
    public AnunciHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anunci_list,
                null, false);
        return new AnunciHolder(itemView);
    }


    public void onBindViewHolder(@NonNull AnunciHolder holder, int position) {
        final Anunci anunci = getItem(position);
        Log.d(TAG, "onBindViewHolder() -> anunci: " + anunci);
        holder.textViewTitle.setText(anunci.getTitle());
        holder.textViewLevel.setText(anunci.getLevel().toString());
        float auxPrice = anunci.getPrice();
        String priceFormat = Float.toString(auxPrice) + " €/H";
        holder.textViewPrice.setText(priceFormat);
    }

    class AnunciHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewLevel;
        private TextView textViewPrice;

        public AnunciHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewLevel = itemView.findViewById(R.id.textViewLevel);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    if (anunciItemListener != null && position != RecyclerView.NO_POSITION) {
                        anunciItemListener.onItemClick(getItem(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Anunci anunci);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.anunciItemListener = listener;
    }
}
