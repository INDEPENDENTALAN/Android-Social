package com.android.social.presentertest;

import com.android.social.entity.AccountEntity;
import com.android.social.interfacetest.SearchPresenterInterface;
import com.android.social.interfacetest.SearchViewInterface;

import java.util.ArrayList;

public class SearchPresenter implements SearchPresenterInterface {

    private SearchViewInterface searchViewInterface;

    public SearchPresenter(SearchViewInterface searchViewInterface) {
        //
        this.searchViewInterface = searchViewInterface;
    }
    @Override
    public void onSearchAccountEntityArrayList(ArrayList<AccountEntity> accountEntityArrayList) {
        //
        String s = "Kh";
        ArrayList<AccountEntity> accountEntities = new ArrayList<>();
        for(AccountEntity accountEntity: accountEntityArrayList){
            if(accountEntity.getUsername().contains(s)){
                accountEntities.add(accountEntity);
            }
        }
        searchViewInterface.onUpdateAccountEntityArrayList(accountEntities);
    }
}
