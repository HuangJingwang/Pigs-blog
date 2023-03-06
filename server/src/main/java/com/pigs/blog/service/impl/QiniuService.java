package com.pigs.blog.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pigs.blog.common.Constants;
import com.pigs.blog.utils.FileUtil;
import com.qiniu.cdn.CdnManager;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class QiniuService {
    private static final Logger logger = LoggerFactory.getLogger(QiniuService.class);
    //上传到七牛后保存的文件名

    //密钥配置
    Auth auth = Auth.create(Constants.ACCESS_KEY, Constants.SECRET_KEY);

    Configuration cfg = new Configuration(Zone.zone2());
    // ...其他参数参考类注释
    UploadManager uploadManager = new UploadManager(cfg);

    // 简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(Constants.bucketName);
    }

    public String saveImage(MultipartFile file) throws IOException {
        try {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                logger.error("上传图片，图片名字异常");
                return null;
            }
            int dotPos = originalFilename.lastIndexOf(".");
            if (dotPos < 0) {
                return null;
            }
            String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
            // 判断是否是合法的文件后缀
            if (!FileUtil.isFileAllowed(fileExt)) {
                return null;
            }

            //使用UUID是为了多个相同文件名称可以上传
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;

            // 调用put方法上传
            Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
            // 打印返回的信息
            if (res.isOK() && res.isJson()) {
                // 返回这张存储照片的地址
                return Constants.QINIU_IMAGE_DOMAIN + JSONObject.parseObject(res.bodyString()).get("key");
            } else {
                logger.error("七牛异常:" + res.bodyString());
                return null;
            }
        } catch (QiniuException e) {
            // 请求失败时打印的异常的信息
            logger.error("七牛异常:" + e.getMessage());
            return null;
        }
    }

    public void deleteImage(List<String> list) {
        BucketManager bucketManager = new BucketManager(auth, cfg);

        for (String s : list) {
            try {
                if(!s.equals(Constants.DEFAULT_ARTICLE_IMG)) { //文章默认图片删除路径验证
                    
                    // 若传来的url直接是文件名 则无须用replace去除前面的域名了
                    bucketManager.delete(Constants.bucketName, s.replaceAll(Constants.QINIU_IMAGE_DOMAIN,""));

                    // 每次删除就刷新一下
                    refresh(s);
                }
            } catch (QiniuException e) {
                logger.error("delete picture which url:{" + s + "} fail");
                e.printStackTrace();
            }
        }
    }

    //刷新文件
    public void refresh(String url) {
        String[] urls = {url};
        CdnManager c = new CdnManager(auth);
        try {
            c.refreshUrls(urls);
        } catch (QiniuException e) {
            logger.error("refresh picture fail");
            e.printStackTrace();
        }
    }
}
