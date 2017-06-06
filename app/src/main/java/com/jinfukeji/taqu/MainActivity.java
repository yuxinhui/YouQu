package com.jinfukeji.taqu;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jinfukeji.taqu.fragment.ClassifyFragment;
import com.jinfukeji.taqu.fragment.IndexFragment;
import com.jinfukeji.taqu.fragment.MyFragment;
import com.jinfukeji.taqu.fragment.ShoppingcartFragment;

/**
 *  底部导航栏
 * */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //fragment对象
    private IndexFragment index_fg;
    private ClassifyFragment classify_fg;
    private ShoppingcartFragment shopping_fg;
    private MyFragment my_fg;
    //定义底部导航栏布局
    private LinearLayout banner_index,banner_classify,banner_shoppingcart,banner_my;
    //定义导航栏中的控件
    private ImageView banner_my_img,banner_index_img,banner_classify_img,banner_shoppingcart_img;
    //FragmentManager对象
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager=getSupportFragmentManager();
        initView();//初始化控件
        setChooseItem(0);//默认选择第一个填充页面
    }

    //初始化控件
    private void initView() {
        banner_index= (LinearLayout) this.findViewById(R.id.banner_index);
        banner_classify= (LinearLayout) this.findViewById(R.id.banner_classify);
        banner_shoppingcart= (LinearLayout) this.findViewById(R.id.banner_shoppingcart);
        banner_my= (LinearLayout) this.findViewById(R.id.banner_my);

        banner_index_img= (ImageView) this.findViewById(R.id.banner_index_img);
        banner_classify_img= (ImageView) this.findViewById(R.id.banner_classify_img);
        banner_shoppingcart_img= (ImageView) this.findViewById(R.id.banner_shoppingcart_img);
        banner_my_img= (ImageView) this.findViewById(R.id.banner_my_img);

        banner_index.setOnClickListener(this);
        banner_classify.setOnClickListener(this);
        banner_shoppingcart.setOnClickListener(this);
        banner_my.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.banner_index:
                setChooseItem(0);
                break;
            case R.id.banner_classify:
                setChooseItem(1);
                break;
            case R.id.banner_shoppingcart:
                setChooseItem(2);
                break;
            case R.id.banner_my:
                setChooseItem(3);
                break;
            default:
                break;
        }
    }

    //定义一个选中一个item后的处理
    private void setChooseItem(int i) {
        //重置选项+隐藏所有Fragment
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        clearColor();
        hideFragments(transaction);
        switch (i){
            case 0:
                banner_index_img.setImageResource(R.mipmap.banner_index_ok);
                if (index_fg == null){
                    index_fg=new IndexFragment();
                    transaction.add(R.id.content,index_fg);
                }else {
                    transaction.show(index_fg);
                }
                break;
            case 1:
                banner_classify_img.setImageResource(R.mipmap.banner_classify_ok);
                if (classify_fg == null){
                    classify_fg= new ClassifyFragment();
                    transaction.add(R.id.content,classify_fg);
                }else {
                    transaction.show(classify_fg);
                }
                break;
            case 2:
                banner_shoppingcart_img.setImageResource(R.mipmap.banner_shoppingcart_ok);
                if (shopping_fg == null){
                    shopping_fg= new ShoppingcartFragment();
                    transaction.add(R.id.content,shopping_fg);
                }else {
                    transaction.show(shopping_fg);
                }
                break;
            case 3:
                banner_my_img.setImageResource(R.mipmap.banner_my_ok);
                if (my_fg == null){
                    my_fg= new MyFragment();
                    transaction.add(R.id.content,my_fg);
                }else {
                    transaction.show(my_fg);
                }
                break;
        }
        transaction.commit();
    }

    //隐藏所有的Fragment,避免fragment混乱
    private void hideFragments(FragmentTransaction transaction) {
        if (index_fg != null){
            transaction.hide(index_fg);
        }
        if (classify_fg != null){
            transaction.hide(classify_fg);
        }
        if (shopping_fg != null){
            transaction.hide(shopping_fg);
        }
        if (my_fg != null){
            transaction.hide(my_fg);
        }
    }

    //初始化图片颜色
    private void clearColor() {
        banner_index_img.setImageResource(R.mipmap.banner_index_wei);
        banner_classify_img.setImageResource(R.mipmap.banner_classify_wei);
        banner_shoppingcart_img.setImageResource(R.mipmap.banner_shoppingcart_wei);
        banner_my_img.setImageResource(R.mipmap.banner_my_wei);
    }

    //退出程序
    private long exitTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis() - exitTime >2000){
                Toast.makeText(getApplicationContext(),"再按一次退出程序",Toast.LENGTH_SHORT).show();
                exitTime=System.currentTimeMillis();
            }else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}