package utils;

import objects.UserInfo;

import javax.servlet.http.HttpSession;

/**
 * Created by Guy on 03/06/2017.
 */
public class SessionUtils {

    // Store user info in Session.
    public static void storeLoginedUser(HttpSession session, UserInfo loginedUser) {
        // On the JSP can access ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }


    // Get the user information stored in the session.
    public static UserInfo getLoginedUser(HttpSession session) {
        UserInfo loginedUser = (UserInfo) session.getAttribute("loginedUser");
        return loginedUser;
    }

}
