package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Jarosław Pawłowski
 */

@Entity
@Table(name = "COMPONENTS")
@NamedQueries({
        @NamedQuery(name = "Components.findAll", query = "SELECT c FROM Components c"),
        @NamedQuery(name = "Components.findByName", query = "SELECT c FROM Components c WHERE c.name = :name")})
public class Components implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @Column(name = "NAME", nullable = false)
    private String name;
    
    @Column(name = "PRIORITY", nullable = false)
    private Integer priority;

    public Components() {
    }

    public Components(Long id, String name, Integer priority) {
        this.id = id;
        this.name = name;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
