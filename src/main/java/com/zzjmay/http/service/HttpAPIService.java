package com.zzjmay.http.service;

import com.zzjmay.http.HttpResult;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClient的service
 * Created by zzjmay on 2019/2/28.
 */
@Service
public class HttpAPIService {

    @Resource(name = "voteHttpClient")
    private CloseableHttpClient voteHttpClient;

    @Autowired
    private RequestConfig config;


    public String doGet(String url) throws Exception{
        HttpGet httpGet = new HttpGet(url);

        httpGet.setConfig(config);

        CloseableHttpResponse response = voteHttpClient.execute(httpGet);

        if(response.getStatusLine().getStatusCode() == 200){
            //返回200，返回响应内容
            return EntityUtils.toString(response.getEntity(),"UTF-8");
        }

        return null;
    }


    public String doGet(String url,Map<String,Object> map) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);

        if(map != null){
            for (Map.Entry<String,Object> entry:map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(),entry.getValue().toString());
            }
        }

        return this.doGet(uriBuilder.build().toString());
    }

    /**
     * 带参数的post请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url, Map<String, Object> map) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);

        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (map != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");

            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }

        // 发起请求
        CloseableHttpResponse response = this.voteHttpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }







}
