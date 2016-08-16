package com.teravision.simple.appfragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.teravision.simple.R;

/**
 * Created by Sara Villarreal on 7/25/2016.
 */
public class SettingsActivity extends PreferenceActivity {
    static final String ON_LOST_TIMEOUT_SECS_KEY = "onLostTimeoutSecs";
    static final String SHOW_DEBUG_INFO_KEY = "showDebugInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new SettingsFragment()).commit();
    }

    public static class SettingsFragment extends PreferenceFragment
            implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
            onSharedPreferenceChanged(getPreferenceScreen().getSharedPreferences(),
                    ON_LOST_TIMEOUT_SECS_KEY);
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals(ON_LOST_TIMEOUT_SECS_KEY)) {
                Preference onLostPref = findPreference(key);
                int val = sharedPreferences.getInt(key, 5);
                String unit = val == 1 ? " second" : " seconds";
                String summary = String.format("Remove from the list devices that haven't been sighted in "
                        + "%d %s. 0 means never remove.", sharedPreferences.getInt(key, 5), unit);
                onLostPref.setSummary(summary);
            }
        }
    }
}
