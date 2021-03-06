/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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
 * @author Rubn_
 */
@Entity
@Table(name = "PLAYAPLAZASFECHAOCUPADAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Playaplazasfechaocupadas.findAll", query = "SELECT p FROM Playaplazasfechaocupadas p"),
    @NamedQuery(name = "Playaplazasfechaocupadas.findByPpfo", query = "SELECT p FROM Playaplazasfechaocupadas p WHERE p.ppfo = :ppfo"),
    @NamedQuery(name = "Playaplazasfechaocupadas.findByPlaya", query = "SELECT p FROM Playaplazasfechaocupadas p WHERE p.playa = :playa"),
    @NamedQuery(name = "Playaplazasfechaocupadas.findByFecha", query = "SELECT p FROM Playaplazasfechaocupadas p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Playaplazasfechaocupadas.findByPlazas", query = "SELECT p FROM Playaplazasfechaocupadas p WHERE p.plazas = :plazas")})
public class Playaplazasfechaocupadas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PPFO")
    private Integer ppfo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "PLAYA")
    private String playa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "FECHA")
    private String fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLAZAS")
    private int plazas;

    public Playaplazasfechaocupadas() {
    }

    public Playaplazasfechaocupadas(Integer ppfo) {
        this.ppfo = ppfo;
    }

    public Playaplazasfechaocupadas(Integer ppfo, String playa, String fecha, int plazas) {
        this.ppfo = ppfo;
        this.playa = playa;
        this.fecha = fecha;
        this.plazas = plazas;
    }

    public Integer getPpfo() {
        return ppfo;
    }

    public void setPpfo(Integer ppfo) {
        this.ppfo = ppfo;
    }

    public String getPlaya() {
        return playa;
    }

    public void setPlaya(String playa) {
        this.playa = playa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ppfo != null ? ppfo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Playaplazasfechaocupadas)) {
            return false;
        }
        Playaplazasfechaocupadas other = (Playaplazasfechaocupadas) object;
        if ((this.ppfo == null && other.ppfo != null) || (this.ppfo != null && !this.ppfo.equals(other.ppfo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Playaplazasfechaocupadas[ ppfo=" + ppfo + " ]";
    }
    
}
