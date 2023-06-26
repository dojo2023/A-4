package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.IconFileName;

/**
 * Servlet implementation class ImgUpload
 */
@WebServlet("/ImgUpload")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1MB
	    maxFileSize = 1024 * 1024 * 10, // 10MB
	    maxRequestSize = 1024 * 1024 * 50 // 50MB
	)
public class ImgUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		HttpSession session = request.getSession();

		//セッションスコープにユーザIDが存在しない場合はログインページにリダイレクトする
		if ((String)session.getAttribute("id") == null) {
			response.sendRedirect("/NYASTER/Login");
			return;
		}

    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/imgUploader.jsp");
		dispatcher.forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userUuid = (String)session.getAttribute("id");

		IconFileName ifn = new IconFileName();
        Part filePart = request.getPart("file");
        String fileName = ifn.getFileName(filePart);

        if (fileName != null && !fileName.isEmpty()) {
            String extension = "png"; // 保存する拡張子を指定

            // ファイル名を生成
            String saveFileName = ifn.generateFileName(userUuid, extension);

            // ファイルを保存するディレクトリのパスを指定
            String uploadDirectory = "/NYASTER/icon_img/";

            // ファイルを保存するパスを組み立て
            Path filePath = Path.of(uploadDirectory, saveFileName);

            try (InputStream fileContent = filePart.getInputStream()) {
                // ファイルをPNG形式で保存
                Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("ファイルが正常に保存されました。");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ファイルの保存に失敗しました。");
            }
        }
        response.sendRedirect("/NYASTER/TopPage"); //元のページにリダイレクト
	}
}
