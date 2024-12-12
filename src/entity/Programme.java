package entity;

import java.util.Objects;

/**
 *
 * @author Chin Tzer Khae
 */
public class Programme {

    private String programmeId;
    private String programmeName;

    public Programme(String programmeId, String programmeName) {
        this.programmeId = programmeId;
        this.programmeName = programmeName;
    }

    public String getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Programme other = (Programme) obj;
        if (!Objects.equals(this.programmeId, other.programmeId)) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        //id
        return String.format("%-9s %-30s", getProgrammeId(), getProgrammeName());
    }

}
