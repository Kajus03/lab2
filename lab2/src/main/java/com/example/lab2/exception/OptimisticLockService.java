package com.example.lab2.exception;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import com.example.lab2.entity.Command;

@Stateless
public class OptimisticLockService {
    @PersistenceContext
    private EntityManager em;

    public void updateCommand(Long id, String newName, int clientVersion) {
        Command managed = em.find(Command.class, id, LockModeType.OPTIMISTIC);
        if (managed.getVersion() != clientVersion) {
            throw new OptimisticLockException();
        }
        managed.setName(newName);
    }
}
