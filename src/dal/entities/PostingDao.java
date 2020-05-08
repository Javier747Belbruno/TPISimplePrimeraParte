/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.entities;

import dal.commons.DaoEclipseLink;
import entities.Posting;
import entities.PostingPK;

/**
 *
 * @author Javier
 */
public class PostingDao extends DaoEclipseLink<Posting, PostingPK>
{
    
    public PostingDao() {
        super(Posting.class);
    }
    
    
    
}
