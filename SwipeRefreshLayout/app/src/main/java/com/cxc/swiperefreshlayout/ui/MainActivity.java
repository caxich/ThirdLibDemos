package com.cxc.swiperefreshlayout.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.cxc.swiperefreshlayout.R;
import com.cxc.swiperefreshlayout.adapter.NewsAdapter;
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
        implements SwipeRefreshLayout.OnRefreshListener,AbsListView.OnScrollListener{

    private SwipeRefreshLayout swipeRefreshLayout;
    private List<News> newsList = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private ListView lv_news;
    private int currentPage = 0;

    //子线程消息标志
    public static final int REFRESH_FAIL = 0;//获取html失败
    public static final int REFRESH_SUCCESS = 1;//解析html成功

    //下拉状态标志
    public static final int STATE_NONE = 0;
    public static final int STATE_REFRESH = 1;
    public static final int STATE_LOADMORE = 2;
    public static final int STATE_NOMORE = 3;
    public static final int STATE_PRESSNONE = 4;// 正在下拉但还没有到刷新的状态
    public static int swipeRefreshState = STATE_NONE;

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

        initView();
        //initData();
    }

    private void initView(){
        initSwipeRefreshLayout();

        lv_news = (ListView)findViewById(R.id.lv_news);
        lv_news.setOnScrollListener(this);
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

        //判断当前是否正在刷新，如果是，则退出
        if(swipeRefreshState == STATE_REFRESH){
            return;
        }

        //下拉刷新，将列表定位到最顶部
        lv_news.setSelection(0);

//        if(swipeRefreshLayout != null){
//            //显示刷新动画
//            swipeRefreshLayout.setRefreshing(true);
//            //防止重复刷新，暂时禁用
//            swipeRefreshLayout.setEnabled(false);
//        }

        //下拉刷新时，分页值为0
//        currentPage = 0;

        swipeRefreshState = STATE_REFRESH;
        initData();

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(newsAdapter == null || newsAdapter.getCount() == 0){
            return;
        }

        switch (scrollState){
            case SCROLL_STATE_IDLE:
                if(view.getLastVisiblePosition() == (view.getCount()-1)){
                    Toast.makeText(MainActivity.this,"to bott!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

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
                //获取新闻图片
                if(elements4.size()>0){
                    String img_url = elements4.get(0).attr("defers");
                    URL url = new URL(img_url);
                    img = BitmapFactory.decodeStream(url.openStream());
                }

                //获取新闻链接及标题
                Elements elements2 = element.getElementsByTag("a");
                if(elements2.size()>0){
                    news.setNewsUrl(elements2.get(0).attr("href"));
                    news.setNewsTitle(elements2.get(0).text());
                }

                //获取新闻时间
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

        newsAdapter = new NewsAdapter(MainActivity.this,R.layout.listview_news,newsList);
        lv_news.setAdapter(newsAdapter);

        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshState = STATE_NONE;
    }
}
