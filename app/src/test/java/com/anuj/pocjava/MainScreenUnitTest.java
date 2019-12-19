package com.anuj.pocjava;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anuj.pocjava.models.BaseResult;
import com.anuj.pocjava.models.Response;
import com.anuj.pocjava.models.RowsItem;
import com.anuj.pocjava.repository.Repository;
import com.anuj.pocjava.util.Constants;
import com.anuj.pocjava.viewmodel.MainScreenViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MainScreenUnitTest {
    @Rule
    public InstantTaskExecutorRule executorRule = new InstantTaskExecutorRule();

    private MainScreenViewModel service;
    @Before
    public void setup() {
        MutableLiveData<BaseResult<Response>> res = new MutableLiveData<>();
        Repository r = new Repository();
        service = Mockito.spy(new MainScreenViewModel(res, r));
    }

    @After
    public void tearDown() {
        service = null;
    }

    @Test
    public void testGetErrorMessage_Error() {
        assertEquals(Constants.INTERNET_CONNECTIVITY_ERROR, service.getErrorMessage());
    }

    @Test
    public void testIsSuccessfulResponse_NoError() {
        Response response = Mockito.mock(Response.class);
        ArrayList<RowsItem> responseList = new ArrayList<>();
        responseList.add(new RowsItem("", "", ""));
        responseList.add(new RowsItem("", "", ""));
        Mockito.when(response.getRows()).thenReturn(responseList);
        assertTrue(service.isSuccessful(response));
    }

    @Test
    public void testIsSuccessfulResponse_Error() {
        Response response = Mockito.mock(Response.class);
        ArrayList<RowsItem> responseList = null;
        Mockito.when(response.getRows()).thenReturn(responseList);
        assertFalse(service.isSuccessful(response));
    }

    @Test
    public void test_ShouldGetLiveData() {
        LiveData<BaseResult<Response>> data = service.getResult();
        assertNotNull("Live Data", data);
    }

    @Test
    public void test_ShouldFetchResponse() {
        Repository repo = Mockito.mock(Repository.class);
        MutableLiveData<BaseResult<Response>> res = new MutableLiveData<>();
        service = Mockito.spy(new MainScreenViewModel(res, repo));
        Mockito.doNothing().when(repo).getMainScreenList(res);

        boolean fetch = service.getResponse(false);
        assertTrue("Force = false and null data", fetch);
        boolean fetch2 = service.getResponse(true);
        assertTrue("Force = true and null data", fetch2);

        MutableLiveData<BaseResult<Response>> res2 = new MutableLiveData<>();
        res2.setValue(new BaseResult<>());
        service = Mockito.spy(new MainScreenViewModel(res2, repo));

        boolean fetch3 = service.getResponse(false);
        assertFalse("Force = false and null data", fetch3);
        boolean fetch4 = service.getResponse(true);
        assertTrue("Force = true and null data", fetch4);
    }
}