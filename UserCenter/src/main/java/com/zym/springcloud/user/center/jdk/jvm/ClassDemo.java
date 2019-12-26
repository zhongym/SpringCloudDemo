package com.zym.springcloud.user.center.jdk.jvm;

import com.zym.springcloud.user.center.jdk.FinalPropertyUpdateDemo;

import java.io.Serializable;

/**
 * @author zhongym
 */
public class ClassDemo extends FinalPropertyUpdateDemo implements Serializable {

    public static final String S_F_S = "s_f_s_zym";
    public final String F_S = "f_s_zym";
    private static String S_S = "s_zym";
    private int fi = 2;
    private Integer fint;

    public int getFi() {
        return fi;
    }

    public void setFi(int fi) {
        this.fi = fi;
    }

    public void setFint(Integer fint) {
        this.fint = fint;
    }

    public String sout(String arg) {
        String result = arg + "aaa";
        return result;
    }

    public static void main(String[] args) {
        System.out.println(S_F_S);

        ClassDemo d = new ClassDemo();

        String zym = d.sout("zym");
        System.out.println(zym);
    }
}
