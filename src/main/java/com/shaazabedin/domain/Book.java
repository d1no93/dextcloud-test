package com.shaazabedin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@Table(name="books")
public class Book {

    @Id
    @Column
    private String isbn;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String summary;
}
