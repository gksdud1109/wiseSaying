package com.back;

import com.back.standard.util.TestUtil;
import com.back.system.AppContext;
import com.back.system.wiseSayingApp;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class AppTestRunner {
    public static String run(String input) {
        Scanner sc = TestUtil.genScanner(input + "\n종료");
        ByteArrayOutputStream outputStream = TestUtil.setOutToByteArray();

        AppContext.init(sc);
        new wiseSayingApp().run();
        return outputStream.toString();
    }
}
