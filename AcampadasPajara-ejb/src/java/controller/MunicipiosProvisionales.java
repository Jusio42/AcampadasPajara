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
@Table(name = "MUNICIPIOS_PROVISIONALES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MunicipiosProvisionales.findAll", query = "SELECT m FROM MunicipiosProvisionales m"),
    @NamedQuery(name = "MunicipiosProvisionales.findByMuniProvId", query = "SELECT m FROM MunicipiosProvisionales m WHERE m.muniProvId = :muniProvId"),
    @NamedQuery(name = "MunicipiosProvisionales.findByMuniProvisionalId", query = "SELECT m FROM MunicipiosProvisionales m WHERE m.muniProvisionalId = :muniProvisionalId")})
public class MunicipiosProvisionales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MUNI_PROV_ID")
    private Integer muniProvId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "MUNI_PROVISIONAL_ID")
    private String muniProvisionalId;

    public MunicipiosProvisionales() {
    }

    public MunicipiosProvisionales(Integer muniProvId) {
        this.muniProvId = muniProvId;
    }

    public MunicipiosProvisionales(Integer muniProvId, String muniProvisionalId) {
        this.muniProvId = muniProvId;
        this.muniProvisionalId = muniProvisionalId;
    }

    public Integer getMuniProvId() {
        return muniProvId;
    }

    public void setMuniProvId(Integer muniProvId) {
        this.muniProvId = muniProvId;
    }

    public String getMuniProvisionalId() {
        return muniProvisionalId;
    }

    public void setMuniProvisionalId(String muniProvisionalId) {
        this.muniProvisionalId = muniProvisionalId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (muniProvId != null ? muniProvId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MunicipiosProvisionales)) {
            return false;
        }
        MunicipiosProvisionales other = (MunicipiosProvisionales) object;
        if ((this.muniProvId == null && other.muniProvId != null) || (this.muniProvId != null && !this.muniProvId.equals(other.muniProvId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.MunicipiosProvisionales[ muniProvId=" + muniProvId + " ]";
    }
    
}
