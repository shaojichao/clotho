package com.runmit.clotho.management.service;

import com.google.gson.Gson;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.meta.FileAuthority;
import com.qcloud.cos.request.CreateFolderRequest;
import com.qcloud.cos.request.UpdateFileRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;
import com.runmit.clotho.core.util.COSConstants;
import com.runmit.clotho.core.util.DateUtils;
import com.runmit.clotho.core.util.FileUtils;
import lombok.extern.log4j.Log4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by scott on 6/16/17.
 */
@Service
@Log4j
public class COSService {

    @Value("${cos.appid}")
    private long appid;
    @Value("${cos.secretid}")
    private String secretid;
    @Value("${cos.secrekey}")
    private String secrekey;
    @Value("${cos.bucketname}")
    private String bucketName;
    @Value("${file.upload.path}")
    private String uploadPath;
    @Value("${file.download.url}")
    private String downloadUrl;

    private Credentials cred;
    private COSClient cosClient;

    @PostConstruct
    private void init() {
        cred = new Credentials(appid, secretid, secrekey);
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setRegion("bj");
        cosClient = new COSClient(clientConfig, cred);
    }

    public int createFolder(String cosPath, String desc) { //slash must be the first and last character is cosPath, the first slash means the root path
        CreateFolderRequest createFolderRequest = new CreateFolderRequest(bucketName, cosPath);
        createFolderRequest.setBizAttr(desc);
        String createFolderRet = cosClient.createFolder(createFolderRequest);
        JSONObject json = new JSONObject(createFolderRet);
        return json.getInt("code");
    }

    public void uploadFile(MultipartFile file, HttpServletResponse response, HttpServletRequest request) throws IOException {
        String result = null;
        Writer writer = response.getWriter();
        String str = file.getOriginalFilename();
        // 将文件名进行改变，以防止覆盖
        String fileName = UUID.randomUUID().toString().replace("-", "") + str.substring(str.lastIndexOf("."));
        String tempPath = DateUtils.getDateString(new Date(), "yyyyMMdd") + "/";
        String cosPath = COSConstants.PACKAGE_PATH_PREFIX + "/" + tempPath + fileName;
        String path = uploadPath + tempPath;
        FileUtils.ensureDirExist(path);
        try {
            byte[] bytes = file.getBytes();
            log.info("==="+bytes.length);
            File fileNew = new File(path + fileName);
            file.transferTo(fileNew);
            FileInputStream fis = new FileInputStream(fileNew);
            String md5 = DigestUtils.md5Hex(fis);
            fis.close();
            UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, cosPath, bytes);
            uploadFileRequest.setEnableShaDigest(false);
            uploadFileRequest.setEnableSavePoint(false);
            //上传文件请求, 对小文件(8MB以下)使用单文件上传接口, 大文件使用分片上传接口, 这个uploadFile自动切换了，不用限制大小
            //单文件不超过20M
            log.info("上传服务器成功，开始上传腾讯云" + DateUtils.getDateTime(new Date()));
            result = cosClient.uploadFile(uploadFileRequest);
            log.info("result:" + result);
            log.info("完成腾讯云上传：" + DateUtils.getDateTime(new Date()));
            if (!"".equals(result)) {
                JSONObject json = new JSONObject(result);
                if (json.getInt("code") == 0) {//表示成功
                    log.info("文件上传成功：" + json);
                    String jsonObject = json.getJSONObject("data").toString();
                    Gson gson = new Gson();
                    Map map = gson.fromJson(jsonObject, Map.class);
                    String source_url = map.get("source_url").toString();
                    log.info("腾讯云地址：" + source_url);
                    response.setContentType("text/html; charset=utf-8");
                    writer.write("{'success': true, 'msg': '" + source_url + "', 'size': '" + file.getSize() + "', 'md5': '" + md5 + "'}");
                    writer.close();
                    writer.flush();
                }
            }
        } catch (Exception e) {
            log.error(e);
            // return null;
        }
        // return null;
    }

    public int updateFile(String filePath, FileAuthority fileAuthority) {
        UpdateFileRequest updateFileRequest = new UpdateFileRequest(bucketName, filePath);
        updateFileRequest.setAuthority(fileAuthority);
        String Ret = cosClient.updateFile(updateFileRequest);
        JSONObject json = new JSONObject(Ret);
        return json.getInt("code");
    }

//    @Transactional(readOnly = true)
//    public int setTrendsPublic(List<Trend> trends) {
//        int ret = 0;
//        for (Trend t : trends) {
//            int inx = StringUtils.ordinalIndexOf(t.getPics(), "/", 3);
//            String path = t.getPics().substring(inx, t.getPics().length());
//            ret += updateFile(path, FileAuthority.WPRIVATERPUBLIC);
//        }
//        return ret;
//    }

    /*public String getSign(String cosPath) {
        long expired = System.currentTimeMillis() / 1000 + expireSecond;
        try {
            return Sign.getDownLoadSign(bucketName, cosPath, cred, expired);
        } catch (AbstractCosException e) {
            return "";
        }
    }*/

    public void test() {

//        UploadFileRequest uploadFileRequest = new UploadFileRequest();


//        Credentials cred = new Credentials(appid, secretid, secrekey);
//        ClientConfig clientConfig = new ClientConfig();
//        clientConfig.setRegion("tj");
        long expired = System.currentTimeMillis() / 1000 + 864000;
//        SignDTO signDTO = new SignDTO();
//        try {
//            String signStr = Sign.getPeriodEffectiveSign(bucketName, cosPath, cred, expired);
//            signDTO.setSign(signStr);
//        } catch (AbstractCosException e) {
//            signDTO.setRtn(ErrorCodes.SIGNERROR.getCode());
//            signDTO.setMsg(ErrorCodes.SIGNERROR.getDesc());
//        }
//        return signDTO;


//        DelFileRequest delFileRequest = new DelFileRequest(bucketName, "/111.jpg");
//        System.out.println(String.format("del 111.jpg result: %s", cosClient.delFile(delFileRequest)));
//
//        GetFileLocalRequest request14 = new GetFileLocalRequest(bucketName, "/4.jpg", "/Users/scott/Desktop/4.jpg");
//        request14.setUseCDN(false);
//        System.out.println(String.format("get 14.jpg result: %s", cosClient.getFileLocal(request14)));
//        GetFileLocalRequest request2 = new GetFileLocalRequest(bucketName, "/2.jpg", "/Users/scott/Desktop/b.jpg");
//        request2.setUseCDN(false);
//        request2.setReferer("www.baidu.com");
//        System.out.println(String.format("get 2.jpg  result: %s", cosClient.getFileLocal(request2)));
//
//        UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, "/111.jpg", "/Users/scott/Desktop/b.jpg");
//        System.out.println(String.format("upload 111.jpg  result: %s", cosClient.uploadFile(uploadFileRequest)));
//
//        UpdateFileRequest updateFileRequest = new UpdateFileRequest(bucketName, "/111.jpg");
//        updateFileRequest.setAuthority(FileAuthority.WPRIVATERPUBLIC);
//        System.out.println(String.format("update 111.jpg  result: %s", cosClient.updateFile(updateFileRequest)));
    }


}
