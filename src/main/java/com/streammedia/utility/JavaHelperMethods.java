package com.streammedia.utility;

import com.streammedia.entity.Trailer;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.nio.file.Files.deleteIfExists;

/**
 * The type Java helper methods.
 */
@Log4j2
public class JavaHelperMethods {
    /**
     * Gets class name.
     *
     * @param trailer the trailer
     * @return the class name
     */
    public static String retrieveClassName(Trailer trailer) {
        String objName = trailer.getClass().getName().toLowerCase()
                .replace(".", " ");
        return objName.substring(23,objName.length());
    }

    /**
     * Extracts file name from HTTP header content-disposition
     *
     * @param part the part
     * @return the string
     */
    public static String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String fileName = s.substring(s.indexOf("=") + 2, s.length()-1);
                log.debug("Files: " + fileName);
                return fileName;
            }
        }
        return "";
    }


    /**
     * Create user image path string.
     *
     * @param appPath  the app path
     * @param username the username
     * @return the string https://stackoverflow.com/questions/22031751/how-to-delete-all-files-in-a-folder-using-default-java-package
     */
    public static String deleteAndCreateFilePath(String appPath, String username) {
        String saveImagePath = appPath  + File.separator + username;
        File imageSaveDir = new File(saveImagePath);
        if (!imageSaveDir.exists()) {
            imageSaveDir.mkdir();
        } else {
            try {
                FileUtils.deleteDirectory(new File(String.valueOf(imageSaveDir)));
                imageSaveDir.mkdir();
            } catch (IOException e) {
                log.error(e);
            }

        }
        return saveImagePath;
    }

    /**
     * Create user image path string.
     *
     * @param appPath   the app path
     * @param fieldName the field name
     * @return the string
     */
    public static String createUserImagePath(String appPath, String fieldName) {
        String saveImagePath = appPath  + File.separator + fieldName;
        File imageSaveDir = new File(saveImagePath);
        if (!imageSaveDir.exists()) {
            imageSaveDir.mkdirs();
        }
        return saveImagePath;
    }

    private static String saveMedia(String saveImagePath, Part part) throws IOException {
        String fileName = JavaHelperMethods.extractFileName(part);
        log.debug("File Exists: " + fileName.length() );
        log.debug("File Exists: " + part.getSize() );
        log.debug("Submit: " + part.getSubmittedFileName());
        fileName = new File(fileName).getName();
        saveImagePath = saveImagePath + File.separator + fileName;
        part.write(saveImagePath);
        return saveImagePath;
    }

    /**
     * Save file name string.
     *
     * @param saveImagePath the save image path
     * @param part          the part
     * @return the string
     * @throws IOException the io exception
     */
    public static String saveFileName(String saveImagePath, Part part) throws IOException {
        saveImagePath = saveMedia(saveImagePath, part);
        log.debug("Path " + saveImagePath);
        return saveImagePath;
    }

    /**
     * Is numeric boolean.
     *
     * @param str the str
     * @return the boolean
     */
//    https://mkyong.com/java/java-how-to-check-if-a-string-is-numeric/
    public static boolean isNumeric(final String str) {
        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;

    }
}
