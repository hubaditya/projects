package delivery.santa.delivery.customer;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager
{
    SharedPreferences pref;
    Editor editor;
    Context _context;
    private static final String PREF_NAME = "PrefName";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    int PRIVATE_MODE = 0;

    public SessionManager(Context context)
    {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn)
    {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }
}
