package com.example.gsondemo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gsondemo.bean.Book;
import com.example.gsondemo.bean.Tag;
import com.google.gson.Gson;
import com.jike.gsondemo.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class MainActivity extends Activity {
	//获取天气的url:http://m.weather.com.cn/atad/101010100.html
	private String url = "https://api.douban.com/v2/book/1220562";
	private TextView tv_tag;
	private ListView listview;
	private MyAdapter adapter;
	private ArrayList<Tag> list;
	private TextView tv_title;
	private TextView tv_price;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// tv_tag=(TextView) findViewById(R.id.tv_tag);
		listview = (ListView) findViewById(R.id.listview);
		tv_title=(TextView) findViewById(R.id.tv_title);
		tv_price=(TextView) findViewById(R.id.tv_price);
		list = new ArrayList<Tag>();
		//初始化数据；
		getData();
		adapter = new MyAdapter(this, list);
		listview.setAdapter(adapter);
	}
	private void getData() {
		// TODO Auto-generated method stub
		HttpUtils http=new HttpUtils();
		http.send(HttpMethod.GET, url, new RequestCallBack<String>() {
           //加载失败的时候；
			@Override
			public void onFailure(HttpException e, String s) {
				// TODO Auto-generated method stub	
			}
           //加载成功；
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				dealData(responseInfo.result);
				System.out.println("---"+responseInfo.result);
			}
			//正在加载的时候显示；可以显示百分比；也可以弹出对话框之类的提示；
			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				// TODO Auto-generated method stub
				super.onLoading(total, current, isUploading);
			}
		});
	}
	private void dealData(String result) {
		//gson在解析的时候，｛｝代码对象；【】代表集合；
		Gson gson = new Gson();
		//json中所有的数据已经返回到book实体中；
		Book book = gson.fromJson(result, Book.class);
		Log.i("info", book.getTitle() + ":" + book.getPublisher() + ":"
				+ book.getTags().size());
		tv_title.setText("书名:"+book.getTitle());
		tv_price.setText("价格:"+book.getPrice());
		list = book.getTags();
		//list集合加入数据后刷新adapter;不刷新则获取不到数据；
		adapter.refreshData(list);
	}

}
