package pl.bugle.pizzaorder.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 *
 * @author Jarosław Pawłowski
 */

@Entity
@Table(name = "USERS")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT p FROM Users p"),
    @NamedQuery(name = "Users.findById", query = "SELECT p FROM Users p WHERE p.id = :id"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT p FROM Users p WHERE p.username = :username"),
    @NamedQuery(name = "Users.findByUsernameLower", query = "SELECT p FROM Users p WHERE lower(trim(both from p.username)) = :username")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @Column(name = "LVL", nullable = false)
    private String level;
    
    @Column(name = "USERNAME", nullable = false)
    private String username;
    
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    
    @Column(name = "EMAIL", nullable = false)
    private String email;
    
    @Column(name = "PHONE", nullable = false)
    private String phone;
    
    @Column(name = "ADDRESS", nullable = false)
    private String address;

    public Users() {
    }

    public Users(Long id) {
        this.id = id;
    }

    public Users(Long id, String level, String username, String password, String email, String phone, String address) {
        this.id = id;
        this.level = level;
        this.username = username;
        this.password = new Sha256Hash(password).toBase64();
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
        this.password = new Sha256Hash(password).toBase64();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "user.Users[ id=" + id + " ]";
    }
}
