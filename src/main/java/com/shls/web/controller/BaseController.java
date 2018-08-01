package com.shls.web.controller;


import com.shls.utils.MimeTypeUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by song on 28/08/2017.
 */
public class BaseController
{
    protected void wrapDownloadHttpResponse(String displayFilename, HttpServletResponse response)
    {
        response.addHeader("Cache-Control", "no-cache");
        response.addDateHeader("Expries", 0);

        String suffix = displayFilename.substring(displayFilename.lastIndexOf(".") + 1);
        String contentType = MimeTypeUtil.getMime(suffix);

        response.setContentType(contentType);
        try
        {
            displayFilename = new String(displayFilename.getBytes("UTF-8"), "ISO8859_1");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        response.addHeader("Content-Disposition", "attachment;filename=" + displayFilename);
    }

    protected void deleteDir(File file)
    {
        for (File f : file.listFiles())
        {
            if (f.exists())
            {
                f.delete();
            }
        }

        file.delete();
    }

    protected void wrapDownloadFile(InputStream inputStream, String displayFilename, HttpServletResponse response)
    {
        wrapDownloadHttpResponse(displayFilename, response);
        OutputStream outputStream = null;
        try
        {
            outputStream = response.getOutputStream();

            int len;
            byte[] buffer = new byte[2048];
            while ((len = inputStream.read(buffer)) != -1)
            {
                outputStream.write(buffer, 0, len);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        finally
        {
            try
            {
                if (inputStream != null)
                {
                    inputStream.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


}
