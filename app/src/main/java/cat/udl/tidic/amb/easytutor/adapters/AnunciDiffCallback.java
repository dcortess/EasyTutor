package cat.udl.tidic.amb.easytutor.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import cat.udl.tidic.amb.easytutor.models.Anunci;

public class AnunciDiffCallback extends DiffUtil.ItemCallback<Anunci> {

@Override
public boolean areItemsTheSame(@NonNull Anunci oldItem, @NonNull Anunci newItem) {
        return oldItem.getId().equals(newItem.getId());
        }

@Override
public boolean areContentsTheSame(@NonNull Anunci oldItem, @NonNull Anunci newItem) {
        return oldItem.equals(newItem);
        }
}
