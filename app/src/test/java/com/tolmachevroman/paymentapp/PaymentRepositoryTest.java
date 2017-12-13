package com.tolmachevroman.paymentapp;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import com.tolmachevroman.paymentapp.datasources.Resource;
import com.tolmachevroman.paymentapp.datasources.WebService;
import com.tolmachevroman.paymentapp.models.paymentmethods.PaymentMethod;
import com.tolmachevroman.paymentapp.models.paymentmethods.PaymentMethodsRepository;
import com.tolmachevroman.paymentapp.utils.Utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by romantolmachev on 13/12/2017.
 */

@RunWith(JUnit4.class)
public class PaymentRepositoryTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    private MockWebServer mockWebServer;
    private PaymentMethodsRepository repository;
    private Utils utils;

    @Mock
    private Observer<Resource<List<PaymentMethod>>> observer;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        WebService webService = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build()
                .create(WebService.class);

        utils = mock(Utils.class);
        repository = new PaymentMethodsRepository(webService, utils);
    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void shouldReturnResourceErrorOn404() {

        when(utils.hasConnection()).thenReturn(true);

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(404));

        repository.getPaymentMethods().observeForever(observer);

        verify(observer, timeout(1000)).onChanged(argThat(new Error404NotFoundMatcher<List<PaymentMethod>>()));
    }

    @Test
    public void shouldReturnResourceSuccess() {


        InputStream in = this.getClass().getClassLoader().getResourceAsStream("payment_methods.json");
        String content = Utils.convertStreamToString(in);

        when(utils.hasConnection()).thenReturn(true);

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(content));

        repository.getPaymentMethods().observeForever(observer);

        verify(observer, timeout(1000)).onChanged(argThat(new SuccessMatcher<List<PaymentMethod>>()));
    }



    class SuccessMatcher<T> implements ArgumentMatcher<Resource<T>> {
        @Override
        public boolean matches(Resource<T> argument) {
            if(argument != null) {
                return (argument.status == Resource.Status.SUCCESS && argument.data != null);
            } else return false;
        }
    }

    class Error404NotFoundMatcher<T> implements ArgumentMatcher<Resource<T>> {
        @Override
        public boolean matches(Resource<T> argument) {
            if (argument != null && argument.error != null) {
                return (argument.status == Resource.Status.ERROR && argument.error.getCode() == 404);
            } else return false;
        }
    }
}
