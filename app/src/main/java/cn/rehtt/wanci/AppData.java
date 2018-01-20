package cn.rehtt.wanci;

import android.app.Application;

/**
 * Created by Rehtt on 2018/1/20.
 */

public class AppData {

    private  static String userName = "";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        AppData.userName = userName;
    }
}
