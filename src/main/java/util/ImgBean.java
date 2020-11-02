package util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**项目中使用
 * 将文字和二维码合并
 */
public class ImgBean {
    public void ImgBean(){}

    /**
     *
     * @param s  与图片合成的文字
     * @param ImgName   生成图片的路径
     */
    public static void ImgYin(String s,String ImgName){
        byte[] bytes = null;
        try{
            String str = s;
            File _file = new File(ImgName);
            Image src = ImageIO.read(_file);
            int wideth=src.getWidth(null);
            int height=src.getHeight(null);
            BufferedImage image=new BufferedImage(wideth,height,BufferedImage.TYPE_INT_RGB);
            Graphics g=image.createGraphics();
            g.drawImage(src,0,0,wideth,height,null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("宋体",Font.PLAIN,20));
            Font aa=new Font("宋体",Font.PLAIN,20);
            //调整文字出现的位置    也就是调整  HN2001801-YS20045001 的位置
            g.drawString(str,wideth-350,height-20);
            g.dispose();
            ByteArrayOutputStream out1 = new ByteArrayOutputStream();
           /* saveImage(image, out1);*/
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out1);
            encoder.encode(image);
            bytes = out1.toByteArray();
            out1.close();
            FileOutputStream out2 = new FileOutputStream(ImgName);
            out2.write(bytes);
            out2.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void saveImage(BufferedImage img, OutputStream out1) throws Exception {
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out1);
        encoder.encode(img);
    }

    public static void main(String[] args){
        ImgYin("HN2001801-YS20045003" , "D:/twocode3/MyQRCode3.png");
    }
}
