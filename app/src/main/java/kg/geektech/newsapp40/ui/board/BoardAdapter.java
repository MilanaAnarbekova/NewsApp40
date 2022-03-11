package kg.geektech.newsapp40.ui.board;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.ItemBoardBinding;
import kg.geektech.newsapp40.databinding.ItemNewsBinding;
import kg.geektech.newsapp40.ui.NewsAdapter;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {


    private List<ScreenItem> screenItems = new ArrayList<>();
    private NavController navController;

    public BoardAdapter(NavController navController) {
        this.navController = navController;
    }

    public void setList(List<ScreenItem> screenItems) {
        this.screenItems = screenItems;
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBoardBinding binding = ItemBoardBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(screenItems.get(position));
    }

    @Override
    public int getItemCount() {
        return screenItems.size();
    }

    public void setList(ArrayList<ScreenItem> screenList) {
        this.screenItems = screenList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemBoardBinding binding;

        public ViewHolder(@NonNull ItemBoardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    navController.navigateUp();
                }
            });


        }

        public void bind(ScreenItem screenItems) {

            if (getAdapterPosition() == getItemCount() - 1) {
                binding.start.setVisibility(View.VISIBLE);
                binding.lottie.setAnimation(R.raw.job);

            } else if ( getAdapterPosition() == 0) {
                binding.lottie.setAnimation(R.raw.plants);
                binding.start.setVisibility(View.INVISIBLE);
            } else {
                binding.start.setVisibility(View.INVISIBLE);
                binding.lottie.setAnimation(R.raw.popcorn);
            }
            binding.textDes.setText(screenItems.getTextDes());
            binding.textTitle.setText(screenItems.getTitle());
        }


    }

}


