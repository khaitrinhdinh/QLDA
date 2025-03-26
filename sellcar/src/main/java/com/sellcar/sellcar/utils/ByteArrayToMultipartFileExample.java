package com.sellcar.sellcar.utils;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class ByteArrayToMultipartFileExample {
    public static MultipartFile parse(byte[] fileContent ) {
        MultipartFile multipartFile = new MockMultipartFile(
                "file",               // Tên file
                "test.txt",           // Tên gốc của file
                "text/plain",         // Loại MIME
                fileContent           // Nội dung file dưới dạng byte[]
        );

        return multipartFile;
    }
}
