package example.fussen.videorecorder.utils;

import android.os.Environment;


public class Config {

    //总路径名称
    public static final String VIDEO_DIR = "/FussenVideo";
    //总路径
    public static final String FUSSEN_PATH = Environment.getExternalStorageDirectory().toString() + VIDEO_DIR;
    //视频存储路径
    public static final String VIDEO_PATH = FUSSEN_PATH + "/video";

    //视频缓存路径

    public static final String VIDEO_CACHE = FUSSEN_PATH + "/videocache";
}
