package com.zym.springcloud.user.center.jdk;

public class InnerClassDemo {

    {
        System.out.println("InnerClassDemo{}");
    }

    private String name;

    public String getName() {
        return name;
    }

    class IC {

        {
            System.out.println("IC{}");
        }

        public void say() {
            System.out.println(name);
            String name = getName();

        }
    }

    public static void main(String[] args) {
        IC ic = new InnerClassDemo().new IC();
    }
}
