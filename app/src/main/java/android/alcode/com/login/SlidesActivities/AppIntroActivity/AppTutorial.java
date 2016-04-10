package android.alcode.com.login.SlidesActivities.AppIntroActivity;

import android.alcode.com.login.R;
import android.alcode.com.login.SlidesActivities.MaterialTutorialSlide;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.vlonjatg.android.apptourlibrary.AppTour;

/**
 * Created by MOMANI on 2016/03/17.
 */
public class AppTutorial extends AppTour {

    @Override
    public void init(Bundle savedInstanceState) {

        int firstColor = Color.parseColor("#0097A7");
        int secondColor = Color.parseColor("#FFA000");
        int thirdColor = Color.parseColor("#FF5722");
        int forthColor = Color.parseColor("#673AB7");

        Fragment firstTutorialSlide = MaterialTutorialSlide.newInstance(R.string.slide_1_african_story_books, R.string.slide_1_african_story_books_subtitle,
                R.drawable.tut_page_1_front, R.drawable.tut_page_1_background);

        Fragment secondTutorialSlide = MaterialTutorialSlide.newInstance(R.string.slide_2_volunteer_professionals, R.string.slide_2_volunteer_professionals_subtitle,
                R.drawable.tut_page_2_front, R.drawable.tut_page_2_background);

        Fragment thiredTutorialSlide = MaterialTutorialSlide.newInstance(R.string.slide_3_download_and_go, R.string.slide_3_download_and_go_subtitle,
                R.drawable.tut_page_3_foreground, R.drawable.tut_page_3_background);

        //JoinUsFragment joinFragment = new JoinUsFragment();


        addSlide(firstTutorialSlide, firstColor);
        addSlide(secondTutorialSlide,secondColor);
        addSlide(thiredTutorialSlide,thirdColor);
        //addSlide(joinFragment,customSlideColor);


        //Customize tour
        setSkipButtonTextColor(Color.WHITE);
        setNextButtonColorToWhite();
        setDoneButtonTextColor(Color.WHITE);
        setDoneText("Done");
    }

    @Override
    public void onSkipPressed() {
        //Do something after clicking Skip button.
        //E.x: Go to the sign up slide.
        setCurrentSlide(2);
    }

    @Override
    public void onDonePressed() {
        //Do something after clicking Done button.
        //E.x: Finish the intro.
        finish();
//        Intent intent = new Intent(getApplication(), MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
    }
}

