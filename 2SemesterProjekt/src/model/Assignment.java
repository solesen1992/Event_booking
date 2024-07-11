package model;

import java.time.LocalDateTime;

/**
 * Part of use case 2
 * 
 * Assignment class represents a shift for a volunteer with a specific role, duration, and description.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */
public class Assignment {
    private String assignmentType;
    private String assignmentDescription;
    private LocalDateTime assignmentStartTime;
    private LocalDateTime assignmentEndTime;
    private int eventId;
 
    /**
	 * Constructor.
	 * The constructor is being used to initialize the attributes when a new object is created.
	 * */
    public Assignment(String assignmentType, String assignmentDescription, LocalDateTime assignmentStartTime,
			LocalDateTime assignmentEndTime, int eventId) {
		super();
		this.assignmentType = assignmentType;
		this.assignmentDescription = assignmentDescription;
		this.assignmentStartTime = assignmentStartTime;
		this.assignmentEndTime = assignmentEndTime;
		this.eventId = eventId;
	}

    /**
	 * Getters and setters
	 * The setters allows the values of these attributes to be changed.
	 * The getters are used to access the current values of the different attributes.
	 * */
    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }
    
    public LocalDateTime getAssignmentStartTime() {
		return assignmentStartTime;
    }

	public void setAssignmentStartTime(LocalDateTime assignmentStartTime) {
		this.assignmentStartTime = assignmentStartTime;
	}
	
	public LocalDateTime getAssignmentEndTime() {
		return assignmentEndTime;
	}

	public void setAssignmentEndTime(LocalDateTime assignmentEndTime) {
		this.assignmentEndTime = assignmentEndTime;
	}
	
    public String getAssignmentDescription() {
        return assignmentDescription;
    }


    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	@Override
	public String toString() {
		return "Assignment [assignmentType=" + assignmentType + ", assignmentDescription=" + assignmentDescription
				+ ", assignmentStartTime=" + assignmentStartTime + ", assignmentEndTime=" + assignmentEndTime
				+ ", eventId=" + eventId + "]";
	}
}


