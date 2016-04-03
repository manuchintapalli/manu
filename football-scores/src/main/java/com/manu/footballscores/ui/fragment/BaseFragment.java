
package com.manu.footballscores.ui.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.view.View;

import com.manu.footballscores.ScoresApplication;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;

/**
 * Base class for all fragments.
 * Binds views and watches memory leaks
 *
 * @see ButterKnife
 * @see RefWatcher
 */
public abstract class BaseFragment extends Fragment {

    @CallSuper
    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @CallSuper
    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @CallSuper
    @Override public void onDestroy() {
        super.onDestroy();
        ScoresApplication.get(getActivity()).getRefWatcher().watch(this);
    }
}
