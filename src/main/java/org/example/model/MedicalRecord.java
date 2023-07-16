package org.example.model;


import lombok.Data;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
@Provider
@Produces(MediaType.APPLICATION_JSON)
@XmlRootElement

@Data
public class MedicalRecord {
  
    private String id;

    private String patientName;

   
    private Date dateOfBirth;
    private String iduser;

    



	public MedicalRecord(String id, String patientName, Date dateOfBirth, String bloodType, String iduser) {
		this.id = id;
		this.patientName = patientName;
		this.dateOfBirth = dateOfBirth;
		this.bloodType = bloodType;
		this.iduser = iduser;
	}

	public MedicalRecord() {

	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPatientName() {
		return patientName;
	}


	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}


	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getBloodType() {
		return bloodType;
	}


	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}


	public String getIduser(String iduser) {
		return this.iduser;
	}


	public void setIduser(String iduser) {
		this.iduser = iduser;
	}


	private String bloodType;
/*
    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalRecordAllergy> allergies;

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalRecordCondition> conditions;

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalRecordMedication> medications;
*/
    // ...
}













/*
@Entity
@Table(name = "medical_record") // Spécifiez le nom de la table dans la base de données
@Data
public class MedicalRecord {
    @Id
    private String id;

    @Column(name = "patient_name")
    private String patientName;

    @Temporal(TemporalType.DATE) // Indiquez le type de persistance de la date (seulement la date sans l'heure)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "blood_type")
    private String bloodType;

    @ElementCollection // Indiquez qu'il s'agit d'une collection d'éléments (allergies)

    @Column(name = "allergy") // Spécifiez le nom de la colonne qui contiendra les valeurs d'allergies
    private List<String> allergies;

    @ElementCollection // Indiquez qu'il s'agit d'une collection d'éléments (médications)

    @Column(name = "medication") // Spécifiez le nom de la colonne qui contiendra les valeurs de médications
    private List<String> medications;

    @ElementCollection // Indiquez qu'il s'agit d'une collection d'éléments (conditions)

    @Column(name = "condition") // Spécifiez le nom de la colonne qui contiendra les valeurs de conditions
    private List<String> conditions;
    public MedicalRecord(String id, String patientName, Date dateOfBirth, String bloodType, List<String> allergies, List<String> medications, List<String> conditions) {
        this.id = id;
        this.patientName = patientName;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.allergies = allergies;
        this.medications = medications;
        this.conditions = conditions;
    }

    public MedicalRecord() {

    }

    public static MedicalRecord fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, MedicalRecord.class);
    }

    public static List<MedicalRecord> medicalRecordsFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<MedicalRecord>>() {}.getType());
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getConditions() {
        return conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }
}
*/

