package anjithsasindran.chromecustomtabs;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final String url = "http://www.anjithsasindran.in";

    private CustomTabsClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomTabsServiceConnection mConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                mClient = customTabsClient;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mClient = null;
            }
        };

        String packageName = "com.android.chrome";
        CustomTabsClient.bindCustomTabsService(this, packageName, mConnection);
    }

    public void prefetchContent(View view) {

        if (mClient != null) {
            mClient.warmup(0);
            CustomTabsSession customTabsSession = getSession();
            customTabsSession.mayLaunchUrl(Uri.parse(url), null, null);
        }

    }

    public void loadCustomTabs(View view) {
        CustomTabsIntent.Builder mBuilder = new CustomTabsIntent.Builder(getSession());
        mBuilder.setToolbarColor(ContextCompat.getColor(this, R.color.indigo_500));
        mBuilder.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_arrow_back_white_24dp));
        mBuilder.addMenuItem("Share", setMenuItem());
        mBuilder.setActionButton(BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_file_download_white_24dp), "Material Color Picker", addActionButton());
        mBuilder.setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left);
        mBuilder.setExitAnimations(this, R.anim.slide_in_left, R.anim.slide_out_right);
        CustomTabsIntent mIntent = mBuilder.build();
        mIntent.launchUrl(this, Uri.parse(url));
    }

    private CustomTabsSession getSession() {
        return mClient.newSession(new CustomTabsCallback() {
            @Override
            public void onNavigationEvent(int navigationEvent, Bundle extras) {
                super.onNavigationEvent(navigationEvent, extras);
            }
        });
    }

    private PendingIntent setMenuItem() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Official website of Anjith Sasindran");
        shareIntent.putExtra(Intent.EXTRA_TEXT, url);
        return PendingIntent.getActivity(this, 0, shareIntent, 0);
    }

    private PendingIntent addActionButton() {
        Intent playStoreIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("market://details?id=com.anjithsasindran.materialcolorpicker"));
        return PendingIntent.getActivity(this, 0, playStoreIntent, 0);
    }

    public void loadInChrome(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    public void loadInWebView(View view) {
        Intent webViewIntent= new Intent(this, CustomWebView.class);
        webViewIntent.putExtra("url", url);
        startActivity(webViewIntent);
    }
}