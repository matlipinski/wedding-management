package pl.mlipinski.wedding.management.domain.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

/**
 * Base entity.
 */
@MappedSuperclass
public class BaseEntity implements Serializable{

    @Id
    @GeneratedValue
    protected Long id;

    @Version
    protected int version;

    protected Date creationDate;

    protected Date modificationDate;

}
