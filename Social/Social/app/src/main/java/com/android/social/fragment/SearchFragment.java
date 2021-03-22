package com.android.social.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.social.R;
import com.android.social.adapter.ActivityAdapter;
import com.android.social.adapter.HomeAdapter;
import com.android.social.adapter.SearchAdapter;
import com.android.social.entity.AccountEntity;
import com.android.social.entity.PostEntity;
import com.android.social.interfacetest.SearchViewInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements SearchViewInterface {

    RecyclerView recyclerView_search;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<AccountEntity> accountEntityArrayList;
    SearchAdapter searchAdapter;
    FirebaseFirestore firebaseFirestore;
    EditText editText_search_search;
    public SearchFragment() {
        //
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        //
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView_search = view.findViewById(R.id.recyclerView_search);
        recyclerView_search.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView_search.setLayoutManager(layoutManager);
        accountEntityArrayList = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Account").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //
                if (task.isSuccessful()) {
                    //
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                        //
                        accountEntityArrayList.add(queryDocumentSnapshot.toObject(AccountEntity.class));
                    }
                    searchAdapter = new SearchAdapter(accountEntityArrayList, getActivity(), firebaseFirestore);
                    recyclerView_search.setAdapter(searchAdapter);
                } else {
                    //
                }
            }
        });
        editText_search_search = view.findViewById(R.id.editText_search_search);
        editText_search_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
                ArrayList<AccountEntity> accountEntities = new ArrayList<>();
                for(AccountEntity accountEntity: accountEntityArrayList){
                    if(accountEntity.getUsername().contains(s.toString())){
                        accountEntities.add(accountEntity);
                    }
                }
                searchAdapter.onSearch(accountEntities);
            }
        });
    }

    @Override
    public void onUpdateAccountEntityArrayList(ArrayList<AccountEntity> accountEntityArrayList) {
        //
        searchAdapter.onSearch(accountEntityArrayList);
    }
}