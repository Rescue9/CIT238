package com.example.rescue.chapter5preferencefragmentexample;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Author: rescue
 * Date: 2/22/18
 * Class: CIT 238 (Android)
 * Purpose:
 */

public class Fragment1 extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//---load the preferences from an XML file---
        addPreferencesFromResource(R.xml.preferences);
    }
}