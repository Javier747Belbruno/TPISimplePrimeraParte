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
@Table(name = "documentos", catalog = "corpus", schema = "")
@NamedQueries({
    @NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d"),
    @NamedQuery(name = "Document.findByIdDocumento", query = "SELECT d FROM Document d WHERE d.idDocumento = :idDocumento"),
    @NamedQuery(name = "Document.findByNombre", query = "SELECT d FROM Document d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Document.getCount", query = "SELECT COUNT(d) FROM Document d")})
public class Document implements Serializable,DalEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_documento")
    private Integer idDocumento;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    public Document() {
    }

    public Document( String nombre) {
        this.nombre = nombre;
    }
/*
    public Document(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }*/

    public Document(Integer idDocumento, String nombre) {
        this.idDocumento = idDocumento;
        this.nombre = nombre;
    }


    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumento != null ? idDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.idDocumento == null && other.idDocumento != null) || (this.idDocumento != null && !this.idDocumento.equals(other.idDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Document[ idDocumento=" + idDocumento + " ]";
    }
    
}
