package model;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PwHashed {
    public static String hashPassword(String password) {
        try {
            // ハッシュ関数のインスタンスを取得
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // パスワードをバイト配列に変換してハッシュ計算
            byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // バイト配列を16進数文字列に変換
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // ハッシュ化されたパスワードを返す
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // ハッシュ関数がサポートされていない場合の例外処理
            e.printStackTrace();
            return null;
        }
    }
}
