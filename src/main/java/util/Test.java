package util;

import com.sun.javaws.IconUtil;
import com.sun.xml.internal.bind.v2.util.CollisionCheckStack;
/**
 * 每隔25个数字存放一个集合
 *
 */
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        List<List> listList = new ArrayList<>();
        for(int i = 1;i < 255; i++){
            list.add(i);
            if(i%25==0){
                list.add(i);
                System.out.println(i);
                listList.add(list);
                list = new ArrayList<Integer>();
            }
        }
        for(List<Integer> x : listList){
            System.out.println(x);
        }
    }
}
