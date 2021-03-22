package com.android.social;

import com.android.social.entity.AccountEntity;
import com.android.social.interfacetest.SearchViewInterface;
import com.android.social.presentertest.SearchPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class SearchPresenterUnitTest {

    SearchPresenter searchPresenter;

    @Mock
    SearchViewInterface searchViewInterface;

    @Before
    public void init() throws Exception {
        //
        searchPresenter = new SearchPresenter(searchViewInterface);
    }

    @Test
    public void testSearchAccountEntityArrayList() {
        //
        ArrayList<AccountEntity> accountEntityArrayList = new ArrayList<>();
        accountEntityArrayList.add(new AccountEntity("Mohammed"));
        accountEntityArrayList.add(new AccountEntity("Ahmad"));
        accountEntityArrayList.add(new AccountEntity("Mahmoud"));
        accountEntityArrayList.add(new AccountEntity("Khaled"));
        searchPresenter.onSearchAccountEntityArrayList(accountEntityArrayList);
        Mockito.verify(searchViewInterface).onUpdateAccountEntityArrayList(accountEntityArrayList);
    }
}
