package android.alcode.com.login.SlidesActivities.DeveloperActivity;

import android.alcode.com.login.R;
import android.alcode.com.login.SlidesActivities.MaterialTutorialSlide;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.vlonjatg.android.apptourlibrary.AppTour;

/**
 * Created by MOMANI on 2016/03/17.
 */
public class TechnologyUsed extends AppTour {

    @Override
    public void init(Bundle savedInstanceState) {

        int firstColor = Color.parseColor("#0097A7");
        int secondColor = Color.parseColor("#FFA000");
        int thirdColor = Color.parseColor("#FF5722");
        int forthColor = Color.parseColor("#673AB7");

        Fragment firstTutorialSlide = MaterialTutorialSlide.newInstance(R.string.slide_1_tech_firebase, R.string.slide_1_tech_firebase_subtitle,
                R.drawable.tech_page_1_front, -1);

        Fragment secondTutorialSlide = MaterialTutorialSlide.newInstance(R.string.slide_2_tech_glide, R.string.slide_2_tech_glide_subtitle,
                R.drawable.tech_page_2_front, -1);

        Fragment thiredTutorialSlide = MaterialTutorialSlide.newInstance(R.string.slide_3_tech_cloudinary, R.string.slide_3_tech_cloudinary_subtitle,
                R.drawable.tech_page_3_front, R.drawable.tut_page_3_background);

        Fragment fourthTutorialSlide = MaterialTutorialSlide.newInstance(R.string.slide_4_tech_github, R.string.slide_4_tech_github_subtitle,
                R.drawable.tech_page_4_front, -1);

        //JoinUsFragment joinFragment = new JoinUsFragment();


        addSlide(firstTutorialSlide, firstColor);
        addSlide(secondTutorialSlide,secondColor);
        addSlide(thiredTutorialSlide,thirdColor);
        addSlide(fourthTutorialSlide,forthColor);
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
        setCurrentSlide(getSlides().size()-1);
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

