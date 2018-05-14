package com.globalcrm.rest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@EqualsAndHashCode(exclude = {"account","groups"})
@ToString(exclude = {"account"})
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Account account;
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private boolean enabled = false;

    @ManyToMany
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Group> groups = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "createdBy")
    private Set<Task> createdTasks = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "assignedTo")
    private Set<Task> assignedTasks = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "responsible")
    private Set<Sale> sales = new HashSet<>();

    public void addCreatedTask(Task task){
        task.setCreatedBy(this);
        createdTasks.add(task);
    }

    public void addAssignedTask(Task task){
        task.setAssignedTo(this);
        assignedTasks.add(task);
    }

    public void addSale(Sale sale){
        sale.setResponsible(this);
        sales.add(sale);
    }

}
