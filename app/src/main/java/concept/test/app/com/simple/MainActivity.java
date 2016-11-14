package concept.test.app.com.simple;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;


    public static TextView txt_all_signals_log;

    public static TextView txt_single_signals_log;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //txt_all_signals_log = (TextView) findViewById(R.id.logAllSignals);
        //txt_single_signals_log = (TextView) findViewById(R.id.logSingleSignal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoYo.with(Techniques.Pulse)
                        .duration(2000)
                        .playOn(view);
                AllSignalsFragment.txt_all_signals_log.setText("");
                Snackbar.make(view, "Deleting Log", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Snackbar.make(findViewById(R.id.fab), "Something Great ^^!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            YoYo.with(Techniques.DropOut)
                    .duration(2000)
                    .playOn(findViewById(R.id.main_content));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    //public static class PlaceholderFragment

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            FragmentEnum fragmentEnum = FragmentEnum.getFragment(position);
            Fragment fragment = null;

            Bundle args = new Bundle();
            switch (fragmentEnum){
                case ALL_SIGNALS_FRAGMENT:
                    fragment = new AllSignalsFragment();
                    args.putInt("section_number", 0);
                    fragment.setArguments(args);
                    break;
                case SINGLE_SIGNAL_FRAGMENT:
                    fragment = new SingleSignalFragment();

                    args.putInt("section_number", 1);
                    fragment.setArguments(args);
                    break;
            }
            return fragment;

            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "All Signals";
                case 1:
                    return "Last Signal";
            }
            return null;
        }
    }

    public enum FragmentEnum {
        ALL_SIGNALS_FRAGMENT,SINGLE_SIGNAL_FRAGMENT;

        public static FragmentEnum getFragment(int _position){
            FragmentEnum result = null;
            switch (_position){
                case 0:
                    result = ALL_SIGNALS_FRAGMENT;
                    break;

                case 1:
                    result = SINGLE_SIGNAL_FRAGMENT;
                    break;
            }

            return result;
        }
    }
}