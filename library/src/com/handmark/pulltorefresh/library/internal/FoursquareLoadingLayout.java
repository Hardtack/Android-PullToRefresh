package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.R;

/**
 * Foursquare style pull to refresh layout
 * 
 * @author Will Hou (will@ezi.am)
 * @date Nov 18, 2012
 */
public class FoursquareLoadingLayout extends FlipLoadingLayout {
	
	private static final int BACKGROUND_COLOR = 0xFF3A3A3A;

	public FoursquareLoadingLayout(Context context, Mode mode, TypedArray attrs) {
		super(context, mode, attrs);
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
		
		// Hide the textviews
		View textviewContainer = getChildAt(1);
		textviewContainer.setVisibility(View.GONE);
	}

	@Override
	protected int getDefaultTopDrawableResId() {
		return R.drawable.foursquare_arrow_up;
	}
	
	@Override
	protected int getDefaultBottomDrawableResId() {
		return R.drawable.foursquare_arrow;
	}
}
