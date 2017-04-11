package pl.mlipinski.wedding.management.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import lombok.Data;
import pl.mlipinski.wedding.management.domain.common.DateTimeConverter;

/**
 * Base entity.
 */
@MappedSuperclass
@Data
public class BaseEntity implements Serializable{

    @Id
    @GeneratedValue
    protected Long id;

    @Version
    protected int version;

    @Convert(converter = DateTimeConverter.class)
    protected LocalDateTime creationTime;

    @Convert(converter = DateTimeConverter.class)
    protected LocalDateTime modificationTime;

    @PrePersist
    protected void beforeInsert() {
        if(creationTime == null) {
            creationTime = LocalDateTime.now();
        }
        if(modificationTime == null) {
            modificationTime = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void beforeUpdate() {
        modificationTime = LocalDateTime.now();
    }

}
