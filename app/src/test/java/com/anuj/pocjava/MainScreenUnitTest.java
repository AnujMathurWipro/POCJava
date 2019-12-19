package com.anuj.pocjava;

import com.anuj.pocjava.models.Response;
import com.anuj.pocjava.models.RowsItem;
import com.anuj.pocjava.util.Constants;
import com.anuj.pocjava.viewmodel.MainScreenViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MainScreenUnitTest {

    private MainScreenViewModel service;
    @Before
    public void setup() {
        service = Mockito.spy(MainScreenViewModel.class);
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
}