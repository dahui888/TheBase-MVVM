package com.theone.mvvm.core.widge;

//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * @author The one
 * @date 2019/10/9 0009
 * @describe 分割线
 * @email 625805189@qq.com
 * @remark
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "SpacesItemDecoration";

    private final int column;
    private final int headerNum;
    private final int left;
    private final int right;
    private final int top;
    private final int bottom;

    public SpacesItemDecoration(int column, int space) {
        this(column,0,space);
    }

    public SpacesItemDecoration(int column, int headerNum, int space) {
        this(column, headerNum, space, space, space, space);
    }

    public SpacesItemDecoration(int column, int headerNum, int left, int right, int top, int bottom) {
        this.column = column;
        this.headerNum = headerNum;
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        // 当前View列位置,
        int columnIndex = 0;
        // 当前View为第几个
        int position ;
        if (parent.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager.LayoutParams params =
                    (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            columnIndex = params.getSpanIndex();
            position = params.getViewAdapterPosition();
        } else if (parent.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) view.getLayoutParams();
            columnIndex = params.getSpanIndex();
            position = params.getViewAdapterPosition();
        } else {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
            position = params.getViewAdapterPosition();
        }
        // 有的时候适配器会加上头部，如果有就不加间距，让头部自行处理 （如果有尾部，同理也可以加上）
        if (position >= headerNum) {
            // 全都加上bottom,left,right间距
            outRect.left = left;
            outRect.right = right;
            outRect.bottom = bottom;
            // 但是只给第一个加上top间距
            outRect.top = position == headerNum?top:0;
            if (column > 1) {
                // 只要不为一列，只考虑最左和最右项的差别
                int X = column - columnIndex;
                // 如果为多列时，给第一行的加上top.
                // X != column 这个判断
                // 当遇到SectionAdapter时，有两列，如果第一行是标题占据一整行，
                // position=1时已经是第二行的内容层了，这个时候就不能加了，案例见aqtour首页布局
                if(position<column && X != column){
                    outRect.top = top;
                }
                if (X == column) {
                    // 最左边的只需要在右边设置一半的间距
                    outRect.right = right/2;
                } else if (X == 1) {
                    // 最右边的只需要在左边设置一半的间距
                    outRect.left = left/2;
                } else {
                    // 其余的一律在左右都设置一般的间距
                    outRect.left = left/2;
                    outRect.right = right/2;
                }
            }
        }
    }

}