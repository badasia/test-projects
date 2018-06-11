import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Files;

public class Main {

    /*
     * Ниже команда для выкачивания всех субтитров с youtube канала
     * youtube-dl --write-sub --write-auto-sub --sub-format ttml --sub-lang ru --skip-download https://www.youtube.com/user/quattro882
     */

    private static final String srcFolderPath = "C:\\Users\\badasia\\Desktop\\src";
    private static final String destFolderPath = "C:\\Users\\badasia\\Desktop\\dest";

    public static void main(String[] args) throws Exception {
        File srcFolder = new File(srcFolderPath);

        StringBuilder finishFile = new StringBuilder();
        finishFile.append(
                "<html xmlns=\"http://www.w3.org/TR/REC-html40\">\n" +
                        "\n" +
                        "<head>\n" +
                        "    <meta http-equiv=Content-Type content=\"text/html; charset=utf-8\">\n" +
                        "</head>\n" +
                        "\n" +
                        "<body>\n");

        if (srcFolder.exists()
                && srcFolder.isDirectory()) {
            for (File srcFile : FileUtils.listFiles(srcFolder, null, true)) {
                String srcString = new String(Files.readAllBytes(srcFile.toPath()), "UTF-8");

                srcString = srcString.substring(srcString.indexOf("<div>") + 5, srcString.indexOf("</div>"));
                srcString = srcString.replace("<span style=\"s1\">", "");
                srcString = srcString.replace("<span style=\"s2\">", "");
                srcString = srcString.replace("<span style=\"s3\">", "");
                srcString = srcString.replace("<span style=\"s4\">", "");
                srcString = srcString.replace("</span>", "");
                srcString = srcString.replace(" style=\"s1\"", "");
                srcString = srcString.replace(" style=\"s2\"", "");
                srcString = srcString.replace(" style=\"s3\"", "");
                srcString = srcString.replace(" style=\"s4\"", "");
                srcString = srcString.replace("<p begin=\"", "[");
                srcString = srcString.replace("\" end=\"", " - ");
                srcString = srcString.replace("\">", "] ");
                srcString = srcString.replace("</p>", "");
                srcString = srcString.replace("<br />", " ");
                srcString = srcString.replace("\n", "<br>");
                srcString = srcString.replace("[музыка]", "[myzika]");

                String destString = "" +
                        "<div>\n" +
                        "<p> FILENAME:" + srcFile.getName() + " <br>\n";
                destString += srcString;
                destString += "" +
                        "</p>\n" +
                        "</div>\n";

                finishFile.append(destString);
            }
        }

        finishFile.append("" +
                "</body>\n" +
                "\n" +
                "</html>");

        File destFile = new File(destFolderPath + "\\" + "resultFile.html");
        FileUtils.write(destFile, finishFile, "UTF-8");
    }
}
