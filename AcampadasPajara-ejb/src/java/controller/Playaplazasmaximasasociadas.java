/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author W7
 */
@Entity
@Table(name = "PLAYAPLAZASMAXIMASASOCIADAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Playaplazasmaximasasociadas.findAll", query = "SELECT p FROM Playaplazasmaximasasociadas p"),
    @NamedQuery(name = "Playaplazasmaximasasociadas.findByPpmaId", query = "SELECT p FROM Playaplazasmaximasasociadas p WHERE p.ppmaId = :ppmaId"),
    @NamedQuery(name = "Playaplazasmaximasasociadas.findByNplazasmaximo", query = "SELECT p FROM Playaplazasmaximasasociadas p WHERE p.nplazasmaximo = :nplazasmaximo")})
public class Playaplazasmaximasasociadas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PPMA_ID")
    private Integer ppmaId;
    @Lob
    @Size(max = 32700)
    @Column(name = "NOMBREPLAYA")
    private String nombreplaya;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NPLAZASMAXIMO")
    private int nplazasmaximo;

    public Playaplazasmaximasasociadas() {
    }

    public Playaplazasmaximasasociadas(Integer ppmaId) {
        this.ppmaId = ppmaId;
    }

    public Playaplazasmaximasasociadas(Integer ppmaId, int nplazasmaximo) {
        this.ppmaId = ppmaId;
        this.nplazasmaximo = nplazasmaximo;
    }

    public Integer getPpmaId() {
        return ppmaId;
    }

    public void setPpmaId(Integer ppmaId) {
        this.ppmaId = ppmaId;
    }

    public String getNombreplaya() {
        return nombreplaya;
    }

    public void setNombreplaya(String nombreplaya) {
        this.nombreplaya = nombreplaya;
    }

    public int getNplazasmaximo() {
        return nplazasmaximo;
    }

    public void setNplazasmaximo(int nplazasmaximo) {
        this.nplazasmaximo = nplazasmaximo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ppmaId != null ? ppmaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Playaplazasmaximasasociadas)) {
            return false;
        }
        Playaplazasmaximasasociadas other = (Playaplazasmaximasasociadas) object;
        if ((this.ppmaId == null && other.ppmaId != null) || (this.ppmaId != null && !this.ppmaId.equals(other.ppmaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.Playaplazasmaximasasociadas[ ppmaId=" + ppmaId + " ]";
    }
    
}
