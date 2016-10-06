
## CommonImageLoader ##
> 一个工具,针对于图片加载框架的抽象封装,主要功能:
>   
> - 支持全局更换具体的图片加载框架,内部代码无需改动
> - 可以对待加载的url进行统一的变换,如加前缀,加后缀,添加token,截取等等
> - 配置占位图,错误图等等统一的配置


## 引用 ##
最新版本号[![](https://jitpack.io/v/chenzhenboy/commonimageloader.svg)](https://jitpack.io/#chenzhenboy/commonimageloader)
### Gradle ###

Project.gradle

    allprojects {
    	repositories {
        	jcenter()
        	maven { url "https://jitpack.io" }
    	}
	}

app.gradle

    compile 'com.github.chenzhenboy:commonimageloader:0.8.1'

### Maven ###
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
==
	<dependency>
	    <groupId>com.github.chenzhenboy</groupId>
	    <artifactId>commonimageloader</artifactId>
	    <version>0.8.1</version>
	</dependency>

## 使用 ##
1.在Application中初始化  

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfig loaderConfig = new ImageLoaderConfig.Builder()
                .client(new GlideClient())//设置加载器
                .placePicRes(R.mipmap.placeholder)//占位图
                .errorPicRes(R.mipmap.placeholder)//错误图
                .interceptor(new DefaInterceptor())//设置拦截器
                .build();
        ImageLoaderHelper.init(loaderConfig);//初始化CommonImageLoader
    }

2.加载图片
	
	ImageLoaderHelper.load(context(), imageView, imageUrl);
  或者带缩略图的加载:

	ImageLoaderHelper.load(context(), imageView, imageUrl,0.1f);
		
## 更换图片加载框架 ##
项目开发中需要更换图片加载框架,如果去每一个加载图片的地方改代码,那...  
so,自定义一个类,实现`ImageLoaderClient`接口即可,如使用Glide加载:


    public class GlideClient implements ImageLoaderClient {
		//正常加载图片
    	@Override
    	public void loadImage(Context context, ImageView view, String url, 	int placePicRes, int errorPicRes) {
        	Glide.with(context).load(url).placeholder(placePicRes).error	(errorPicRes).into(view);
    	}
		//带缩略图
    	@Override
    	public void loadImage(Context context, ImageView view, String url, 	int placePicRes, int errorPicRes, float thumbnail) {
        	Glide.with(context).load(url).placeholder(placePicRes).error(errorPicRes).thumbnail(thumbnail).into(view);
    	}
	}
在init()中使用自己的`ImageLoaderClient`

    .client(new GlideClient())//设置加载框架

## 设置拦截器 ##
拦截器可以对待加载的图片url进行统一的预处理,工具提供了默认拦截器(不对url做任何处理):

    .interceptor(new DefaInterceptor())//设置拦截器
可以自定义类实现`ImageInterceptor`接口,然后在init()中设置该拦截器:

	public class DefaInterceptor implements ImageInterceptor {

    	@Override
    	public String InterceptorUrl(String oldUrl) {
        	return "http://" + oldUrl;
    	}
	}

## 关于作者 ##

- 简	书:[uncochen](http://www.jianshu.com/users/1695117cc969 )
- 新浪微博:[@Chen丶振](http://weibo.com/724132180 )

## License ##

    Copyright 2016 chenzhenboy

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
