package com.tolmachevroman.paymentapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.tolmachevroman.paymentapp.utils.Utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by romantolmachev on 13/12/2017.
 */

@RunWith(JUnit4.class)
public class UtilsTest {

    @Test
    public void hasConnection() {

        Context context = mock(Context.class);
        ConnectivityManager connManager = mock(ConnectivityManager.class);
        NetworkInfo networkInfo = mock(NetworkInfo.class);
        PackageManager packageManager = mock(PackageManager.class);

        when(context.getPackageManager()).thenReturn(packageManager);
        when(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connManager);
        when(connManager.getActiveNetworkInfo()).thenReturn(networkInfo);
        when(networkInfo.isAvailable()).thenReturn(true);
        when(networkInfo.isConnected()).thenReturn(true);
        assertTrue(Utils.hasConnection(context));

        when(networkInfo.isConnected()).thenReturn(false);
        assertFalse(Utils.hasConnection(context));
    }

    @Test
    public void convertStreamToString() {

        InputStream anyInputStream = new ByteArrayInputStream("test".getBytes());
        assertEquals(Utils.convertStreamToString(anyInputStream), "test");
    }
}
