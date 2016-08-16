package com.teravision.simple.appfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

/**
 * Created by Sara Villarreal on 7/19/2016.
 */
public abstract class ApplicationFragment extends Fragment implements Serializable {

    public interface AppFragmentListener {
        public void goToFragment(ApplicationFragment fragment, int title);
    }

    private AppFragmentListener mFragmentListener;
    /**
     * Mandatory
     * <p/>
     * override this method to set the main layout of the fragment
     *
     * @return Ej: R.layout.fragment_container
     */
    abstract protected Integer getFragmentLayout();

    /**
     * Mandatory
     * <p/>
     * override this method to initialize all fragment view components
     */
    abstract protected void setViews(View view);

    /**
     * Mandatory
     * override this method to set all fragment view components events
     */
    abstract protected void setViewsEvents();

    protected abstract AppFragmentListener getFragmentListener();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);
        mFragmentListener = getFragmentListener();
        setViews(view);
        setViewsEvents();
        return view;
    }
}
