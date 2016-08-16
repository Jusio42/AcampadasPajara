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
 * @author W7
 */
@Entity
@Table(name = "HISTORIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historial.findAll", query = "SELECT h FROM Historial h"),
    @NamedQuery(name = "Historial.findByHistId", query = "SELECT h FROM Historial h WHERE h.histId = :histId"),
    @NamedQuery(name = "Historial.findByPlaya", query = "SELECT h FROM Historial h WHERE h.playa = :playa"),
    @NamedQuery(name = "Historial.findByNombrepersona", query = "SELECT h FROM Historial h WHERE h.nombrepersona = :nombrepersona"),
    @NamedQuery(name = "Historial.findByApellido", query = "SELECT h FROM Historial h WHERE h.apellido = :apellido"),
    @NamedQuery(name = "Historial.findByDni", query = "SELECT h FROM Historial h WHERE h.dni = :dni"),
    @NamedQuery(name = "Historial.findByFechaEntrada", query = "SELECT h FROM Historial h WHERE h.fechaEntrada = :fechaEntrada"),
    @NamedQuery(name = "Historial.findByFechaSalida", query = "SELECT h FROM Historial h WHERE h.fechaSalida = :fechaSalida")})
public class Historial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "HIST_ID")
    private Integer histId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "PLAYA")
    private String playa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "NOMBREPERSONA")
    private String nombrepersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "APELLIDO")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "DNI")
    private String dni;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "FECHA_ENTRADA")
    private String fechaEntrada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "FECHA_SALIDA")
    private String fechaSalida;

    public Historial() {
    }

    public Historial(Integer histId) {
        this.histId = histId;
    }

    public Historial(Integer histId, String playa, String nombrepersona, String apellido, String dni, String fechaEntrada, String fechaSalida) {
        this.histId = histId;
        this.playa = playa;
        this.nombrepersona = nombrepersona;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public Integer getHistId() {
        return histId;
    }

    public void setHistId(Integer histId) {
        this.histId = histId;
    }

    public String getPlaya() {
        return playa;
    }

    public void setPlaya(String playa) {
        this.playa = playa;
    }

    public String getNombrepersona() {
        return nombrepersona;
    }

    public void setNombrepersona(String nombrepersona) {
        this.nombrepersona = nombrepersona;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (histId != null ? histId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historial)) {
            return false;
        }
        Historial other = (Historial) object;
        if ((this.histId == null && other.histId != null) || (this.histId != null && !this.histId.equals(other.histId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Historial[ histId=" + histId + " ]";
    }
    
}
