package android.alcode.com.login.SlidesActivities;

import android.alcode.com.login.R;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * @author rebeccafranks
 * @since 2015/10/15.
 */
public class MaterialTutorialSlide extends Fragment {
    private static final String TAG = "MaterialTutFragment";

    private static String ARG_foregroundImageRes = "foregroundImage";
    private static String ARG_backgroundImageRes = "backgroudImage";
    private static String ARG_titleText = "title";
    private static String ARG_subTitleText = "subtitle";

    private int backgroundImage;
    private int foregroundImage;
    private int title;
    private int subTitle;

    public MaterialTutorialSlide() {
    }

    /**
     * Create Material Slide
     *
     * @param foregroundImageRes foreground Image resource for the slide
     * @param backgroundImageRes background Image resource for the slide
     * @param titleTextRes       String value of the slide title
     * @param subTitleTextRes    String value of the slide content
     * @return Returned the created slide
     */
    public static MaterialTutorialSlide newInstance(@NonNull int titleTextRes, @Nullable int subTitleTextRes, @DrawableRes int foregroundImageRes, @DrawableRes int backgroundImageRes) {

        MaterialTutorialSlide tutorialImageFragment = new MaterialTutorialSlide();

        Bundle args = new Bundle();
        args.putInt(ARG_foregroundImageRes, foregroundImageRes);
        args.putInt(ARG_backgroundImageRes, backgroundImageRes);
        args.putInt(ARG_titleText, titleTextRes);
        args.putInt(ARG_subTitleText, subTitleTextRes);
        tutorialImageFragment.setArguments(args);

        return tutorialImageFragment;
    }

    int page;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().size() != 0) {
            backgroundImage = getArguments().getInt(ARG_backgroundImageRes);
            foregroundImage = getArguments().getInt(ARG_foregroundImageRes);
            title = getArguments().getInt(ARG_titleText);
            subTitle = getArguments().getInt(ARG_subTitleText);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_help_tutorial_image, container, false);
        v.setTag(page);

        ImageView imageViewFront = (ImageView) v.findViewById(R.id.fragment_help_tutorial_imageview);
        ImageView imageViewBack = (ImageView) v.findViewById(R.id.fragment_help_tutorial_imageview_background);
        TextView textViewSubTitle = (TextView) v.findViewById(R.id.fragment_help_tutorial_subtitle_text);
        TextView textView = (TextView) v.findViewById(R.id.fragment_help_tutorial_text);

        textView.setText(title);
        textViewSubTitle.setText(subTitle);
        Glide.with(this).load(backgroundImage).into(imageViewBack);
        Glide.with(this).load(foregroundImage).into(imageViewFront);
        //}
        return v;
    }

  /*  public void onTranform(int pageNumber, float transformation) {
        Log.d(TAG, "onTransform:" + transformation);
        if (!isAdded()) {
            return;
        }
        if (transformation == 0){
            imageViewBack.setTranslationX(0);
            return;
        }
        int pageWidth = getView().getWidth();
        Log.d(TAG, "onTransform Added page Width:" + pageWidth);

        imageViewBack.setTranslationX(-transformation * (pageWidth / 2)); //Half the normal speed

    }*/
}

