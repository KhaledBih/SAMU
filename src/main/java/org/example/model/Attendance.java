package org.example.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;
@Provider
@Produces(MediaType.APPLICATION_JSON)
@XmlRootElement

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
 
    private String id;


    private Date clockIn;


    private Date clockOut;


    private String duration;

    private String idUser;

    private Boolean status;

    private String nameUser;


    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getClockIn() {
		return clockIn;
	}

	public void setClockIn(Date clockIn) {
		this.clockIn = clockIn;
	}

	public Date getClockOut() {
		return clockOut;
	}

	public void setClockOut(Date clockOut) {
		this.clockOut = clockOut;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getPhoneUser() {
		return phoneUser;
	}

	public void setPhoneUser(String phoneUser) {
		this.phoneUser = phoneUser;
	}

	public String getPosition() {
		return position;
	}


	private String phoneUser;

    private String position;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Attendance fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Attendance.class);
    }

    @Data
    @AllArgsConstructor
    public static class AttendanceList {
        @SerializedName("attendanceList")
        private List<Attendance> attendanceList;

        public String toJson() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }

        public List<Attendance> getAttendanceList() {
			return attendanceList;
		}

		public void setAttendanceList(List<Attendance> attendanceList) {
			this.attendanceList = attendanceList;
		}

		public static AttendanceList fromJson(String json) {
            Gson gson = new Gson();
            return gson.fromJson(json, AttendanceList.class);
        }
    }

	public void setPosition(String position) {
		this.position = position;
	}

}
