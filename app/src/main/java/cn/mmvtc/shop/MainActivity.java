package cn.mmvtc.shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cn.mmvtc.shop.database.SQLiteHelper;

public class MainActivity extends Activity{
    private ListView mListView;
    SQLiteHelper mSQLiteHelper;
    String id;
    TextView Shopping_name,content;
    //商品名称与价格数据集合
    private String[] titles={
            "Crodne派克服","野兽派（THE BEAST）永生花玫瑰礼盒","S999纯银项链新年礼物生日礼物","小米9 Pro 5G 骁龙855Plus","联想ThinkPad X1 Carbon 2019",
            "男鞋冬季2019新款","迪奥（Dior）滋润正红999礼盒","豹纹新款网红时尚真空便携随手保温杯","怡浓纯脂麋鹿棒棒糖黑巧克力年货节礼盒","床垫单双人学生铺宿舍立体酒店宾馆民宿垫子","幻钻个性铁艺小吊灯单头可调节"
    };
    private  String[] prices={
            "1580","520","288","3799","9699","280","1200","68","39","512","269"
    };
    //图片数据集合
    private int[] icons={R.drawable.clothes,R.drawable.flower,R.drawable.
            xianglian,R.drawable.xiaomi,R.drawable.diannao,R.drawable.xiezi,R.drawable.kouhong,R.drawable.baowenbei,R.drawable.qiaokeli,R.drawable.bed,R.drawable.light};
    private android.view.LayoutInflater LayoutInflater;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView car=(ImageView) findViewById(R.id.car);
        mListView=(ListView) findViewById(R.id.lv);//初始化ListView控件
        MybaseAdapter mAdapter =new MybaseAdapter();//
        mSQLiteHelper = new SQLiteHelper(this);
        mListView.setAdapter(mAdapter);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ShoppinglistActivity.class);
                startActivityForResult(intent,1);
            }
        });
        initData();
    }
    protected void initData(){
//        mSQLiteHelper=new SQLiteHelper(this);
//        Shopping_name.setText("购物车");
}
    class MybaseAdapter extends BaseAdapter {
            @Override
            public int getCount() { //获取item的总数
                return titles.length;//返回ListView Item条目的总数
            }
            @Override
            public Object getItem(int position) {
                return titles[position];//返回Item的数据对象
            }
            @Override
            public long getItemId(int position) {
                return position; //返回Item的id
            }
            //得到Item的View视图
            class ViewHolder {
                TextView title, price;
                ImageView iv;
                Button addshop;

            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                if (convertView == null) {
                    convertView = View.inflate(MainActivity.this, R.layout.goods_item, null);
                    holder = new ViewHolder();
                    holder.title =  convertView.findViewById(R.id.title);
                    holder.price = convertView.findViewById(R.id.price);
                    holder.iv =  convertView.findViewById(R.id.iv);
                    holder.addshop = convertView.findViewById(R.id.addshop);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.addshop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean n = mSQLiteHelper.insertData(titles[position],prices[position],icons[position]);
                        if(n){
                            Toast.makeText(MainActivity.this,"加入购物车成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this,"加入购物车失败",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                holder.title.setText(titles[position]);
                holder.price.setText(prices[position]);
                holder.iv.setBackgroundResource(icons[position]);
                return convertView;
            }
        }
    }
