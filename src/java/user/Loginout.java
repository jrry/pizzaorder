package user;

import ejb.Database;
import entity.Users;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import messages.Message;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author Jarosław Pawłowski
 */

@Named
@RequestScoped
public class Loginout implements Serializable {
    @EJB
    private Database db;

    @NotNull(message = "{nullNick}")
    private String username;
    @NotNull(message = "{nullPassword}")
    private String password;

    private Message msg;
    private boolean remember;
    private Session session;
    private Subject currentUser;
    private Users puser;

    public Loginout() {
        currentUser = SecurityUtils.getSubject();
        msg = new Message();
        puser = new Users();
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login() {
        try {
            currentUser.login(new UsernamePasswordToken(username, password));
            session = currentUser.getSession();
            puser = db.getUserByUsername(username);
            session.setAttribute("uid", puser.getId());
            msg.setInfo("Loginpass");
        } catch (UnknownAccountException uae) {
            msg.setError("Loginerr");
        } catch (IncorrectCredentialsException ice) {
            msg.setError("Wrongpass");
        } catch (AuthenticationException ae) {
            msg.setError("Wrongpass");
        }
    }

    public String logout() {
        currentUser.logout();
        msg.setInfo("Logoutpass");
        return "outhome";
    }
}
