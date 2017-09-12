package com.ljt.fragmentscorrespondmethod;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


//关于Fragment与Fragment、Activity通信的四种方式
public class MainActivity extends AppCompatActivity implements NameFragment.ShowName {

    private ContentFragment cf;
    private NameFragment nf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getFragmentManager();
        cf = (ContentFragment) fm.findFragmentById(R.id.content_fg);
        nf = (NameFragment) fm.findFragmentById(R.id.name_fg);
    }

    @Override
    public void showProByName(String name) {
        cf.showPro(name);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear_data:
                nf.clearData();
                break;

            default:
                break;
        }
    }

    /*
    *1.直接在一个Fragment中调用另外一个Fragment中的方法
    * 2.使用接口
    * 3.使用广播
    *4.Fragment直接调用Activity中的public方法
我们也可以直接在Fragment中调用Activity中的公开方法，如下：
((MainActivity) getActivity()).showProByName(name);
这里的showProByName就是我们上文贴出来的那个MainActivity中的方法。
    *
    *5 fragment setArguments（）
    *
    *
    *
    * */
}
