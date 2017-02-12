package user;

import util.Avatar;
import ejb.Database;
import entity.Users;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author Jarosław Pawłowski
 */

@Named
@RequestScoped
public class UserInfo implements Serializable {
    @EJB
    private Database db;

    private Long uid;
    private Session session;
    private Subject currentUser;
    private Avatar gravatar;

    public UserInfo() {
        currentUser = SecurityUtils.getSubject();
        session = currentUser.getSession();
        uid = (Long) session.getAttribute("uid");
    }
    
    public String getAvatar() {
        gravatar = new Avatar();
        return gravatar.getAvatar(getUser().getEmail());
    }
    
    public Users getUser() {
        return db.getUserByID(uid);
    }
}
