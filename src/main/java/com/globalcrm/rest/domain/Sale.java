package com.globalcrm.rest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hugo Lezama on May - 2018
 */
@Data
@EqualsAndHashCode(exclude = {"contact","responsible"})
@ToString(exclude = {"contact","responsible"})
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToOne(optional = false)
    private Contact contact;
    @ManyToOne(optional = false)
    private User responsible;
    @Enumerated(value = EnumType.STRING)
    private SaleStage currentStage;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sale")
    private Set<Note> notes= new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "linkedToSale")
    private Set<Task> tasks= new HashSet<>();

    public void addNote(Note note){
        note.setSale(this);
        this.notes.add(note);
    }

    public void addTask(Task task){
        task.setLinkedToSale(this);
        this.tasks.add(task);
    }

}