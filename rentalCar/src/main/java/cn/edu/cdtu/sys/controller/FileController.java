package cn.edu.cdtu.sys.controller;

import cn.edu.cdtu.bus.domain.DataGridView;
import cn.edu.cdtu.sys.utils.AppFileUtils;
import cn.edu.cdtu.sys.utils.RandomUtils;
import cn.edu.cdtu.sys.utils.SysConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/file/")
@Controller
public class FileController {

    /**
     * 图片下载  downloadShowFile
     * 参数： 参数 String path , Response
     * 返回值: ResponseEntity   是springmvc提供的用于下载的一个实体
     */
    @RequestMapping("downloadShowFile")
    public ResponseEntity<Object> downloadShowFile(String path, HttpServletResponse response){
        //使用工具类，调用下载方法
        ResponseEntity<Object> responseEntity = AppFileUtils.downloadFile(response, path, "");
        return responseEntity;
    }

    /**
     * 添加
     *
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping("uploadFile")
    @ResponseBody
    public DataGridView uploadFile(MultipartFile mf) throws IllegalStateException, IOException {
        // 文件上传的父目录
        String parentPath = AppFileUtils.PATH;
        // 得到当前日期作为文件夹名称
        String dirName = RandomUtils.getCurrentDateForString();
        // 构造文件夹对象
        File dirFile = new File(parentPath, dirName);
        if (!dirFile.exists()) {
            dirFile.mkdirs();// 创建文件夹
        }
        // 得到文件原名
        String oldName = mf.getOriginalFilename();
        // 根据文件原名得到新名
        String newName = RandomUtils.createFileNameUseTime(oldName, SysConstant.FILE_UPLOAD_TEMP);
        File dest = new File(dirFile, newName);
        mf.transferTo(dest);

        Map<String,Object> map=new HashMap<>();
        map.put("src", dirName+"/"+newName);
        return new DataGridView(map);

    }


}
