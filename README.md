# VideoRecorder
微信录制小视频，播放小视频
## 说明
首先，我想说一说造轮子的事，其实我也很矛盾对于这个来讲，由于公司业务的原因，可能需求会很急，然而给你开发的时间却很短，这时候，许多功能你不能自己去写，也就是去抄别人的，如果说要自己做的话，那么时间肯定不够的，所以说，这个问题很矛盾，你也不想堕落，你想自己造轮子，但是你就是不能造，没有那么多的时间让你浪费，于是，我们好像感觉自己变得不值钱了，好吧，我就想厚着脸皮说，我一直是在抄别人，因为我也没有那么多的时间，同时我也在学习别人的思想，别人的方法，何乐而不为呢！废话不说了，开始今天的任务吧
## 效果展示

![效果](http://img.blog.csdn.net/20161028200934650)

## 实现的功能
1. 视频录制
2. 手动对焦
3. 上滑取消录制
4. 视频循环播放

## 实现方式
1. 视频录制

	首先，先感谢Vcamera，新浪微博的视频播放就是用的Vitamio的技术，因此，我用他的视频录制也不为过吧，这个案例就是用Vcamera实现的，所以说实现起来很是容易，少了很多不必要的麻烦，当然这里面也有许多我们可以学习的地方
	
2. 视频播放

	视频的循环播放是用的`videoView`
	
## 主要代码
### 1. 初始化
	

```
// 设置拍摄视频缓存路径
	VCamera.setVideoCachePath(Config.VIDEO_PATH);
	
	// 开启log输出,ffmpeg输出到logcat
	VCamera.setDebugMode(true);
	// 初始化拍摄SDK，必须
	VCamera.initialize(this);
```

### 2. 激活Media
	

```
mMediaRecorder = new MediaRecorderNative();
	
	mMediaRecorder.setOnErrorListener(this);
	mMediaRecorder.setOnEncodeListener(this);
	File f = new File(VCamera.getVideoCachePath());
	if (!FileUtils.checkFile(f)) {
	    f.mkdirs();
	}
	String key =String.valueOf(System.currentTimeMillis()); 
	mMediaObject = mMediaRecorder.setOutputDirectory(key,
	VCamera.getVideoCachePath() + key);
	        mMediaRecorder.setSurfaceHolder(mSurfaceView.getHolder());
	mMediaRecorder.prepare();
```

### 3. 处理监听事件

	

```
 private View.OnTouchListener onVideoRecoderTouchListener = new View.OnTouchListener() {
	        private float startY;
	        private float moveY;
	
	        @Override
	        public boolean onTouch(View v, MotionEvent event) {
	
	            if (mMediaRecorder == null) {
	                return false;
	            }
	
	            //第一次触摸记录时间
	            if (isFirstTouch) {
	                isFirstTouch = false;
	                firstTime = System.currentTimeMillis();
	            }
	
	            //和第一次触摸记录时间做对比
	            if (System.currentTimeMillis() - firstTime < RECORD_TIME_MAX) {
	
	                switch (event.getAction()) {
	
	                    case MotionEvent.ACTION_DOWN:
	
	                        //开始录制
	                        startY = event.getY();
	                        startRecoder();
	                        break;
	                    case MotionEvent.ACTION_MOVE:
	
	                        moveY = event.getY();
	                        float drution = moveY - startY;
	
	                        if ((drution > 0.0f) && Math.abs(drution) > OFFSET_DRUTION) {
	                            //滑动取消
	                            slideCancelRecoder();
	
	                        }
	                        if ((drution < 0.0f) && (Math.abs(drution) > OFFSET_DRUTION)) {
	                            releaseCancelRecoder();
	                        }
	                        break;
	                    case MotionEvent.ACTION_UP:
	
	                        //停止录制
	                        stopAll();
	                        if (isCancelRecoder) {
	                            hideRecoderTxt();
	                            removeRecoderPart();
	                            return true;
	                        }
	                        int duration = mMediaObject.getDuration();
	                        if (duration < RECORD_TIME_MIN) {
	                            recoderShortTime();
	                            return true;
	                        }
	                        //开始编码
	                        startEncoding();
	                        break;
	                    default:
	                        return true;
	                }
	            } else {
	                if (isNeedFinish) {
	                    isNeedFinish = false;
	                    //停止录制
	                    stopAll();
	                    //编码
	                    startEncoding();
	                    return true;
	                }
	            }
	            return true;
	        }
	    };
```

### 4. 播放视频

 

```
       videoView.setVideoPath(path);
        // 开始播放
       videoView.start();

       videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                textExist.setVisibility(View.VISIBLE);
                videoView.start();
            }
        });
```

## 最后的话
由于过程还是比较麻烦，只能粘贴部分代码，具体的代码，以我的Demo为主，大家可以下载Demo，浏览全部源码，毕竟这只是一个效果，最重要的还是如何录制视频，还有压缩视频，这些都是非常重要的，让他应用到你的项目中去吧
## 结束

1. [点击下载Demo的apk](http://dl.download.csdn.net/down11/20161028/51196193ca616cd91f09237a98a5cbf9.apk?response-content-disposition=attachment;filename=%22video.apk%22&OSSAccessKeyId=9q6nvzoJGowBj4q1&Expires=1477661696&Signature=BQyf48h1jqYwBYdxfKicU3Svejc=%20%E2%80%9C%E7%82%B9%E5%87%BB%E4%B8%8B%E8%BD%BD%E2%80%9D)
2. 欢迎各位关注我们的微信公共号：AppCode，文章都会先在微信公共号中发布的，扫面下面的二维码即可关注
![这里写图片描述](http://img.blog.csdn.net/20161028200540912)


