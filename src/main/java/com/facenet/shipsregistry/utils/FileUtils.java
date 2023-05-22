package com.facenet.shipsregistry.utils;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Component
public class FileUtils {

    /**
     *
     * @param data
     * @return
     */
    public byte[] compressFile(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    /**
     *
     * @param data
     * @return
     */
    public byte[] decompressFile(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
        } catch (DataFormatException e) {
            throw new RuntimeException(e);
        }
        return outputStream.toByteArray();
    }
}