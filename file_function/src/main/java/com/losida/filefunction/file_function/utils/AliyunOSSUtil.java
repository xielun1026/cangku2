package com.losida.filefunction.file_function.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.losida.filefunction.file_function.pojo.ConstantProperties;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AliyunOSSUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AliyunOSSUtil.class);


    public static String upload(File file) {
        logger.info("=========>OSS文件上传开始：" + file.getName());
        String endpoint = ConstantProperties.JAVA4ALL_END_POINT;
        String accessKeyId = ConstantProperties.JAVA4ALL_ACCESS_KEY_ID;
        String accessKeySecret = ConstantProperties.JAVA4ALL_ACCESS_KEY_SECRET;
        String bucketName = ConstantProperties.JAVA4ALL_BUCKET_NAME1;
        String fileHost = ConstantProperties.JAVA4ALL_FILE_HOST;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());

        if (null == file) {
            return null;
        }

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            //容器不存在，就创建
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            //创建文件路径
            String fileUrl = fileHost + "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());
            //上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file));
            //设置权限 这里是公开读
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (null != result) {
                logger.info("==========>OSS文件上传成功,OSS地址：" + fileUrl);
                return fileUrl;
            }
        } catch (OSSException oe) {
            logger.error(oe.getMessage());
        } catch (ClientException ce) {
            logger.error(ce.getMessage());
        } finally {
            //关闭
            ossClient.shutdown();
        }
        return null;
    }

    //删除文件
    public static void deleteFile(String fileName) {
        String endpoint = ConstantProperties.JAVA4ALL_END_POINT;
        String accessKeyId = ConstantProperties.JAVA4ALL_ACCESS_KEY_ID;
        String accessKeySecret = ConstantProperties.JAVA4ALL_ACCESS_KEY_SECRET;
        String bucketName = ConstantProperties.JAVA4ALL_BUCKET_NAME1;

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId,accessKeySecret);
        ossClient.deleteObject(bucketName, fileName);
        ossClient.shutdown();
    }

    //下载文件
    /* 从OSS服务中下载所需文件到本地临时文件
     param endponit oss对外服务的域名
     param accessKeyId 用户身份认证标识
     param accessKeySecret 用于加密签名字符串，oss用来验证签名字符串的秘钥
     param bucketName 要访问的存储空间
     param objectNames 要下载的对象/文件
     return*/
    public static String downloadOSS(List<String> objectNames,String pathName){
        String endpoint = ConstantProperties.JAVA4ALL_END_POINT;
        String accessKeyId = ConstantProperties.JAVA4ALL_ACCESS_KEY_ID;
        String accessKeySecret = ConstantProperties.JAVA4ALL_ACCESS_KEY_SECRET;
        String bucketName = ConstantProperties.JAVA4ALL_BUCKET_NAME1;

        OSSClient ossClient = null;
        String filePath="";
        try{
            //创建OSSClient实例，用于操作oss空间
            ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            for (String objectName:objectNames){
                //指定文件保存路径
               // filePath = pathName+"/"+objectName.substring(0,objectName.lastIndexOf("/"));
                filePath =pathName;
                //判断文件目录是否存在，不存在则创建
                File file = new File(filePath);
                if (!file.exists()){
                    file.mkdirs();
                }
                //判断保存文件名是否加后缀
                if (objectName.contains(".")){
                    //指定文件保存名称
                    filePath = filePath+"/"+objectName.substring(objectName.lastIndexOf("/")+1);
                }

                //获取OSS文件并保存到本地指定路径中，此文件路径一定要存在，若保存目录不存在则报错，若保存文件名已存在则覆盖本地文件
               ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(filePath));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            //关闭oss连接
            if (ossClient != null){
                ossClient.shutdown();
            }
        }
        return pathName;
    }

    public static void zipDir(ZipArchiveOutputStream zipos, File file, String baseDir) throws IOException {
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File f:files){
                //对文件进行递归判断
                zipDir(zipos,f,baseDir+file.getName()+File.separator);
            }
        }else{
            //将文件封装成压缩项
            //根据文件创建zip内容实体
            ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file,baseDir+file.getName());
            //将根据文件创建的实体保存到压缩流中
            zipos.putArchiveEntry(zipArchiveEntry);
            //将内容输出到压缩文件中
            zipos.write(FileUtils.readFileToByteArray(file));
            zipos.closeArchiveEntry();
        }
    }

    /**
     * 文件及文件夹的递归删除
     * @param file
     */
    public static void deleteFile(File file){
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File f: files){
                deleteFile(f);
                //将循环后的空文件夹删除
                if(f.exists()){
                    f.delete();
                }
            }
        }else{
            file.delete();
        }
    }
}