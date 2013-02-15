package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.R;

/**
 * Foursquare style pull to refresh layout
 * 
 * @author Will Hou (will@ezi.am)
 * @date Nov 18, 2012
 */
public class FoursquareLoadingLayout extends FlipLoadingLayout {
	
	private static final int BACKGROUND_COLOR = 0xFF3A3A3A;
	
	protected ImageView mCircleImageView;
	
	private Animation mSpinAnimation;

	public FoursquareLoadingLayout(Context context, Mode mode, Orientation scrollDirection, TypedArray attrs) {
		super(context, mode, scrollDirection, attrs);
		setBackgroundColor(BACKGROUND_COLOR);

		// Make refresh view container fill
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.setMargins(0, 0, 0, 0);
		params.gravity = Gravity.CENTER;
		
		ViewGroup refreshViewContainer = (ViewGroup) getChildAt(0);
		refreshViewContainer.setLayoutParams(params);
		
		// Center the arrow image
		FrameLayout.LayoutParams frameParams = (FrameLayout.LayoutParams) mHeaderImage.getLayoutParams();
		frameParams.gravity = Gravity.CENTER;
		mHeaderImage.setLayoutParams(frameParams);
				
        mSpinAnimation = new RotateAnimation(-359, 0, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mSpinAnimation.setInterpolator(new LinearInterpolator());
        mSpinAnimation.setRepeatMode(Animation.INFINITE);
        mSpinAnimation.setRepeatCount(Animation.INFINITE);
//        mSpinAnimation.setStartOffset(200);
        mSpinAnimation.setDuration(500);

        Log.d("@@@", "Foursquare layout");
        
//        mCircleImageView = (ImageView) findViewById(R.id.pull_to_refresh_custom_image);
	}

//	TODO not working
//	protected int getDefaultTopDrawableResId() {
//		return R.drawable.foursquare_arrow_up;
//	}
//	
//	protected int getDefaultBottomDrawableResId() {
//		return R.drawable.foursquare_arrow;
//	}
	
	@Override
	protected int getDefaultDrawableResId() {
	    return R.drawable.foursquare_arrow;
	}
	
	@Override
	protected void refreshingImpl() {
	    super.refreshingImpl();
	    mHeaderProgress.setVisibility(View.GONE);
	    if (mCircleImageView != null) {
            mCircleImageView.setVisibility(View.VISIBLE);
	        mCircleImageView.startAnimation(mSpinAnimation);
	    }
	}
	
	@Override
	protected void resetImpl() {
        super.resetImpl();
        mHeaderProgress.setVisibility(View.GONE);
        if (mCircleImageView != null) {
            mCircleImageView.clearAnimation();
            mCircleImageView.setVisibility(View.GONE);
        }
	}
}
