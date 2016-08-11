/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author W7
 */
@Entity
@Table(name = "PLAYADATOSPERSONAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Playadatospersonas.findAll", query = "SELECT p FROM Playadatospersonas p"),
    @NamedQuery(name = "Playadatospersonas.findByPdpId", query = "SELECT p FROM Playadatospersonas p WHERE p.pdpId = :pdpId"),
    @NamedQuery(name = "Playadatospersonas.findByPlaya", query = "SELECT p FROM Playadatospersonas p WHERE p.playa = :playa"),
    @NamedQuery(name = "Playadatospersonas.findByNombrepersona", query = "SELECT p FROM Playadatospersonas p WHERE p.nombrepersona = :nombrepersona"),
    @NamedQuery(name = "Playadatospersonas.findByApellido", query = "SELECT p FROM Playadatospersonas p WHERE p.apellido = :apellido"),
    @NamedQuery(name = "Playadatospersonas.findByDni", query = "SELECT p FROM Playadatospersonas p WHERE p.dni = :dni"),
    @NamedQuery(name = "Playadatospersonas.findByMunicipio", query = "SELECT p FROM Playadatospersonas p WHERE p.municipio = :municipio"),
    @NamedQuery(name = "Playadatospersonas.findByEmail", query = "SELECT p FROM Playadatospersonas p WHERE p.email = :email"),
    @NamedQuery(name = "Playadatospersonas.findByTelefono", query = "SELECT p FROM Playadatospersonas p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Playadatospersonas.findByFechaEntrada", query = "SELECT p FROM Playadatospersonas p WHERE p.fechaEntrada = :fechaEntrada"),
    @NamedQuery(name = "Playadatospersonas.findByFechaSalida", query = "SELECT p FROM Playadatospersonas p WHERE p.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "Playadatospersonas.findByNumPersonas", query = "SELECT p FROM Playadatospersonas p WHERE p.numPersonas = :numPersonas"),
    @NamedQuery(name = "Playadatospersonas.findByTipoAcamp", query = "SELECT p FROM Playadatospersonas p WHERE p.tipoAcamp = :tipoAcamp"),
    @NamedQuery(name = "Playadatospersonas.findByNumCasetas", query = "SELECT p FROM Playadatospersonas p WHERE p.numCasetas = :numCasetas"),
    @NamedQuery(name = "Playadatospersonas.findByCaraCase", query = "SELECT p FROM Playadatospersonas p WHERE p.caraCase = :caraCase"),
    @NamedQuery(name = "Playadatospersonas.findByMatriculaCar", query = "SELECT p FROM Playadatospersonas p WHERE p.matriculaCar = :matriculaCar"),
    @NamedQuery(name = "Playadatospersonas.findByRecoger", query = "SELECT p FROM Playadatospersonas p WHERE p.recoger = :recoger")})
public class Playadatospersonas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PDP_ID")
    private Integer pdpId;
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
    @Column(name = "MUNICIPIO")
    private String municipio;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "TELEFONO")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ENTRADA")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SALIDA")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_PERSONAS")
    private int numPersonas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "TIPO_ACAMP")
    private String tipoAcamp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_CASETAS")
    private int numCasetas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "CARA_CASE")
    private String caraCase;
    @Size(max = 144)
    @Column(name = "MATRICULA_CAR")
    private String matriculaCar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "RECOGER")
    private String recoger;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 32700)
    @Column(name = "OBSERVACIONES")
    private String observaciones;

    public Playadatospersonas() {
    }

    public Playadatospersonas(Integer pdpId) {
        this.pdpId = pdpId;
    }

    public Playadatospersonas(Integer pdpId, String playa, String nombrepersona, String apellido, String dni, String municipio, String email, String telefono, Date fechaEntrada, Date fechaSalida, int numPersonas, String tipoAcamp, int numCasetas, String caraCase, String recoger, String observaciones) {
        this.pdpId = pdpId;
        this.playa = playa;
        this.nombrepersona = nombrepersona;
        this.apellido = apellido;
        this.dni = dni;
        this.municipio = municipio;
        this.email = email;
        this.telefono = telefono;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.numPersonas = numPersonas;
        this.tipoAcamp = tipoAcamp;
        this.numCasetas = numCasetas;
        this.caraCase = caraCase;
        this.recoger = recoger;
        this.observaciones = observaciones;
    }

    public Integer getPdpId() {
        return pdpId;
    }

    public void setPdpId(Integer pdpId) {
        this.pdpId = pdpId;
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

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public String getTipoAcamp() {
        return tipoAcamp;
    }

    public void setTipoAcamp(String tipoAcamp) {
        this.tipoAcamp = tipoAcamp;
    }

    public int getNumCasetas() {
        return numCasetas;
    }

    public void setNumCasetas(int numCasetas) {
        this.numCasetas = numCasetas;
    }

    public String getCaraCase() {
        return caraCase;
    }

    public void setCaraCase(String caraCase) {
        this.caraCase = caraCase;
    }

    public String getMatriculaCar() {
        return matriculaCar;
    }

    public void setMatriculaCar(String matriculaCar) {
        this.matriculaCar = matriculaCar;
    }

    public String getRecoger() {
        return recoger;
    }

    public void setRecoger(String recoger) {
        this.recoger = recoger;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pdpId != null ? pdpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Playadatospersonas)) {
            return false;
        }
        Playadatospersonas other = (Playadatospersonas) object;
        if ((this.pdpId == null && other.pdpId != null) || (this.pdpId != null && !this.pdpId.equals(other.pdpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.Playadatospersonas[ pdpId=" + pdpId + " ]";
    }
    
}