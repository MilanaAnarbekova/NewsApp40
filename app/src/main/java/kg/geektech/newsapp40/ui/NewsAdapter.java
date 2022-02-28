package kg.geektech.newsapp40.ui;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.ItemNewsBinding;
import kg.geektech.newsapp40.models.News;
import kg.geektech.newsapp40.ui.home.OnClickListener;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> list;
    private OnClickListener listener;

    public NewsAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(News news) {
        list.add(0, news);
        notifyItemInserted(0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemNewsBinding binding;

        public ViewHolder(@NonNull ItemNewsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;


        }

        public void bind(News news) {
            if (getAdapterPosition() %2 == 0){
                itemView.setBackgroundColor(Color.GRAY);
            }
            SimpleDateFormat sdf = new SimpleDateFormat(  "HH:mm:ss"+" - " + ".dd.yy" );
            String a = sdf.format(news.getCreatedAt());
            binding.textTitle.setText(news.getTitle());
            binding.time.setText(a);
        }
    }
}
