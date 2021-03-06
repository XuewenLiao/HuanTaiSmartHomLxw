package huantai.smarthome.control;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gizwits.gizwifisdk.api.GizWifiDevice;
import com.gizwits.gizwifisdk.enumration.GizWifiErrorCode;
import com.gizwits.gizwifisdk.listener.GizWifiDeviceListener;

import org.json.JSONException;

import java.util.concurrent.ConcurrentHashMap;

import huantai.smarthome.adapter.MyFragmentPagerAdapter;
import huantai.smarthome.bean.ControlDataible;
import huantai.smarthome.initial.R;
import huantai.smarthome.utils.ConvertUtil;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class HomeFragment0 extends Fragment implements ControlDataible{

    private RadioGroup rg_tab_bar;
    private RadioButton rb_channel;
    private RadioButton rb_message;
    private RadioButton rb_better;
    private RadioButton rb_setting;
    private ViewPager vpager;

    private MyFragmentPagerAdapter mAdapter;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;


    /** The tv MAC */
    private TextView tvMAC;

    /** The GizWifiDevice device */
    private GizWifiDevice device;

    /** The ActionBar actionBar */
    ActionBar actionBar;

    //发送状态更新广播
    private static final String KUOZHAN = "kuozhan";
    private byte[] SEND_BROAD = {(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x15};

    private Button btn_send;

    //	@ViewInject(R.id.tv_msg)
    private TextView tv_msg;
    private final int RETURN_MSG=1;

    private String msg;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container, false);
        initDevice();
        initView();
        //开启广播
        btn_send=(Button)view.findViewById(R.id.btn_send);
        tv_msg = (TextView)view.findViewById(R.id.tv_msg);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initBroadreceive();
            }
        });
        initStatusListener();

        return view;
    }



    @Override
    public void initStatusListener() {
                //设置设备状态监听
        device.setListener(mListener);
        //通知设备上报数据
        initBroadreceive();
    }

    @Override
    public void initView() {
        tvMAC = (TextView) view.findViewById(R.id.tvMAC);
        if (null != device) {

            tvMAC.setText(device.getMacAddress().toString());

        }
    }

    @Override
    public void initBroadreceive() {
        try {
            sendJson(KUOZHAN, SEND_BROAD);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initDevice() {
        Intent intent = getActivity().getIntent();
        device = (GizWifiDevice) intent.getParcelableExtra("GizWifiDevice");
        Log.i("Apptest", device.getDid());
    }
    @Override
    public void sendJson(String key, Object value) throws JSONException{
        ConcurrentHashMap<String, Object> hashMap = new ConcurrentHashMap<String, Object>();
        hashMap.put(key, value);
        device.write(hashMap, 0);
        Log.i("==", hashMap.toString());
    }

    /**
     * description:获取设备状态
     * auther：joahluo
     * time：2017/6/27 15:53
     */
    private GizWifiDeviceListener mListener = new GizWifiDeviceListener() {
        @Override
        public void didReceiveData(GizWifiErrorCode result, GizWifiDevice device, ConcurrentHashMap<String, Object> dataMap, int sn) {
            if (result == GizWifiErrorCode.GIZ_SDK_SUCCESS) {
                // 已定义的设备数据点，有布尔、数值和枚举型数据
                if (dataMap.get("data") != null) {
                    ConcurrentHashMap<String, Object> map = (ConcurrentHashMap<String, Object>) dataMap.get("data");
                    // 获得kuozhan类型数据
//                    String msg = ConvertUtil.byteStringToHexString((byte[]) map.get("Temperature"));

                    Log.i("ListenerMsg",msg);
                    tv_msg.setText(msg);
                    System.out.println("接收到数据："+msg);
                }
            }

        }

    };
}
