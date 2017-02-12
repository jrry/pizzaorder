package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import util.PizzaOrder;

/**
 *
 * @author Jarosław Pawłowski
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Orders.findAll", query = "SELECT p FROM Orders p"),
        @NamedQuery(name = "Orders.findById", query = "SELECT p FROM Orders p WHERE p.id = :id"),
        @NamedQuery(name = "Orders.findMy", query = "SELECT p FROM Orders p WHERE p.user.id = :id")})
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<PizzaOrder> pizzas;
    private Double price;
    @OneToOne
    private Users user;
    private Integer status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Orders() {
        pizzas = new ArrayList<PizzaOrder>();
    }

    public List<PizzaOrder> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<PizzaOrder> pizzas) {
        this.pizzas = pizzas;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Orders[ id=" + id + " ]";
    }
}
