package databank.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-03-03T16:54:01.137-0500")
@StaticMetamodel(PersonPojo.class)
public class PersonPojo_ {
	public static volatile SingularAttribute<PersonPojo, Integer> id;
	public static volatile SingularAttribute<PersonPojo, String> lastName;
	public static volatile SingularAttribute<PersonPojo, String> firstName;
	public static volatile SingularAttribute<PersonPojo, String> email;
	public static volatile SingularAttribute<PersonPojo, String> phoneNumber;
	public static volatile SingularAttribute<PersonPojo, LocalDateTime> created;
	public static volatile SingularAttribute<PersonPojo, LocalDateTime> updated;
	public static volatile SingularAttribute<PersonPojo, Integer> version;
	public static volatile SingularAttribute<PersonPojo, String> city;
}
