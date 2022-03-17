package kg.geektech.newsapp40;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import kg.geektech.newsapp40.databinding.FragmentNewsBinding;
import kg.geektech.newsapp40.databinding.FragmentNotificationsBinding;
import kg.geektech.newsapp40.models.News;


public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
         return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text =binding.editText.getText().toString();
                if(TextUtils.isEmpty(text)){
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .playOn(binding.editText);
                    binding.editText.setError("Input text");
                }else{
                    save();
                    binding.progressBar.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    private void save() {
        String text = binding.editText.getText().toString();
        News news = new News(text , System.currentTimeMillis() , "Description");
        saveToFirestore(news);
        Bundle bundle  = new Bundle();
        bundle.putSerializable("news",news);
        getParentFragmentManager().setFragmentResult("rk_news" , bundle);
        App.dataBase.newsDao().insertNews(news);

    }

    private void saveToFirestore(News news) {
        FirebaseFirestore.getInstance()
                .collection("news")
                .add(news)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(requireContext(), "Успешно", Toast.LENGTH_SHORT).show();
                            close();

                        } else{
                            Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void close() {
        NavController navController = Navigation.findNavController
                (requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}