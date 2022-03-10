package kg.geektech.newsapp40.person;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import kg.geektech.newsapp40.Prefs;
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
        Prefs prefs = new Prefs(getContext());
        Glide.with(this).load(prefs.getImage()).circleCrop().into(binding.imagePerson);
        putPhoto(prefs);
        goImage();
    }

    private void putPhoto(Prefs prefs) {
        binding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                prefs.saveNameState(s.toString());
            }
        });
        binding.editText.setText(prefs.getNameSate());
        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.DeleteAll();
                binding.editText.setText("");
            }
        });
    }

    private void goImage() {
        ActivityResultLauncher<String> image = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                Prefs prefs = new Prefs(getContext());
                binding.imagePerson.setImageURI(uri);
                prefs.saveImage(uri);

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