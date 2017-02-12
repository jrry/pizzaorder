package user;

import entity.Users;
import ejb.Database;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import messages.Message;
import validation.Email;

/**
 *
 * @author Jarosław Pawłowski
 */

@Named
@RequestScoped
public class Register implements Serializable {
    @EJB
    private Database db;

    private String tmpnick;
    private Message msg;

    @NotNull(message = "{nullNick}")
    @Size(min = 3, max = 15, message = "{sizeNick}")
    private String nick;

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

    @NotNull(message = "{nullPhone}")
    @Pattern(regexp = "([0-9]{9})", message = "{odPhone}")
    private String telefon;
    
    @NotNull(message = "{nullAddress}")
    @Size(min = 6, message = "{sizeAddress}")  
    private String address;
    
    private Users puser;
    
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Register() {
        msg = new Message();
    }

    public String register() {
        if (nick != null) {
            tmpnick = nick.toLowerCase();
        }
        puser = db.getUserByUsernameLower(tmpnick);
        if (puser == null && password != null) {
            puser = new Users();
            puser.setLevel("USER");
            puser.setUsername(nick);
            puser.setPassword(password);
            puser.setEmail(email);
            puser.setPhone(telefon);
            puser.setAddress(address);
            db.createUser(puser);
            msg.setInfo("Registerpass");
            return "home";
        } else {
            msg.setError("Registererr");
            return null;
        }
    }
}
