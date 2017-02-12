package admin;

import ejb.Database;
import entity.Components;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Jarosław Pawłowski
 */

@Named
@RequestScoped
public class ComponentsEdit {
    @EJB
    private Database db;
    private List<Components> components;
    private List<Components> filteredComponents;
    private String name;
    private Integer priority;
    private Components selectedComponent;
    private Components component;

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
    
    public ComponentsEdit() {
        component = new Components();
        components = new ArrayList<Components>();
        selectedComponent = new Components();
    }

    public Components getSelectedComponent() {
        return selectedComponent;
    }

    public void setSelectedComponent(Components selectedComponent) {
        this.selectedComponent = selectedComponent;
    }

    public List<Components> getComponents() {
        if (components.isEmpty()) {
            components = db.getComponents();
        }
        return components;
    }

    public List<Components> getFilteredComponents() {
        return filteredComponents;
    }

    public void setFilteredComponents(List<Components> filteredComponents) {
        this.filteredComponents = filteredComponents;
    }
    
    public void update() {
        db.updateComponent(selectedComponent);
        components = db.getComponents();
    }
    
    public void remove() {
        db.removeComponent(selectedComponent);
        components = db.getComponents();
    }

    public void add() {
        if (name != null && priority != null) {
            component.setName(name);
            component.setPriority(priority);
            db.createComponent(component);
        }
        name = null;
        priority = null;
        components = getComponents();
    }
}
