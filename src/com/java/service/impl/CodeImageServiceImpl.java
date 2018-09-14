package com.java.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;

import com.java.service.CodeImageService;
import com.java.utils.StringUtils;

@Controller
public class CodeImageServiceImpl implements CodeImageService {

	/**
	 * 生成图片二维码
	 */
	@Override
	public String getCodeImage(String key) throws IOException {
		String code = StringUtils.getRandom(4);
		Random random = new Random();
		int width = 60;
		int height = 40;
		
		Color color = getRandomColor();
		
		Color colorReserve = getReserveColor(color);
		
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D graphics2d = bufferedImage.createGraphics();
		graphics2d.setColor(color);
		graphics2d.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));//设置字体
		graphics2d.fillRect(0, 0, width, height);
		graphics2d.setColor(colorReserve);
		graphics2d.drawString(code, 5, 25);
		
		//绘制干扰点
		for (int i = 0,n=random.nextInt(30); i < n; i++) {
			graphics2d.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
		}
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", out);
		//进行base64转换
		String base64 = Base64Utils.encodeToString(out.toByteArray());
		out.close();
		return base64;
	}

	private Color getReserveColor(Color color) {
		return new Color(255-color.getRed(),255-color.getGreen(),255-color.getBlue());
	}

	private Color getRandomColor() {
		Random random = new Random();
		return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
	}

}
