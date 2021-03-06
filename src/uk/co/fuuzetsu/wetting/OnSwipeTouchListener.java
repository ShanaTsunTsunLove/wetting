package uk.co.fuuzetsu.wetting;

import android.util.*;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class OnSwipeTouchListener implements OnTouchListener {

	private final String TAG = "OnSwipeTouchListener";
	private final GestureDetector gestureDetector = new GestureDetector(new GestureListener());

	public boolean onTouch(final View v, final MotionEvent event) {
		return gestureDetector.onTouchEvent(event);
	}

	private final class GestureListener extends SimpleOnGestureListener {

		private static final int SWIPE_THRESHOLD = 100;
		private static final int SWIPE_VELOCITY_THRESHOLD = 100;

		@Override
		public boolean onDown(MotionEvent e) {
			return true;
		}
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			onTouch(e);
			return true;
		}


		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			boolean result = false;
			try {
				float diffY = e2.getY() - e1.getY();
				float diffX = e2.getX() - e1.getX();
				if (Math.abs(diffX) > Math.abs(diffY)) {
					if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
						if (diffX > 0) {
							Log.d(TAG, "diffX > 0");
							Log.d(TAG, "diffX: " + diffX);
							onSwipeLeft(e1);
						}
						else {
							Log.d(TAG, "diffX < 0");
							Log.d(TAG, "diffX: " + diffX);
							onSwipeRight(e1);
						}
					}
				}
				else {
					Log.d(TAG, "diffX > diffY");
					Log.d(TAG, "diffX: " + diffX);
					Log.d(TAG, "diffY: " + diffY);
				}
			}
			catch (Exception exception) {
				exception.printStackTrace();
			}
			Log.d(TAG, "result: " + result);
			return result;
		}
	}

	public void onSwipeLeft(MotionEvent e) {}

	public void onSwipeRight(MotionEvent e) {}

	public void onTouch(MotionEvent e) {}
}
