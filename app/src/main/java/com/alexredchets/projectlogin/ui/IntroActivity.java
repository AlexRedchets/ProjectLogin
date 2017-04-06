package com.alexredchets.projectlogin.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alexredchets.projectlogin.R;
import com.alexredchets.projectlogin.adapters.IntroViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntroActivity extends AppCompatActivity {

    @BindView(R.id.intro_view_pager)
    ViewPager viewPager;
    @BindView(R.id.intro_layout_dots)
    LinearLayout dotsLayout;
    @BindView(R.id.intro_next_button)
    Button nextButton;
    @BindView(R.id.intro_skip_button)
    Button skipButton;

    private IntroViewPagerAdapter viewPagerAdapter;
    private int[] layouts;
    private TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        viewPager = (ViewPager) findViewById(R.id.intro_view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.intro_layout_dots);

        ButterKnife.bind(this);

        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.intro_slide_1,
                R.layout.intro_slide_2,
                R.layout.intro_slide_3,
                R.layout.intro_slide_4};
        
        initDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        viewPagerAdapter = new IntroViewPagerAdapter(getApplicationContext(), layouts);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initDots(position);

                // changing the next button text 'NEXT' / 'GOT IT'
                if (position == layouts.length - 1) {
                    // last page change button text to GOT IT
                    nextButton.setText(getString(R.string.start));
                    skipButton.setVisibility(View.GONE);
                } else {
                    // still pages are left
                    nextButton.setText(getString(R.string.intro_next_button));
                    skipButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    @OnClick(R.id.intro_next_button)
    public void nextButtonClicked(View view){
        // checking for last page
        // if last page home screen will be launched
        int current = getItem(+1);
        if (current < layouts.length) {
            // move to next screen
            viewPager.setCurrentItem(current);
        } else {
            callMainActivity();
        }
    }

    @OnClick(R.id.intro_skip_button)
    public void skipButtonClicked(View view){
        Toast.makeText(this, "Skip Intro", Toast.LENGTH_SHORT).show();
    }

    private void initDots(int currentPage) {
        dots = new TextView[layouts.length];

        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(checkSDKVersion());
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.dot_inactive));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0){
            dots[currentPage].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.dot_active));
        }
    }

    private static Spanned checkSDKVersion() {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml("&#8226;",Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml("&#8226;");
        }
        return result;
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void callMainActivity() {
        Toast.makeText(this, "Done. Go to home screen", Toast.LENGTH_SHORT).show();
    }
}
