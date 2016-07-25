/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine.app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;

/**
 * A {@link PreferenceActivity} that presents a set of application settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends PreferenceActivity
        implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add 'general' preferences, defined in the XML file
        addPreferencesFromResource(R.xml.pref_general); //No setContentView Here...!

        // For all preferences, attach an OnPreferenceChangeListener so the UI summary can be
        // updated when the preference changes.

        //Like findViewByID:
        Preference locationPreference = findPreference(getString(R.string.pref_location_key));
        //This method takes the values stored in sharedprefs and puts them in the Preference View
        bindPreferenceSummaryToValue(locationPreference);


        Preference unitsPreference = findPreference(getString(R.string.pref_location_key));
        bindPreferenceSummaryToValue(unitsPreference);
    }

    /**
     * Attaches a listener so the summary is always updated with the preference value.
     * Also fires the listener once, to initialize the summary (so it shows up before the value
     * is changed.)
     *
     *
     *  The Preference class Represents the basic Preference UI element
     *  displayed by a {@link PreferenceActivity} in the form of a
     * {@link ListView}. This class provides the {@link View} to be displayed in
     * the activity and associates with a {@link SharedPreferences} to
     * store/retrieve the preference data.
     */
    private void bindPreferenceSummaryToValue(Preference preference) {


        // Set the listener to watch for value changes - this is done per preference so we get notified on all of them.
        preference.setOnPreferenceChangeListener(this);

        SharedPreferences defaultPrefs = PreferenceManager
                .getDefaultSharedPreferences(preference.getContext());

        String prefValueFromSharedPrefs = defaultPrefs.getString(preference.getKey(), "");


        // Trigger the listener immediately with the preference's
        // current value.
        onPreferenceChange(preference,prefValueFromSharedPrefs);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue = value.toString();

        preference.setSummary(stringValue);
        return true;
    }

}