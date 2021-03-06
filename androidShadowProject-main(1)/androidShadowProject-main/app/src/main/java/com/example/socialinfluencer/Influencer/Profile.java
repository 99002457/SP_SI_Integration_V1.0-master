package com.example.socialinfluencer.Influencer;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialinfluencer.DataModels.InfluencerProfileData;
import com.example.socialinfluencer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {
//    ArrayList<String> title= new ArrayList<>(Arrays.asList("WoodEssence","Good Looks","Canine Crew","Peak Sports","Dream Shades","Creative Tree"));
//    ArrayList<String> description= new ArrayList<>(Arrays.asList("Shop the luxury furniture you need","Fashion friendly clothes for fashion enthusiasts","For wagging tails and more","When you feel down and out choose to play a sport","Beauty is Whatever Brings Perfect","Adding Creativity Through Brush"));
//    ArrayList<String> category= new ArrayList<>(Arrays.asList("Furniture","Clothing","Pets","Sports","Cosmetics","Arts"));
//    // ArrayList<String> endDate= new ArrayList<>(Arrays.asList("20-12-20","16-12-20","18-12-20","20-12-20","15-12-20","28-12-20"));
//    ArrayList<String> endDate= new ArrayList<>(Arrays.asList("Running","Past","Running","Past","Running","past"));
//    Integer[] ar={R.drawable.fur1,R.drawable.cloth4,R.drawable.pet1,R.drawable.sport3,R.drawable.cos3,R.drawable.art1};
    InfluencerProfileData Influencer;
    FirebaseAuth fAuth;
    String UserID;
    DatabaseReference ref;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //        u_name u_gender u_category u_price u_totalearning
        //instagramFollowing  instagramFollower instagramPosts InstaEngagement_percent
        //ytsubscribers, ytwatchhours ytposts yrimpressions
        //facefollowing facefollowers faceposts faceEngaement imageView
        fAuth = FirebaseAuth.getInstance();
        UserID=fAuth.getCurrentUser().getUid();
        Toast.makeText(getContext(),UserID,Toast.LENGTH_LONG).show();
        ref= FirebaseDatabase.getInstance().getReference("Influencer").child(UserID);
//        u_gender  u_name u_gender u_category u_price u_totalearning
        //instagramFollowing  instagramFollower instagramPosts InstaEngagement_percent
        //ytsubscribers, ytwatchhours ytposts yrimpressions
        //facefollowing facefollowers faceposts faceEngaement imageView

        TextView name=view.findViewById(R.id.uname);
        TextView gender=view.findViewById(R.id.u_gender);
        TextView category=view.findViewById(R.id.u_category);
        TextView price=view.findViewById(R.id.u_price);
        TextView totalEarnings=view.findViewById(R.id.u_totalearning);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild("Profile"))
                {
                    Influencer=snapshot.child("Profile").getValue(InfluencerProfileData.class);
                    name.setText(Influencer.getInfluncer_Name());
                }
                else
                {
                    loadFragment(new CreateProfile());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//

//        List<String> Cat=Influencer.getCategories();
//        gender.setText(Influencer.getInflucencer_Gender());
//        category.setText(Cat.get(0)+" "+Cat.get(1));
//        price.setText(String.valueOf(Influencer.getInflucencer_Price()));
//        totalEarnings.setText(String.valueOf(Influencer.getTotal_Earnings()));
//
//        TextView InstaFollowing=view.findViewById(R.id.instagramFollowing);
//        TextView InstaFollower=view.findViewById(R.id.instagramFollower);
//        TextView InstaPosts=view.findViewById(R.id.instagramPosts);
//        TextView InstaEngage=view.findViewById(R.id.InstaEngagement_percent);
//
//        TextView FaceFollowing=view.findViewById(R.id.facefollowing);
//        TextView FaceFollower=view.findViewById(R.id.facefollowers);
//        TextView FacePosts=view.findViewById(R.id.faceposts);
//        TextView FaceEngage=view.findViewById(R.id.faceEngaement);
//
//        TextView ytSubscribers=view.findViewById(R.id.ytsubscribers);
//        TextView ytWatchhours=view.findViewById(R.id.ytwatchhours);
//        TextView ytPosts=view.findViewById(R.id.ytposts);
//        TextView ytImpressions=view.findViewById(R.id.yrimpressions);
//
//        InstaFollowing.setText(String.valueOf(Influencer.getInstagram().getFollowing()));
//        InstaFollower.setText(String.valueOf(Influencer.getInstagram().getFollowers()));
//        InstaPosts.setText(String.valueOf(Influencer.getInstagram().getPosts()));
//        InstaEngage.setText(Influencer.getInstagram().getEngagement());
//
//        FaceFollowing.setText(String.valueOf(Influencer.getFacebook().getFollowing()));
//        FaceFollower.setText(String.valueOf(Influencer.getFacebook().getFollowers()));
//        FacePosts.setText(String.valueOf(Influencer.getFacebook().getPosts()));
//        FaceEngage.setText(Influencer.getFacebook().getEngagement());
//
//        ytSubscribers.setText(String.valueOf(Influencer.getYoutube().getSubscribers()));
//        ytWatchhours.setText(String.valueOf(Influencer.getYoutube().getImpressions()));
//        ytPosts.setText(String.valueOf(Influencer.getYoutube().getWatchHours()));
//        ytImpressions.setText(String.valueOf(Influencer.getYoutube().getPosts()));

//        RecyclerView campaignList=view.findViewById(R.id.recyclerView);
//
//        LinearLayoutManager linearLayoutManager= new LinearLayoutManager((getContext()));
//        linearLayoutManager.setAutoMeasureEnabled(true);

//        campaignList.setLayoutManager(linearLayoutManager);
//        campaignList.setNestedScrollingEnabled(false);
//        customAdapter customeAdapter =new customAdapter(title,description,category,endDate,ar,getContext());
//        campaignList.setAdapter(customeAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    private void loadFragment(Fragment fragment)
    {
        //create a fragment manager
        FragmentManager manager;
        manager = getParentFragmentManager();
        //create the fragment transaction
        FragmentTransaction fd=manager.beginTransaction();
        fd.replace(R.id.frame,fragment);
        fd.commit();

    }
}