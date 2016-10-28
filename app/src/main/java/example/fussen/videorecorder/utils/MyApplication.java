package example.fussen.videorecorder.utils;

import android.app.Application;

import com.yixia.camera.VCamera;

/**
 * Created by Fussen on 2016/10/28.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // 设置拍摄视频缓存路径

        VCamera.setVideoCachePath(Config.VIDEO_PATH);

        // 开启log输出,ffmpeg输出到logcat
        VCamera.setDebugMode(true);
        // 初始化拍摄SDK，必须
        VCamera.initialize(this);
    }
}
