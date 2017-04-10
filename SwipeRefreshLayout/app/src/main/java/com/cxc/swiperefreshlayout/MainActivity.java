package com.cxc.swiperefreshlayout;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cxc.swiperefreshlayout.bean.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeRefreshLayout;
    private List<News> newsList = new ArrayList<>();

    public static final int REFRESH_FAIL = 0;//获取html失败
    public static final int REFRESH_SUCCESS = 1;//解析html成功

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case REFRESH_FAIL:
                    break;
                case REFRESH_SUCCESS:
                    showNewsList();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initView(){
        initSwipeRefreshLayout();
    }

    private void initSwipeRefreshLayout(){
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.srl);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.swiperefresh_color1,R.color.swiperefresh_color2,
                R.color.swiperefresh_color3,R.color.swiperefresh_color4);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(MainActivity.this,"refresh start",Toast.LENGTH_SHORT).show();
    }

    private void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                parseHtml();
            }
        }).start();
    }

    private void parseHtml(){
        try{
            Document document;
            document = Jsoup.connect("http://www.jy510.com/a/houseinfo/kpyh/").get();
            Elements elements = document.select("#listZone");
            Document document1 = Jsoup.parse(elements.toString());
            Elements elements1 = document1.getElementsByClass("tpWrap");

            for(int i=0;i<elements1.size();i++){
                News news = new News();
                Element element = elements1.get(i);

                //从url读取图片，耗时，放在最开始处理
                Elements elements4 = element.getElementsByTag("img");
                Bitmap img = null;
                if(elements4.size()>0){
                    String img_url = elements4.get(0).attr("defers");
                    URL url = new URL(img_url);
                    img = BitmapFactory.decodeStream(url.openStream());
                }

                Elements elements2 = element.getElementsByTag("a");
                if(elements2.size()>0){
                    news.setNewsUrl(elements2.get(0).attr("href"));
                    news.setNewsTitle(elements2.get(0).text());
                }
                Elements elements3 = element.getElementsByClass("time");
                if(elements3.size()>0){
                    String str_tmp = elements3.get(0).text();
                    str_tmp = str_tmp.substring(0,16);
                    news.setNewsTime(str_tmp);
                }

                //图片放在最后赋值
                news.setNewsPic(img);

                newsList.add(news);
            }

            Message msg = new Message();
            msg.what = REFRESH_SUCCESS;
            msg.obj = "refresh success";
            handler.sendMessage(msg);
        }catch (IOException ie){
            Message msg = new Message();
            msg.what = REFRESH_FAIL;
            msg.obj = "refresh failed";
            handler.sendMessage(msg);
            ie.printStackTrace();
        }
    }

    private void showNewsList(){
        Toast.makeText(MainActivity.this,newsList.get(7).getNewsTitle(),Toast.LENGTH_SHORT).show();
    }
}
