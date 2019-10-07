package com.example.greiser.uebenlollipop5_0;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.greiser.uebenlollipop5_0.SQLite.math.category.CategoryDS;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.OPERATION_SIGN_DIVIDE;
import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.OPERATION_SIGN_MINUS;
import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.OPERATION_SIGN_MULT;
import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.OPERATION_SIGN_PLUS;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class CategoryTest {
    private CategoryDS ds;

    @Before
    public void setUp() {
        ds = new CategoryDS(InstrumentationRegistry.getTargetContext());
    }

    @After
    public void finish() {

    }

    @Test
    public void getOperation() {
        assertEquals(OPERATION_SIGN_PLUS, ds.getOperation(1));
        assertEquals(OPERATION_SIGN_MINUS, ds.getOperation(2));
        assertEquals(OPERATION_SIGN_MULT, ds.getOperation(3));
        assertEquals(OPERATION_SIGN_DIVIDE, ds.getOperation(4));

        assertNull(ds.getOperation(0));
    }


    @Test
    public void getID() {
        assertEquals(1, ds.getID(OPERATION_SIGN_PLUS));
        assertEquals(2, ds.getID(OPERATION_SIGN_MINUS));
        assertEquals(3, ds.getID(OPERATION_SIGN_MULT));
        assertEquals(4, ds.getID(OPERATION_SIGN_DIVIDE));

        assertEquals(-1, ds.getID("."));
    }
}
