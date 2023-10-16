package de.cieszynski.geckoviewrunner;

import android.app.Activity;
import android.os.Bundle;
import androidx.core.view.WindowCompat;
import org.mozilla.geckoview.GeckoRuntime;
import org.mozilla.geckoview.GeckoRuntimeSettings;
import org.mozilla.geckoview.GeckoSession;
import org.mozilla.geckoview.GeckoView;

public class MainActivity extends Activity implements GeckoSession.ProgressDelegate {

    private GeckoView mGeckoView;
    private GeckoSession mGeckoSession;
    private GeckoSession.SessionState mState;
    private static GeckoRuntime sGeckoRuntime;
    private static final String BUNDLE_KEY = "gecko";

    @Override
    public void onSessionStateChange(GeckoSession session,
                                    GeckoSession.SessionState sessionState) {

        if(mGeckoSession == session) {
            mGeckoSession.restoreState(sessionState);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // https://developer.android.com/develop/ui/views/layout/edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        if(savedInstanceState==null) {
            final GeckoRuntimeSettings.Builder runtimeSettingsBuilder =
                new GeckoRuntimeSettings.Builder();

            runtimeSettingsBuilder
            .javaScriptEnabled(true)
            .aboutConfigEnabled(true);

            mGeckoView = new GeckoView(this);
            
            mGeckoSession = new GeckoSession();
            
            sGeckoRuntime = GeckoRuntime.create(this, runtimeSettingsBuilder.build());

            mGeckoSession.open(sGeckoRuntime);
            mGeckoView.setSession(mGeckoSession);
           // mGeckoSession.loadUri("about:buildconfig");
           // mGeckoSession.loadUri("resource://android/assets/index.html");
           mGeckoSession.loadUri("https://www.heise.de");
        } else {

        }


        setContentView(mGeckoView);
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(BUNDLE_KEY, mState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        mState = bundle.getParcelable(BUNDLE_KEY);
        mGeckoSession.restoreState(mState);
    }
/*
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mWebView.restoreState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }*/
}
