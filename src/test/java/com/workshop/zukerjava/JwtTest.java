package com.workshop.zukerjava;

import com.workshop.zukerjava.security.JwtUtils;
import org.junit.Test;

import java.util.Date;

public class JwtTest {
    @Test
    public void tokenTest() {
        JwtUtils.generateKey(new Date().getTime());
        String t = JwtUtils.createToken((long) 1);
        System.out.println(t);
        System.out.println(JwtUtils.getUserId(t));
        t = "0" + t.substring(1);
        System.out.println(JwtUtils.verify(t));
    }
}
