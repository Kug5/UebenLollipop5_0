package com.example.greiser.uebenlollipop5_0;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.greiser.uebenlollipop5_0.SQLite.user.User;
import com.example.greiser.uebenlollipop5_0.SQLite.user.UserDS;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class UserTest {
    private UserDS ds;

    @Before
    public void setUp() {
        ds = new UserDS(InstrumentationRegistry.getTargetContext());
    }

    @After
    public void finish() {

    }

    @Test
    public void createUser() {
        String username = "Heidi" + System.currentTimeMillis();
        long id = ds.addUser(username);
        assertFalse(id == -1);

        User heidi = ds.getUser(username);
        assertEquals(heidi.getUsername(),username );
        assertEquals(heidi.getId(), id );

        User not_exists = ds.getUser(username+"e");
        assertNull(not_exists);
    }

    @Test
    public void notCreateDuplicateUser() {
        String username = "Heidi" + System.currentTimeMillis();
        ds.addUser(username);
        assertEquals(-1, ds.addUser(username));
    }

    @Test
    public void getAllExistingUsernames() {
        ArrayList<String> list = ds.getAllExistingUsernames();
        assertFalse(list == null);

        String username = "Heidi" + System.currentTimeMillis();
        ds.addUser(username);

        list = ds.getAllExistingUsernames();
        for (String tmp : list) {
            if (tmp.equals(username)) {
                assertTrue(true);
                return;
            }
        }
        assertFalse(false);
    }
}
