package model;

import javax.servlet.http.Part;

import DAO.AccountsDao;


public class IconFileName {

	// ユーザIDと拡張子をまとめる
	public String generateFileName(String userUuid, String extension) {
    	AccountsDao aDao = new AccountsDao();
		model.User loginUser = aDao.showUser(userUuid);
		String userId = loginUser.getUser_id();
        return userId + "." + extension;
    }

	// アップロードファイルの名前を取得
	public String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");

        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
