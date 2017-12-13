package com.tolmachevroman.paymentapp;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.tolmachevroman.paymentapp.datasources.WebService;
import com.tolmachevroman.paymentapp.models.paymentmethods.PaymentMethod;
import com.tolmachevroman.paymentapp.utils.Utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by romantolmachev on 13/12/2017.
 */

@RunWith(JUnit4.class)
public class WebServiceTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    private WebService webService;
    private MockWebServer mockWebServer;

    @Before
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        webService = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build()
                .create(WebService.class);
    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void getPaymentMethods() throws IOException {

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("payment_methods.json");
        String content = Utils.convertStreamToString(in);

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(content));

        Response<List<PaymentMethod>> response = webService.getPaymentMethods(BuildConfig.PUBLIC_KEY).execute();
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
        assertEquals((response.body()).get(0).getName(), "Visa");
    }
}
