package lk.ijse.dep.pos.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Customer implements SuperEntity {
    @Id
    private String id;
    private String name;
    private String address;


    }


