package com.example.lab2.bean;

import com.example.lab2.entity.Command;
import com.example.lab2.exception.OptimisticLockService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CommandBean implements Serializable {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private OptimisticLockService lockService;

    private List<Command> commands;
    private Command newCommand = new Command();
    private Command selected;

    @PostConstruct
    public void init() {
        load();
    }

    public void load() {
        commands = em.createQuery("SELECT c FROM Command c", Command.class).getResultList();
    }

    public void edit(Command c) {
        selected = c;
    }

    @Transactional
    public String create() {
        em.persist(newCommand);
        newCommand = new Command();
        load();
        return null;
    }

    @Transactional
    public String update() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            lockService.updateCommand(
                    selected.getId(),
                    selected.getName(),
                    selected.getVersion()
            );
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", null));
            selected = null;
        }
        catch (OptimisticLockException ole) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "pasikeite", null));
            em.clear();
            selected = em.find(Command.class, selected.getId(), LockModeType.OPTIMISTIC);
        }
        load();
        return null;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public Command getNewCommand() {
        return newCommand;
    }

    public void setNewCommand(Command newCommand) {
        this.newCommand = newCommand;
    }

    public Command getSelected() {
        return selected;
    }

    public void setSelected(Command selected) {
        this.selected = selected;
    }
}
