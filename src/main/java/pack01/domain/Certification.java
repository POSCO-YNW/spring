package pack01.domain;

import java.time.LocalDate;
import java.util.Date;

public class Certification {
    private Long certificationId;
    private String name;
    private String level;
    private Date date;
    private Long resumeId;

    public Certification() {
    }

    public Certification(Long certificationId, String name, String level, Date date, Long resumeId) {
        this.certificationId = certificationId;
        this.name = name;
        this.level = level;
        this.date = date;
        this.resumeId = resumeId;
    }

    public Certification(String name, String level, Date date, Long resumeId) {
        this.name = name;
        this.level = level;
        this.date = date;
        this.resumeId = resumeId;
    }

    public Long getCertificationId() {
        return certificationId;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public Date getDate() {
        return date;
    }

    public Long getResumeId() {
        return resumeId;
    }
}
