package org.sdia.customerqueryside.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Customer {
    @Id
    private String id;
    private String name;
    private String email;
}
