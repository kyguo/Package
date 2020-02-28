package com.didichuxing.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.InputStream;

public class Test1 {
    public static void main(String[] args) throws Exception {
        String url = "https://restapi.amap.com/v3/geocode/regeo?output=json&location=116.310003,39.991957&key=b9c3dc795effd8bc01a19c12e95154aa&radius=1000&extensions=base";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String province = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpGet);
            //获取返回码
            int status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                //获取reponse字符串
                String jsonStr = EntityUtils.toString(response.getEntity());
                //将字符串解析成JsonObject
                JSONObject jsonObject = JSON.parseObject(jsonStr);
                //获取key为regeocode的body，并以JsonObject形式返回
                JSONObject regeocode = jsonObject.getJSONObject("regeocode");
                //若返回不为空，则解析出城市
                if (regeocode != null && !regeocode.isEmpty()) {
                    JSONObject addressComponent = regeocode.getJSONObject("addressComponent");
                    province = addressComponent.getString("province");
                }
            }
        } finally {
            httpclient.close();
        }


    }

}






