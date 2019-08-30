package com.example.greiser.uebenlollipop5_0.helper;

import java.io.File;

public interface Storage {

    Object load() throws Exception;

    File getFile();

    void update(Object newValue);
}
