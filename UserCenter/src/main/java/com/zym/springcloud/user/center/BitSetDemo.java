package com.zym.springcloud.user.center;

import java.util.BitSet;

public class BitSetDemo {
    public static void main(String[] args) {
        BitSet set = new BitSet(20);

        set.set(1);
        set.set(5);

        for (int i = 0, j = 0; j < 18; i++, j++) {
            i = set.nextClearBit(i);
            System.out.println("j=" + j + " i=" + i);
        }

    }
}
