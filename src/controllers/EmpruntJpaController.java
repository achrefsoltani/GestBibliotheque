/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.Emprunt;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Etudiant;
import entities.Livre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author TOSHIBA
 */
public class EmpruntJpaController implements Serializable {

    public EmpruntJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Emprunt emprunt) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Etudiant idEtudiant = emprunt.getIdEtudiant();
            if (idEtudiant != null) {
                idEtudiant = em.getReference(idEtudiant.getClass(), idEtudiant.getId());
                emprunt.setIdEtudiant(idEtudiant);
            }
            Livre idLivre = emprunt.getIdLivre();
            if (idLivre != null) {
                idLivre = em.getReference(idLivre.getClass(), idLivre.getId());
                emprunt.setIdLivre(idLivre);
            }
            em.persist(emprunt);
            if (idEtudiant != null) {
                idEtudiant.getEmpruntCollection().add(emprunt);
                idEtudiant = em.merge(idEtudiant);
            }
            if (idLivre != null) {
                idLivre.getEmpruntCollection().add(emprunt);
                idLivre = em.merge(idLivre);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Emprunt emprunt) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Emprunt persistentEmprunt = em.find(Emprunt.class, emprunt.getId());
            Etudiant idEtudiantOld = persistentEmprunt.getIdEtudiant();
            Etudiant idEtudiantNew = emprunt.getIdEtudiant();
            Livre idLivreOld = persistentEmprunt.getIdLivre();
            Livre idLivreNew = emprunt.getIdLivre();
            if (idEtudiantNew != null) {
                idEtudiantNew = em.getReference(idEtudiantNew.getClass(), idEtudiantNew.getId());
                emprunt.setIdEtudiant(idEtudiantNew);
            }
            if (idLivreNew != null) {
                idLivreNew = em.getReference(idLivreNew.getClass(), idLivreNew.getId());
                emprunt.setIdLivre(idLivreNew);
            }
            emprunt = em.merge(emprunt);
            if (idEtudiantOld != null && !idEtudiantOld.equals(idEtudiantNew)) {
                idEtudiantOld.getEmpruntCollection().remove(emprunt);
                idEtudiantOld = em.merge(idEtudiantOld);
            }
            if (idEtudiantNew != null && !idEtudiantNew.equals(idEtudiantOld)) {
                idEtudiantNew.getEmpruntCollection().add(emprunt);
                idEtudiantNew = em.merge(idEtudiantNew);
            }
            if (idLivreOld != null && !idLivreOld.equals(idLivreNew)) {
                idLivreOld.getEmpruntCollection().remove(emprunt);
                idLivreOld = em.merge(idLivreOld);
            }
            if (idLivreNew != null && !idLivreNew.equals(idLivreOld)) {
                idLivreNew.getEmpruntCollection().add(emprunt);
                idLivreNew = em.merge(idLivreNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = emprunt.getId();
                if (findEmprunt(id) == null) {
                    throw new NonexistentEntityException("The emprunt with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Emprunt emprunt;
            try {
                emprunt = em.getReference(Emprunt.class, id);
                emprunt.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The emprunt with id " + id + " no longer exists.", enfe);
            }
            Etudiant idEtudiant = emprunt.getIdEtudiant();
            if (idEtudiant != null) {
                idEtudiant.getEmpruntCollection().remove(emprunt);
                idEtudiant = em.merge(idEtudiant);
            }
            Livre idLivre = emprunt.getIdLivre();
            if (idLivre != null) {
                idLivre.getEmpruntCollection().remove(emprunt);
                idLivre = em.merge(idLivre);
            }
            em.remove(emprunt);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Emprunt> findEmpruntEntities() {
        return findEmpruntEntities(true, -1, -1);
    }

    public List<Emprunt> findEmpruntEntities(int maxResults, int firstResult) {
        return findEmpruntEntities(false, maxResults, firstResult);
    }

    private List<Emprunt> findEmpruntEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Emprunt.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Emprunt findEmprunt(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Emprunt.class, id);
        } finally {
            em.close();
        }
    }
    public Emprunt findEmpruntEtudiant(String nomEtudiant) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Emprunt.class, nomEtudiant);
        } finally {
            em.close();
        }
    }

    public int getEmpruntCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Emprunt> rt = cq.from(Emprunt.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
