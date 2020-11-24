package com.sxt.sys.common;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 文件上传下载工具类
 */
public class AppFileUtils {
    //文件上传的默认保存路径
    public static String UPLOAD_PATH = "c:/upload/";


    static {
        //读取配置文件的存储地址
        InputStream stream = AppFileUtils.class.getClassLoader().getResourceAsStream("file.properties");
        Properties properties=new Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String property = properties.getProperty("filepath");
        if(null!=property) {
            UPLOAD_PATH=property;
        }
    }

    /**
     * 根据文件老名字得到新名字
     */
    public static String createNewFileName(String oldName){
        String substr = oldName.substring(oldName.lastIndexOf("."), oldName.length());
        return IdUtil.simpleUUID().toUpperCase()+substr;
    }

    /**
     * 文件下载
     */
    public static ResponseEntity<Object> createResponseEntity(String path){
        //1,构造文件对象
        File file = new File(UPLOAD_PATH,path);
        if (file.exists()){
            //将下载的文件,封装byte[]
            byte[] bytes= null;
            bytes = FileUtil.readBytes(file);
            //创建封装响应头信息的对象
            HttpHeaders header=new HttpHeaders();
            //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件的名称
//			header.setContentDispositionFormData("attachment", "123.jpg");
            //创建ResponseEntity对象
            ResponseEntity<Object> entity=
                    new ResponseEntity<Object>(bytes, header, HttpStatus.CREATED);
            return entity;
        }
        return null;
    }

    /**
     * 根据路径改名字 去掉_temp
     * @param imgpath
     * @return
     */
    public static String renameFile(String imgpath) {
        File file = new File(UPLOAD_PATH,imgpath);
        String replace = imgpath.replace("_temp", "");
        if (file.exists()){
            file.renameTo(new File(UPLOAD_PATH,replace));
        }
        return replace;
    }
    //根据老路径删除图片
    public static void removeFilePath(String oldPath) {
        File file = new File(UPLOAD_PATH,oldPath);
        if (file.exists()){
            file.delete();
        }
    }
}
