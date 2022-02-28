package kg.geektech.newsapp40.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.models.News;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private ArrayList<News> list;

    public NewsAdapter(){
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news , parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(News news){
        list.add(0,news);
        notifyItemInserted(0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle= itemView.findViewById(R.id.text_title);
        }

        public void bind(News news) {
            textTitle.setText(news.getTitle());
        }
    }
}
