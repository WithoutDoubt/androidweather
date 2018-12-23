package com.example.admin.androidweather;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.androidweather.gson.AddressUse;
import com.example.admin.androidweather.gson.SessionGson;
import com.example.admin.androidweather.gson.UserMenu;
import com.example.admin.androidweather.util.HttpUtil;
import com.example.admin.androidweather.util.Utility;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {
//    public static String address_localhost = "http://10.0.2.2:8080";
//    public static String address_210 = "http://210.45.212.96:8080";


    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private EditText editName;
    private EditText editPsw;

    private List<UserMenu> list_menu ;
   // private AddressUse addressUse;

    private ProgressDialog progressDialog;

   //服务端交互
    private String address ;
    //private String addresslogin = address_210 + " /a/login?";
    //private String addresslogin =  " http://210.45.212.96:8080/a/login?";
    private String addresslogin = AddressUse.ADDRESS_HEAD +"a/login?";
    private RequestBody requestBody;
    private SessionGson user;


    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //加载
        final QMUITipDialog tipDialog;
        tipDialog = new QMUITipDialog.Builder(MainActivity.this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();


        //状态栏
        QMUIStatusBarHelper.translucent(this);
        View root = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        ButterKnife.bind(this, root);
        //调整标题栏
        initTopBar();
        setContentView(root);

        Button button1 = (Button) findViewById(R.id.bin_login);
        editName = (EditText) findViewById(R.id.et_account);
        editPsw = (EditText)findViewById(R.id.et_password);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = getSharedPreferences("user",MODE_PRIVATE).edit();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipDialog.show();

                String inputName = editName.getText().toString();
                String inputPsw = editPsw.getText().toString();

                Log.d("Name111", "onClick: "+inputName + "  ----  " + inputPsw );


                if ((inputName==null)||inputPsw==null) {
                    tipDialog.dismiss();

                    clearEdit();
                    Toast.makeText(MainActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    //读取用户密码，账号
                    address = addresslogin + "username="+inputName
                            +"&password="+inputPsw+"&mobileLogin=true&login=1";

                    //


                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");

                    requestBody = RequestBody.create(JSON,"");

                    //交互
                    HttpUtil.sendPostRequest(address, requestBody,"", new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tipDialog.dismiss();
                                    Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, okhttp3.Response response) throws IOException {
                            final String responseText = response.body().string();
                            user = Utility.handleUserResponse(responseText);
                            list_menu  = new ArrayList<UserMenu>();

                            /*测试*/
                            try {
                                JSONObject jsonObject = new JSONObject(responseText);
                                JSONArray jsonArray = jsonObject.getJSONArray("menuList");
                                //获取完menulist

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject menuObject = jsonArray.getJSONObject(i);
                                    UserMenu userMenu = new UserMenu();

                                   // if (menuObject.getString("target") == "mobile") {
                                        userMenu.setName(menuObject.getString("name"));
                                        list_menu.add(userMenu);
                                    // }
                                }
                                //return list;
                            } catch (Exception e) {
                                e.printStackTrace();
                                //return null;
                            }

                            /*测试*/

                          //  list_menu  = Utility.handleUserMenuResponse(responseText);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tipDialog.dismiss();

                                    if (user.getSessionid()==null){
                                        Toast.makeText(MainActivity.this,"账号密码错误",Toast.LENGTH_SHORT).show();
                                        clearEdit();


                                    }else {
                                        //测试
                                        //String a = list_menu.get(0).getName();


                                        editor = getSharedPreferences("user",MODE_PRIVATE).edit();
                                        editor.putString("loginName",user.getLoginName());
                                        editor.putString("sessionId",user.getSessionid());
                                        editor.putString("roleName",user.getRoleName());
                                        editor.putString("name",user.getName());
                                        editor.putString("email",user.getEmail());
                                        editor.putString("phone",user.getPhone());
                                        editor.putString("mobile",user.getMobile());
                                        editor.putString("no",user.getNo());


                                        editor.putString("mobile_1","");
                                        editor.putString("mobile_2","");
                                        editor.putString("mobile_3","");
                                        editor.putString("mobile_4","");
                                        editor.putString("mobile_5","");
                                        editor.putString("mobile_6","");
                                        editor.putString("mobile_7","");
                                        editor.putString("mobile_8","");
                                        editor.putString("mobile_9","");
                                        editor.putString("mobile_10","");
                                        editor.putString("mobile_11","");

                                        if(!list_menu.isEmpty()) {
                                            for (UserMenu item : list_menu) {
                                                switch (item.getName()) {
                                                    case "钢筋登记":
                                                        editor.putString("mobile_1", item.getName());
                                                        break;
                                                    case "构件自检":
                                                        editor.putString("mobile_2", item.getName());
                                                        break;
                                                    case "构件抽检":
                                                        editor.putString("mobile_3", item.getName());
                                                        break;
                                                    case "模具抽检":
                                                        editor.putString("mobile_4", item.getName());
                                                        break;
                                                    case "内运计划":
                                                        editor.putString("mobile_5", item.getName());
                                                        break;
                                                    case "内运审核":
                                                        editor.putString("mobile_6", item.getName());
                                                        break;
                                                    case "实际内运":
                                                        editor.putString("mobile_7", item.getName());
                                                        break;
                                                    case "发货计划":
                                                        editor.putString("mobile_8", item.getName());
                                                        break;
                                                    case "构件报废":
                                                        editor.putString("mobile_9", item.getName());
                                                        break;
                                                    case "发货登记":
                                                        editor.putString("mobile_10", item.getName());
                                                        break;
                                                    case "收货登记":
                                                        editor.putString("mobile_11", item.getName());
                                                        break;

                                                }
                                            }
                                        }

                                        editor.commit();
                                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                        intent.putExtra("role",user.getRoleName());
                                        startActivity(intent);
                                    }

                                }
                            });
                        }
                    });

                }
            }
        });
    }
    private void initTopBar() {
        mTopBar.setBackgroundColor(ContextCompat.getColor(this, R.color.app_color_blue_2));

        mTopBar.setTitle("账号登录");
    }

    private void clearEdit(){
        editName.getText().clear();
        editPsw.getText().clear();
    }


}
