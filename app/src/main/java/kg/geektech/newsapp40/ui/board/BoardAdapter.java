package kg.geektech.newsapp40.ui.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.ui.NewsAdapter;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

private String[] titles = new String[] {"Hello", "Привет", "Hi"};


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board ,
                 parent,false);
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_title);
        }

        public void bind(int position) {
            textTitle.setText(titles[position]);
        }
    }
}
