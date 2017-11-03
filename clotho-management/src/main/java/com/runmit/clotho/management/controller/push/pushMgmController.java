package com.runmit.clotho.management.controller.push;

import com.alibaba.fastjson.JSONObject;
import com.runmit.clotho.core.domain.push.Broadcast;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sjc
 * @date 2017-05-31-16:39
 */
@RestController
@RequestMapping(value = "/push")
public class pushMgmController {
    private static final Logger LOGGER = LoggerFactory.getLogger(pushMgmController.class);

    @Value("${broadcast.url}")
    private String broadcastUrl;

    @RequestMapping(value = "/broadcast.do", method = RequestMethod.POST)
    private ExtStatusEntity saveBroadcast(Broadcast broadcast) {

        ExtStatusEntity extStatusEntity = new ExtStatusEntity();
        LOGGER.info("broadcastUrl is "+broadcastUrl);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(broadcastUrl);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("content", broadcast.getContent()));
        params.add(new BasicNameValuePair("appID", broadcast.getAppID()));
        params.add(new BasicNameValuePair("offline", Boolean.toString(broadcast.isOffline())));
        /*if (broadcast.getKeyValues() != null && !"".equals(broadcast.getKeyValues())){
            String kvs = broadcast.getKeyValues().substring(0, broadcast.getKeyValues().length()-1);
            LOGGER.info(kvs);
            params.add(new BasicNameValuePair("keyValues", kvs));
        }*/
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//            ObjectMapper mapper = new ObjectMapper();
//            String json = mapper.writeValueAsString(broadcast);
//            LOGGER.info("broadcast is " + json);
//            StringEntity se = new StringEntity(json, ContentType.APPLICATION_FORM_URLENCODED);
//            httpPost.setEntity(se);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            LOGGER.info("statusCode is " + statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(entity));
                LOGGER.info("result:" + jsonObject.toString());
                int returnCode = jsonObject.getIntValue("rtn");
                LOGGER.info("returnCode is " + returnCode);
                if (returnCode == 0) {
                    LOGGER.info("推送成功！");
                    extStatusEntity.setMsg("推送成功");
                    extStatusEntity.setSuccess(true);
                }else{
                    LOGGER.info("推送失败！");
                    extStatusEntity.setMsg("推送失败");
                    extStatusEntity.setSuccess(false);
                }
            }
        } catch (IOException | IllegalStateException e) {
            LOGGER.error("Exception by broadcast appeared ", e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException ex) {
                LOGGER.error("Exception by broadcast appeared ", ex);
            }
        }
        return extStatusEntity;
    }
}
