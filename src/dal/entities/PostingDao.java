/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.entities;

import dal.commons.DaoEclipseLink;
import entities.Posting;
import entities.PostingPK;
import entities.Term;
import java.util.List;

/**
 *
 * @author Javier
 */
public class PostingDao extends DaoEclipseLink<Posting, PostingPK>
{
    
    public PostingDao() {
        super(Posting.class);
    }
    
     public Long getAmountDocumentsByTerm(Integer termID)
    {
        Long amount = (Long)entityManager.createNamedQuery("Posting.countByIdTermino")
                .setParameter("idTermino", termID)
                .getSingleResult();
        return amount;
    }
     
     public List<Posting> retrievePostingList(Integer termId)
    {
        List<Posting> resp = entityManager.createNamedQuery("Posting.findByIdTerminoOrderByFrecuencia")
                .setParameter("idTermino", termId)
                .getResultList();
        if (resp.size() > 0) return resp;
        
        return null;
    }
     
     

    
}
