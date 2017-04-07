package com.cxc.bottomnavigationbar.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.cxc.bottomnavigationbar.fragment.CasesFragment;
import com.cxc.bottomnavigationbar.fragment.EnterpriseFragment;
import com.cxc.bottomnavigationbar.fragment.LawFragment;
import com.cxc.bottomnavigationbar.R;
import com.cxc.bottomnavigationbar.fragment.UserFragment;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationBar.OnTabSelectedListener{

    public static final int CASES = 0;
    public static final int LAW = 1;
    public static final int ENTERPRISE = 2;
    public static final int USER = 3;

    private BottomNavigationBar btm_bar;

    private CasesFragment casesFragment;
    private UserFragment userFragment;
    private LawFragment lawFragment;
    private EnterpriseFragment enterpriseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        //初始化底部导航栏
        initBtmBar();

        //设置默认Fragment
        setDefaultFragment();
    }

    private void initBtmBar(){
        btm_bar = (BottomNavigationBar)findViewById(R.id.btm_bar);
        BadgeItem badgeItem = new BadgeItem().setText("2");
        btm_bar
                //切换时是否显示动画
                .setMode(BottomNavigationBar.MODE_FIXED)
                //点击时是否有水波纹
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                //设置背景颜色
                .setBarBackgroundColor(R.color.btm_bar)
                //选中或未选中时图标和文字的颜色（全局设定，每个Item也可单独设定选中时的颜色）
                .setInActiveColor(R.color.white)
                .setActiveColor(R.color.colorPrimary)
                //添加导航按钮
                .addItem(new BottomNavigationItem(R.mipmap.city_2,R.string.home))
                .addItem(new BottomNavigationItem(R.mipmap.map_2,R.string.map))
                .addItem(new BottomNavigationItem(R.mipmap.file,R.string.report).setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.user_2,R.string.my))
                .initialise();
        btm_bar.setTabSelectedListener(this);
    }

    private void setDefaultFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        casesFragment = new CasesFragment();
        transaction.add(R.id.ll_content,casesFragment);
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction){
        if(casesFragment != null){
            fragmentTransaction.hide(casesFragment);
        }
        if(userFragment != null){
            fragmentTransaction.hide(userFragment);
        }
        if(enterpriseFragment != null){
            fragmentTransaction.hide(enterpriseFragment);
        }
        if(lawFragment != null){
            fragmentTransaction.hide(lawFragment);
        }
    }

    private void showFragment(int i,FragmentTransaction fragmentTransaction){
        switch (i){
            case CASES:
                if(casesFragment == null){
                    casesFragment = new CasesFragment();
                    fragmentTransaction.add(R.id.ll_content,casesFragment);
                }else{
                    fragmentTransaction.show(casesFragment);
                }
                break;
            case LAW:
                if(lawFragment == null){
                    lawFragment = new LawFragment();
                    fragmentTransaction.add(R.id.ll_content,lawFragment);
                }else{
                    fragmentTransaction.show(lawFragment);
                }
                break;
            case  ENTERPRISE:
                if(enterpriseFragment == null){
                    enterpriseFragment = new EnterpriseFragment();
                    fragmentTransaction.add(R.id.ll_content,enterpriseFragment);
                }else{
                    fragmentTransaction.show(enterpriseFragment);
                }
                break;
            case  USER:
                if(userFragment == null){
                    userFragment = new UserFragment();
                    fragmentTransaction.add(R.id.ll_content,userFragment);
                }else{
                    fragmentTransaction.show(userFragment);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        showFragment(position,fragmentTransaction);
        fragmentTransaction.commit();
    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }
}
