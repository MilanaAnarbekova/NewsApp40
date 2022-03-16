package kg.geektech.newsapp40.ui.board;

import android.os.Binder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.prefs.PreferenceChangeEvent;

import kg.geektech.newsapp40.Prefs;
import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.FragmentBoardBinding;
import kg.geektech.newsapp40.databinding.ItemBoardBinding;


public class BoardFragment extends Fragment {
    private FragmentBoardBinding binding;
    private ArrayList<ScreenItem> screenList = new ArrayList<>();
    private ScreenItem itemScreen;
    private static Prefs prefs;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
binding = FragmentBoardBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        prefs = new Prefs(requireActivity());
        loadData();
        ViewPager2 viewPager = view.findViewById(R.id.view_pager);

        NavController navController  = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        BoardAdapter adapter = new BoardAdapter(navController);
        viewPager.setAdapter(adapter);
        itemScreen = new ScreenItem(screenList ,this);
        adapter.setList(screenList);
        binding.dots.setViewPager2(viewPager);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (position == 2){
                    binding.skip.setVisibility(View.INVISIBLE);
                } else{
                    binding.skip.setVisibility(View.VISIBLE);
                }
            }
        });
        binding.skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.saveBoardState();
                navController.navigateUp();
            }
        });


    }

    private void loadData() {
        screenList.add(new ScreenItem(R.raw.cat,"lottie","jkdbkb"));
        screenList.add(new ScreenItem(R.raw.job,"gradle","jkdbkb"));
        screenList.add(new ScreenItem(R.raw.plants,"kfmdk","jkdbkb"));
    }



}