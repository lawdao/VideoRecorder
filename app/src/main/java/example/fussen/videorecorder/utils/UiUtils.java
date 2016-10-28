package example.fussen.videorecorder.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Fussen on 2016/10/27.
 */

public class UiUtils {
    /**
     * px = dp * (dpi / 160)
     *
     * @param ctx
     * @param dip
     * @return
     */
    public static int dipToPX(final Context ctx, float dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, ctx.getResources().getDisplayMetrics());
    }

}
