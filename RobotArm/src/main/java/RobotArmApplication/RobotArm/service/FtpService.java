package RobotArmApplication.RobotArm.service;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FtpService {

    // server: 192.168.1.1
    private static final String server = "192.168.1.1";

    // port: 21
    private static final int port = 21;

    // username: lua
    private static final String username = "lua";

    // password: 123456
    private static final String password = "123456";

    // Function uploadLuaFile to upload the lua file to the Robot Arm
    /**
     * Uploads a Lua file to the Robot Arm using FTP.
     *
     * @param localFilePath The local path of the Lua file to be uploaded.
     * @return Success or failure message.
     */
    public static String uploadLuaFile(String localFilePath) {
        FTPClient ftpClient = new FTPClient();

        try {
            // Connect to the FTP server
            ftpClient.connect(server, port);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // Get the local Lua file
            File localFile = new File(localFilePath);
            FileInputStream inputStream = new FileInputStream(localFile);

            // Set the remote directory on the Robot Arm
            String remoteDirectory = "/lua"; // Update with the actual directory
            ftpClient.changeWorkingDirectory(remoteDirectory);

            // Store the file on the Robot Arm
            boolean success = ftpClient.storeFile(localFile.getName(), inputStream);
            System.out.println(success);
            System.out.println(localFile.getName());
            inputStream.close();

            if (success) {
                return "Lua file uploaded successfully.";
            } else {
                return "Failed to upload Lua file.";
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading Lua file: " + e.getMessage();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
