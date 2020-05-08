/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dal.commons.DalEntity;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Javier
 */
@Entity
@Table(name = "terminos", catalog = "corpus", schema = "")
@NamedQueries({
    @NamedQuery(name = "Term.findAll", query = "SELECT t FROM Term t"),
    @NamedQuery(name = "Term.findByIdTermino", query = "SELECT t FROM Term t WHERE t.idTermino = :idTermino"),
    @NamedQuery(name = "Term.findByPalabra", query = "SELECT t FROM Term t WHERE t.palabra = :palabra")})
public class Term implements Serializable,DalEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_termino")
    private Integer idTermino;
    @Basic(optional = false)
    @Column(name = "palabra")
    private String palabra;

    public Term() {
    }

    public Term(Integer idTermino) {
        this.idTermino = idTermino;
    }

    public Term(Integer idTermino, String palabra) {
        this.idTermino = idTermino;
        this.palabra = palabra;
    }
    
    public Term(String palabra) {
        this.palabra = palabra;
    }

    public Integer getIdTermino() {
        return idTermino;
    }

    public void setIdTermino(Integer idTermino) {
        this.idTermino = idTermino;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTermino != null ? idTermino.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Term)) {
            return false;
        }
        Term other = (Term) object;
        if ((this.idTermino == null && other.idTermino != null) || (this.idTermino != null && !this.idTermino.equals(other.idTermino))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Term[ idTermino=" + idTermino + " ]";
    }
    
}
