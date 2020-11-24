package com.sxt.sys.task;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sxt.sys.common.AppFileUtils;

import cn.hutool.core.date.DateUtil;

/**
 * 定时器  删除当天的_temp文件
 * @author xuebo_huang
 *
 */
@Component
public class RecyleTempFileTask {

	/*
	 * 每天晚上12点执行
	 */
	@Scheduled(cron = "0 0 0 * * ? ")
	public void recyleTempFile() {
		// 得到当前日期的字符串
		String dirName = DateUtil.format(new Date(), "yyyy-MM-dd");
		// 构造文件夹
		File dirFile = new File(AppFileUtils.UPLOAD_PATH, dirName);
		deleteFile(dirFile);
		// 获取当前时间
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("当前时间为:" + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	}

	/*
	 * 删除图片
	 */
	private void deleteFile(File file) {

		if (null != file) {
			File[] listFiles = file.listFiles();
			if (null != listFiles && listFiles.length > 0) {
				for (File f : listFiles) {
					if (f.isFile()) {
						if (f.getName().endsWith("_temp")) {
							f.delete();
						}
					}
				}
			}
		}
	}
}
