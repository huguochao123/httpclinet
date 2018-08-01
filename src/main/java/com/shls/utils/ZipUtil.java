package com.shls.utils;

/**
 * Created by song on 18/07/2017.
 */

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩文件工具类
 */
public class ZipUtil
{
    public static void compress(Map<String, byte[]> dataList, OutputStream outputStream) throws IOException
    {
        ZipOutputStream zos = new ZipOutputStream(outputStream);

        dataList.forEach((name, data) ->
        {
            try
            {
                compress(data, name, zos);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });

        zos.close();
    }

    private static void compress(byte[] data, String displayName, ZipOutputStream zos) throws IOException
    {
        zos.putNextEntry(new ZipEntry(displayName));
        zos.write(data, 0, data.length);
    }
}