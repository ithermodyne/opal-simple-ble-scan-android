package concept.test.app.com.simple;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Edgard Rendon on 13/11/2016.
 */

public class AllSignalsFragment extends Fragment {

    public static TextView txt_all_signals_log;
    public static ScrollView scrollView;

    public AllSignalsFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AllSignalsFragment newInstance(int sectionNumber) {
        AllSignalsFragment fragment = new AllSignalsFragment();
        /*
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        */
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_signals, container, false);

        txt_all_signals_log = (TextView) rootView.findViewById(R.id.logAllSignals);
        scrollView = (ScrollView) rootView.findViewById(R.id.scrollView);

        Intent i = new Intent(getActivity(), GetSignalsServices.class);
        getActivity().startService(i);
        return rootView;
    }

}
