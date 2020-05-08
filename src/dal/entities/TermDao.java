/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.entities;

import dal.commons.DaoEclipseLink;
import entities.Term;
import java.util.List;

/**
 *
 * @author Javier
 */
public class TermDao extends DaoEclipseLink<Term, Integer>
{
    public TermDao() {
        super(Term.class);
    }
    
    public Integer retrieve(String term)
    {
        List<Term> resp = entityManager.createNamedQuery("Term.findByPalabra")
                .setParameter("palabra", term)
                .getResultList();
        if (resp.size() == 1) return resp.get(0).getIdTermino();
        
        return null;
    }
}
