package user;

import ejb.Database;
import entity.Users;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import messages.Message;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import validation.Email;

/**
 *
 * @author Jarosław Pawłowski
 */

@Named
@RequestScoped
public class Edit {
    @EJB
    private Database db;
    
    @NotNull(message = "{nullPassword}")
    @Size(min = 8, message = "{sizePassword}")  
    @Pattern.List({
        @Pattern(regexp = "(^.*[0-9].*$)", message = "{nPassword}"),
        @Pattern(regexp = "(^.*[a-z].*$)", message = "{slPassword}"),
        @Pattern(regexp = "(^.*[A-Z].*$)", message = "{blPassword}")
    })
    private String password;
    
    @Email
    private String email;

    @NotNull(message = "{nullAddress}")
    @Size(min = 6, message = "{sizeAddress}")  
    private String address;

    @NotNull(message = "{nullPhone}")
    @Pattern(regexp = "([0-9]{9})", message = "{odPhone}")
    private String telefon;
    
    private Long uid;
    private Session session;
    private Subject currentUser;
    private Message msg;

    public Edit() {
        msg = new Message();
        currentUser = SecurityUtils.getSubject();
        session = currentUser.getSession();
        uid = (Long) session.getAttribute("uid");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return db.getUserByID(uid).getEmail();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return db.getUserByID(uid).getAddress();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelefon() {
        return db.getUserByID(uid).getPhone();
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    
    public String save(Users user) {
        user.setPassword(password);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhone(telefon);
        db.updateUser(user);
        msg.setInfo("Updatedata");
        return "home";
    }
}
