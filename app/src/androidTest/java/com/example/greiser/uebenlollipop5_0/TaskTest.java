package com.example.greiser.uebenlollipop5_0;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.greiser.uebenlollipop5_0.SQLite.math.task.TaskDS;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.OPERATION_SIGN_MULT;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class TaskTest {
    private TaskDS ds;

    @Before
    public void setUp() {
        ds = new TaskDS(InstrumentationRegistry.getTargetContext());
    }

    @After
    public void finish() {

    }

    @Test
    public void getTasksByOperation() {
        ds.getTaskByCategory(OPERATION_SIGN_MULT);
    }
}
