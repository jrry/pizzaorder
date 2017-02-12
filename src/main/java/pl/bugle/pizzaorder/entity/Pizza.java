package pl.bugle.pizzaorder.entity;

import pl.bugle.pizzaorder.util.CompareComponents;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Jarosław Pawłowski
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Pizza.findAll", query = "SELECT p FROM Pizza p"),
        @NamedQuery(name = "Pizza.findById", query = "SELECT p FROM Pizza p WHERE p.id = :id"),
        @NamedQuery(name = "Pizza.findByName", query = "SELECT p FROM Pizza p WHERE p.pname = :pname")})
public class Pizza implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pname;
    private Double sprice;
    private Double bprice;
    @OneToMany
    private List<Components> pcomponents;

    public Pizza() {
        this.pcomponents = new ArrayList<Components>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Double getSprice() {
        return sprice;
    }

    public void setSprice(Double sprice) {
        this.sprice = sprice;
    }

    public Double getBprice() {
        return bprice;
    }

    public void setBprice(Double bprice) {
        this.bprice = bprice;
    }

    public List<Components> getPcomponents() {
        Collections.sort(pcomponents, new CompareComponents());
        return pcomponents;
    }

    public void setPcomponents(List<Components> pcomponents) {
        this.pcomponents = pcomponents;
    }

    public void addElement(Components component) {
        this.getPcomponents().add(component);
    }
    
    public void dropElement(Components component) {
        this.getPcomponents().remove(component);
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
        if (!(object instanceof Pizza)) {
            return false;
        }
        Pizza other = (Pizza) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Pizza[ id=" + id + " ]";
    }
}
