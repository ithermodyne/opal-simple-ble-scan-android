package concept.test.app.com.simple;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Edgard Rendon on 13/11/2016.
 */

public class SingleSignalFragment extends Fragment {

    public static TextView txt_single_signal_log;

    public SingleSignalFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static SingleSignalFragment newInstance(int sectionNumber) {
        SingleSignalFragment fragment = new SingleSignalFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_single_signals, container, false);
        txt_single_signal_log = (TextView) rootView.findViewById(R.id.logSingleSignal);
        return rootView;
    }
}