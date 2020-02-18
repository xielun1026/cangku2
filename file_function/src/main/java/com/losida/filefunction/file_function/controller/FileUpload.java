package com.losida.filefunction.file_function.controller;

import com.losida.filefunction.file_function.utils.AliyunOSSUtil;
import jdk.nashorn.internal.runtime.options.LoggingOption;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/file")
public class FileUpload {
    //文件的上传
    @ResponseBody
    @RequestMapping("/upload")
    public String fileUpload(MultipartFile file, String projectName, String groupName) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("文件上传");

        try {
            if (file != null) {
                //获取文件名字
                String originalFilename = file.getOriginalFilename();
                //文件名字不为空
                if (!"".equals(originalFilename.trim())) {
                    File file1 = new File(originalFilename);
                    FileOutputStream fileOutputStream = new FileOutputStream(file1);
                    fileOutputStream.write(file.getBytes());
                    fileOutputStream.close();
                    file.transferTo(file1);
                    AliyunOSSUtil.upload(file1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "123";
    }

    @ResponseBody
    @RequestMapping("/downLoad")
    public String downLoad(String fileName,String zipName ,String savePath) {
        List<String> strings = new ArrayList<>();
        strings.add(fileName);
        if (fileName == null || StringUtils.isBlank(savePath)) {
            return "文件名不存在";
        }
        File file=null;
        try {
            String pathName = AliyunOSSUtil.downloadOSS(strings, savePath);
            //获取待压缩文件源
            file = new File(pathName);
            //指定压缩文件存放路径
            File file1 = new File("F:/文件");
            file1.mkdir();
            String zipFileName = file1 + "/"+zipName + ".zip";
            File zipFile = new File(zipFileName);
            //构建输出流
            FileOutputStream fout = new FileOutputStream(zipFile);
            //构建压缩输出流
            ArchiveOutputStream aos = new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.ZIP, fout);
            //判断获取的压缩实例是否为zip格式
            if (aos instanceof ZipArchiveOutputStream) {
                //进行压缩实例强转
                ZipArchiveOutputStream zipos = (ZipArchiveOutputStream) aos;

                //将指定文件封装成压缩项，添加到压缩流中
                //判断文件是否存在
                if (file1.exists()) {
                    //判断文件类型，调用文件处理方法
                    AliyunOSSUtil.zipDir(zipos, file, "");
                }
            }
            //关闭流
            aos.flush();
            aos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            //删除文件源，只保留zip文件
            if (file.exists()) {
                //判断文件类型
                if (file.isDirectory()) {
                    //对文件夹进行处理递归删除（有内容的文件夹不能直接被删除）
                    AliyunOSSUtil.deleteFile(file);
                    file.delete();//删除空文件夹
                } else {
                    file.delete();//文件直接删除
                }
            }
        }
        return"下载成功";
}

    @RequestMapping("/main")
    public ModelAndView index() {
        //return "redirect:http://127.0.0.1:8087/index.html";
        return new ModelAndView("index");
    }
}
