package tesing;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratingPasswords
{

    /*-
     * 
     */
    public static void main(String[] args)
    {
        String plain_password = "password";

        //
        String pw_hash = BCrypt.hashpw(plain_password, BCrypt.gensalt());

        System.out.println(pw_hash);

        //
        String hashed = BCrypt.hashpw(plain_password, BCrypt.gensalt());
        System.out.println(hashed);

        //
        String hashed2 = BCrypt.hashpw(plain_password, BCrypt.gensalt(12));
        System.out.println(hashed2);

        if (BCrypt.checkpw(plain_password, pw_hash)) {

            System.out.println("It matches-1");

        }

        if (BCrypt.checkpw(plain_password, hashed)) {

            System.out.println("It matches-2");

        }
        if (BCrypt.checkpw(plain_password, hashed2)) {

            System.out.println("It matches-3");

        }
        
        ///
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();  
//        boolean isPasswordMatches = bcrypt.matches(userenteredpasswordWithotEncryoted, encryptedPasswordFromDb);
        
        boolean isPasswordMatches = bcrypt.matches(
            "password",
            "$2a$10$ONMb9w8v2vZcjghycOutXui01Fh.EbGZlvKpTPDWjZF08aErt1Fie"
    );


            if (isPasswordMatches) { // correct password
                System.out.println("matches");
            } else { // Wrong Password
                System.out.println("Not Matches");
            }
        
        
    }

}
