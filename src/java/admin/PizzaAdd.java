package admin;

import ejb.Database;
import entity.Components;
import entity.Pizza;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import messages.Message;

/**
 *
 * @author Jarosław Pawłowski
 */

@Named
@RequestScoped
public class PizzaAdd {
    @EJB
    private Database db;
    
    @NotNull(message = "{nullPizza}")
    private String name;
    @NotNull(message = "{smallPizza}")
    @Pattern(regexp = "(^[0-9]+(\\.[0-9]{2})?$)", message = "{pricePizza}")
    private String sprice;
    @NotNull(message = "{bigPizza}")
    @Pattern(regexp = "(^[0-9]+(\\.[0-9]{2})?$)", message = "{pricePizza}")
    private String bprice;

    private List<Components> cmps;
    private List<Pizza> pizzas;
    private Pizza pizza;
    private Pizza selectedPizza;
    private Components component;
    private Message msg;
    private String[] selectedScmps;  

    public PizzaAdd() {
        cmps = new ArrayList<>();
        pizzas = new ArrayList<>();
        selectedPizza = new Pizza();
        msg = new Message();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSprice() {
        return sprice;
    }

    public void setSprice(String sprice) {
        this.sprice = sprice;
    }

    public String getBprice() {
        return bprice;
    }

    public void setBprice(String bprice) {
        this.bprice = bprice;
    }

    public Pizza getSelectedPizza() {
        return selectedPizza;
    }

    public void setSelectedPizza(Pizza selectedPizza) {
        this.selectedPizza = selectedPizza;
    }

    public List<Components> getCmps() {
        if (cmps.isEmpty()) {
            cmps = db.getComponents();
        }
        return cmps;
    }

    public List<Pizza> getPizzas() {
        if (pizzas.isEmpty()) {
            pizzas = db.getPizzas();
        }
        return pizzas;
    }
    
    public String savep() {
        pizza = new Pizza();
        pizza.setPname(name);
        pizza.setSprice(Double.parseDouble(sprice));
        pizza.setBprice(Double.parseDouble(bprice));
        for (String s : selectedScmps) {
            component = db.getComponentsByName(s);
            pizza.addElement(component);
        }
        db.createPizza(pizza);
        pizzas = new ArrayList<Pizza>();
        clearVar();
        msg.setInfo("Pizzaadd");
        return "addpizza";
    }
    
    public void deletePizza(Pizza p) {
        db.removePizza(p);    
        pizzas = new ArrayList<Pizza>();
    }

    public void updatePizza(Pizza p, String[] selected) {
        if (selected.length > 0) {
            p.setPcomponents(new ArrayList<Components>());
            for (String s : selected) {
                component = db.getComponentsByName(s);
                p.addElement(component);
            }
        } else {
            pizza = db.getPizzaByID(p.getId());
            p.setPcomponents(pizza.getPcomponents());
        }
        db.updatePizza(p);
        pizzas = new ArrayList<Pizza>();
    }

    public String[] getSelectedScmps() {
        return selectedScmps;
    }

    public void setSelectedScmps(String[] selectedScmps) {
        this.selectedScmps = selectedScmps;
    }
    
    private void clearVar() {
        name = null;
        sprice = null;
        bprice = null;
        selectedScmps = null;
    }
}
