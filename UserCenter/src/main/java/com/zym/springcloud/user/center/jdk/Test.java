package com.zym.springcloud.user.center.jdk;

import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        String newTem = "C:\\Users\\zhongym\\Documents\\WeChat Files\\zhongyuanmao\\CustomEmotions\\new\\";
        File file = new File("C:\\Users\\zhongym\\Documents\\WeChat Files\\zhongyuanmao\\CustomEmotions");
        for (File listFile : file.listFiles()) {
//            listFile.renameTo(new File(newTem + listFile.getName() + ".png"));
            FileCopyUtils.copy(listFile,new File(newTem + listFile.getName() + ".png"));
        }
    }
}
