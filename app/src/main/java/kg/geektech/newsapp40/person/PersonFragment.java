package kg.geektech.newsapp40.person;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.FragmentNewsBinding;
import kg.geektech.newsapp40.databinding.FragmentPersonBinding;


public class PersonFragment extends Fragment {
    private FragmentPersonBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPersonBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        goImage();
    }

    private void goImage() {
        ActivityResultLauncher <String> image = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                binding.imagePerson.setImageURI(uri);
                
            }
        });
        binding.imagePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.launch("image/*");
            }
        });
        
    }
}