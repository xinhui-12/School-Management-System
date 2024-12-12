package entity;

import java.util.Objects;

/**
 *
 * @author Chin Tzer Khae
 */
public class ProgTutorialGroup {

    private String programmeId;
    private String tutGrpId;
    private String tutGrpName;
    private String tutor;

    public ProgTutorialGroup(String programmeId, String tutGrpId, String tutGrpName, String tutor) {
        this.programmeId = programmeId;
        this.tutGrpId = tutGrpId;
        this.tutGrpName = tutGrpName;
        this.tutor = tutor;
    }

    public String getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    public String getTutGrpId() {
        return tutGrpId;
    }

    public void setTutGrpId(String tutGrpId) {
        this.tutGrpId = tutGrpId;
    }

    public String getTutGrpName() {
        return tutGrpName;
    }

    public void setTutGrpName(String tutGrpName) {
        this.tutGrpName = tutGrpName;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    @Override
    public int hashCode() {
        int hash = 4;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProgTutorialGroup other = (ProgTutorialGroup) obj;
        if (!Objects.equals(this.tutGrpId, other.tutGrpId)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        //id
        return String.format("%-9s %-4s %-4s %-4s", getProgrammeId(), getTutGrpId(), getTutGrpName(), getTutor());
    }

}
