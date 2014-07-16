package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor;
import com.huewu.pla.lib.MultiColumnListView;
import com.huewu.pla.lib.internal.PLA_AbsListView;

/**
 * Created by minggong on 7/16/14.
 */
public class PullToRefreshMultiColumnView extends PullToRefreshAdapterViewBase2<MultiColumnListView>  {

    public PullToRefreshMultiColumnView(Context context) {
        super(context);
    }

    public PullToRefreshMultiColumnView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshMultiColumnView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshMultiColumnView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected MultiColumnListView createRefreshableView(Context context, AttributeSet attrs) {
        final MultiColumnListView gv;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            gv = new InternalMultiColumnListViewSDK9(context, attrs);
        } else {
            gv = new InternalMultiColumnListView(context, attrs);
        }
        // Use Generated ID (from res/values/ids.xml)
        gv.setId(R.id.multiColumnListView);
        return gv;
    }

    @Override
    public void onScrollStateChanged(PLA_AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(PLA_AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


    class InternalMultiColumnListView extends MultiColumnListView implements EmptyViewMethodAccessor {

        public InternalMultiColumnListView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public void setEmptyView(View emptyView) {
            PullToRefreshMultiColumnView.this.setEmptyView(emptyView);
        }

        @Override
        public void setEmptyViewInternal(View emptyView) {
            super.setEmptyView(emptyView);
        }
    }

    @TargetApi(9)
    final class InternalMultiColumnListViewSDK9 extends InternalMultiColumnListView {

        public InternalMultiColumnListViewSDK9(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX,
                                       int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

            final boolean returnValue = super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
                    scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);

            // Does all of the hard work...
            OverscrollHelper.overScrollBy(PullToRefreshMultiColumnView.this, deltaX, scrollX, deltaY, scrollY, isTouchEvent);

            return returnValue;
        }
    }
}
