package kg.geektech.newsapp40.phone;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.FragmentPhoneBinding;
import kg.geektech.newsapp40.ui.home.HomeViewModel;

public class PhoneFragment extends Fragment {
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    FirebaseAuth mAuth;
    private FragmentPhoneBinding binding;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhoneBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        createCallBack();

        binding.btnContinue.setOnClickListener(v -> {
            String phone = binding.etNum.getText().toString().trim();
            if (TextUtils.isEmpty(phone)) {
                binding.etNum.setError("Error Phone");
            }else{
                register(phone);
                binding.btnContinue.setVisibility(View.INVISIBLE);
                binding.etNum.setVisibility(View.INVISIBLE);
                binding.tvInputNum.setVisibility(View.INVISIBLE);
                binding.etCode.setVisibility(View.VISIBLE);
                binding.btnCode.setVisibility(View.VISIBLE);
            }

        });
    }


    private void init() {
        mAuth = FirebaseAuth.getInstance();

    }


    private void register(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(requireActivity())                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    public void createCallBack(){
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

                Log.d("TAG", "onVerificationCompleted:" + credential);

                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                e.printStackTrace();
                Log.e("TAG", "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {


                } else if (e instanceof FirebaseTooManyRequestsException) {

                }


            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Log.d("TAG", "onCodeSent:" + verificationId);
                binding.btnCode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initCode(verificationId);
                    }
                });
            }
        };
    }

    private void initCode(String verificationId) {
        String code = binding.etCode.getText().toString().trim();

        if (TextUtils.isEmpty(code)) {
            binding.etNum.setError("Error Code");
        }
        else {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
            signInWithPhoneAuthCredential(credential);
        }

    }



    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                          Navigation.findNavController(requireView()).navigate(R.id.boardFragment);
                            FirebaseUser user = task.getResult().getUser();
                        } else {
                            binding.etCode.setError("error code");
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            }
                        }
                    }
                });
    }

} 