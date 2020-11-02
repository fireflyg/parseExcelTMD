package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 创建文件夹
 *
 */
public class Files {
    public static void main(String[] args){//主程序，程序入口
        File file=new File("D:\\GBF");
        if(!file.exists()){//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        /*try{//异常处理
            //如果Qiju_Li文件夹下没有Qiju_Li.txt就会创建该文件
            BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\Qiju_Li\\Qiju_Li.txt"));
            bw.write("Hello I/O!");//在创建好的文件中写入"Hello I/O"
            bw.close();//一定要关闭文件
        }catch(IOException e){
            e.printStackTrace();
        }*/
    }

}
