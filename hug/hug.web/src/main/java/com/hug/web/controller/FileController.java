package com.hug.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hug.core.BZConstant;
import com.hug.core.string.BZString;

/**
 * @Description 文件上传
 * @author chenjian
 * @date 2018年9月1日 下午3:37:45
 */
@Controller
@RequestMapping("/file")
public class FileController {

	/**
	 * @Description 上传文件
	 * @author chenjian
	 * @date 2018年9月1日 下午4:16:41
	 */
	@ResponseBody
	@RequestMapping("/upload/{id}")
	public boolean upload(@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable String id,
			HttpServletRequest request) {
		File[] files = new File(BZConstant.pathPrefix).listFiles();
		for (File delFile : files) {
			if (BZString.getFilePrefix(delFile.getName()).equals(id)) {
				delFile.delete();
				break;
			}
		}
		String fileName = id + BZString.getFileSuffix(file.getOriginalFilename());
		File targetFile = new File(BZConstant.pathPrefix, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/view/{id}")
	public String getpic(@PathVariable String id, HttpServletResponse response) throws IOException {
		response.setContentType("*/*"); // 设置返回内容格式
		File[] files = new File(BZConstant.pathPrefix).listFiles();
		for (File file : files) {
			if (BZString.getFilePrefix(file.getName()).equals(id)) {
				InputStream in = new FileInputStream(file); // 用该文件创建一个输入流
				OutputStream os = response.getOutputStream(); // 创建输出流
				byte[] b = new byte[1024];
				while (in.read(b) != -1) {
					os.write(b);
				}
				in.close();
				os.flush();
				os.close();
				break;
			}
		}
		return null;
	}

}
