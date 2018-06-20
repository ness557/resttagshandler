package simple.microservices.resttagshandler.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tag")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @EqualsAndHashCode
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
}
