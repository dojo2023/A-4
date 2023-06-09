package servlet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.AccountsDao;
import model.IconFileName;
import model.PwHashed;
import model.User;


/**
 * Servlet implementation class Comment
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		//セッションスコープからログイン中のユーザのUUIDを取得する
		HttpSession session = request.getSession();
		String userUuid = (String)session.getAttribute("id");


		//アカウントのDAOをインスタンス化する
		AccountsDao uDao = new AccountsDao();

		//インスタンス化したアカウントのDAOの中のshowUserメソッドにさっき取得したUUIDを引数として渡す
		User loginUser = uDao.showUser(userUuid);
		String User_name=loginUser.getUser_name();
		String User_id=loginUser.getUser_id();
		//帰ってきたBeans（User型のインスタンス）からIDと名前を取り出してリクエストスコープに入れる。
		request.setAttribute("username", User_name);
		request.setAttribute("userid", User_id);

		// 編集ページにフォワードする
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editProfile.jsp");
		dispatcher.forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");


		HttpSession session = request.getSession();
		String userUuid = (String)session.getAttribute("id");

		String userId = request.getParameter("USER_ID");
		String userName = request.getParameter("USER_NAME");
		String password = request.getParameter("pw-af");
		String hashpw = PwHashed.hashPassword(password);

		if (request.getParameter("select").equals("変更")) {
			// 更新を行う
			AccountsDao rDao = new AccountsDao();
			User u = new User();
			u.setUser_id(userId);
			String str = rDao.check(u);
			if (str == null) {


					if (rDao.update(userUuid,userId,userName,password)) {	// 更新成功
						System.out.println("更新しました");
						response.sendRedirect("/NYASTER/UserPage");
					}
					else { // 更新失敗
						System.out.println("更新できませんでした");
					}

			}
		}

		else if(request.getParameter("select").equals("PW変更")) {

			AccountsDao aDao = new AccountsDao();
			model.User loginUser = aDao.showUser(userUuid); //ログインユーザの情報を取得
			String userid = loginUser.getUser_id();

			String uuid = aDao.isLoginOK(userid, hashpw);
			if (uuid != null) {
				System.out.println("更新しました");
				response.sendRedirect("/NYASTER/UserPage");
			}
			else{ // 更新失敗
				System.out.println("更新できませんでした");
			}

		}
		else if (request.getParameter("select").equals("アカウント削除")) {
			// 削除を行う
			AccountsDao cDao = new AccountsDao();
			if (cDao.delete(userUuid)) {	// 削除成功
				System.out.println("削除しました");
				response.sendRedirect("/NYASTER/Login");
			}
			else {						// 削除失敗
				System.out.println("削除できませんでした");
			}
		}

		IconFileName ifn = new IconFileName();
        Part filePart = request.getPart("file");
        String fileName = ifn.getFileName(filePart);

        if (fileName != null && !fileName.isEmpty()) {
            String extension = "png"; // 保存する拡張子を指定

            // ファイル名を生成
            String saveFileName = ifn.generateFileName(userUuid, extension);

            // ファイルを保存するディレクトリのパスを指定
            String uploadDirectory = "C:/pleiades/workspace/data/icon_img/";

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
        response.sendRedirect("/NYASTER/EditProfile"); //元のページにリダイレクト
	}
}





















