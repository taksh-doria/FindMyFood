package com.maverick.findmyfood.appmain;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.maverick.findmyfood.MainActivity;
import com.maverick.findmyfood.R;
import com.maverick.findmyfood.utility.Authentication;

import de.hdodenhof.circleimageview.CircleImageView;


public class AccountFragment extends Fragment {
    View root;
    private CircleImageView profile_image;
    private TextView display_name;
    private TextView email;
    private Button signout;
    FirebaseAuth mauth;
    GoogleSignInClient signInClient;
    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user=mauth.getCurrentUser();
        if (user!=null)
        {
            Glide.with(root.getContext()).load(user.getPhotoUrl()).into(profile_image);
            display_name.setText(user.getDisplayName());
            email.setText(user.getEmail());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root=inflater.inflate(R.layout.fragment_account, container, false);
        signout=(Button)root.findViewById(R.id.signout);
        profile_image=(CircleImageView) root.findViewById(R.id.profile_pic);
        display_name=(TextView)root.findViewById(R.id.username);
        email=(TextView)root.findViewById(R.id.email);
        mauth=FirebaseAuth.getInstance();
        signInClient= Authentication.requestService(root.getContext());
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                GoogleSignInClient client = Authentication.requestService(root.getContext());
                client.signOut().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(root.getContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Removes other Activities from stack
                        startActivity(intent);
                    }
                });
            }
        });
        return root;
    }
}