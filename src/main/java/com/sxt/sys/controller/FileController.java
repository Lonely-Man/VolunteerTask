package com.sxt.sys.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sxt.sys.common.AppFileUtils;
import com.sxt.sys.common.WangEditor;

import cn.hutool.core.date.DateUtil;

@RestController
@RequestMapping("/file")
public class FileController {
	/**
	 * 文件上传
	 */
	@RequestMapping("uploadFile")
	public Map<String, Object> uploadFile(MultipartFile mf) {
		// 1,得到文件名
		String oldName = mf.getOriginalFilename();
		// 2,根据文件名生成新的文件名
		String newName = AppFileUtils.createNewFileName(oldName);
		// 3,得到当前日期的字符串
		String dirName = DateUtil.format(new Date(), "yyyy-MM-dd");
		// 4,构造文件夹
		File dirFile = new File(AppFileUtils.UPLOAD_PATH, dirName);
		// 5,判断当前文件夹是否存在
		if (!dirFile.exists()) {
			dirFile.mkdirs();// 创建文件夹
		}
		// 6,构造文件对象
		File file = new File(dirFile, newName + "_temp");
		// 7,把mf里面的图片信息写入file
		try {
			mf.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("path", dirName + "/" + newName + "_temp");
		return map;
	}

	/**
	 * 文件下载
	 */
	@RequestMapping("showImageByPath")
	public ResponseEntity<Object> fileDownload(String path) {
		return AppFileUtils.createResponseEntity(path);
	}

	/**
	 * wangeditor图片上传
	 * @param multipartFile
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "editorUpload", method = RequestMethod.POST)
	@ResponseBody
	public WangEditor uploadEditor(@RequestParam("myFile") MultipartFile multipartFile, HttpServletRequest request) {
		try {
			// 获取项目路径
			InputStream inputStream = multipartFile.getInputStream();
			// 服务器根目录的路径
			String path = "C:/upload";

			// 根目录下新建文件夹upload，存放上传图片
			String uploadPath = path + "/noticeImg/";
			// 获取文件名称
			String filename = getFileName();
			// 将文件上传的服务器根目录下的upload文件夹
			File file = new File(uploadPath, filename);
			FileUtils.copyInputStreamToFile(inputStream, file);
			// 相对路径
			String url = "/Path/noticeImg/" + filename;
			String[] str = { url };
			WangEditor we = new WangEditor(0, str);
			return we;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public String getFileName() {
		// 日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// 获取当前时间并格式化
		String timeStr = sdf.format(new Date());
		// 5个String 随机字符串 右边的范围
		String str = RandomStringUtils.random(5, "abcdefghijklmnopqrstuvwxyz1234567890");
		// 字符串拼接
		String name = timeStr + str + ".jpg";
		return name;
	}

}
