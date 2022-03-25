package com.cay.rockstock.redis;

import com.google.common.base.Stopwatch;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


@SpringBootTest
public class Base64ImageTest {
    @Resource
    private Jedis jedis;

    @Test
    public void testImage() {
        URL url = null;
        try {
            url = new URL("https://img-blog.csdn.net/20150629101409290");
            encodeImageToBase64(url);
            decodeBase64ToImage("/Users/houqing/project/", "football.jpg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }


    /**
     * 将网络图片进行Base64位编码，并存储到redis
     */
    public void encodeImageToBase64(URL imageUrl) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageUrl);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(outputStream.toByteArray());
            System.out.println(encode.length());
            jedis.set("image_test", encode, SetParams.setParams().ex(100L));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }


    /**
     * 从redis读数据，将Base64位编码的图片进行解码，并保存到指定目录
     */
    public void decodeBase64ToImage(String path, String imgName) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        String image = jedis.get("image_test");
        System.out.println(image.length());
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] decoderBytes = decoder.decodeBuffer(image);
            FileOutputStream write = new FileOutputStream(path + imgName);
            write.write(decoderBytes);
            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

}