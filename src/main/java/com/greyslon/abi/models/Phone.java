package com.greyslon.abi.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "phones")
public class Phone {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "phonr_number")
  private String phoneNumber;

  @Column(name = "comment")
  private String comment;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "phone_client",
      joinColumns = @JoinColumn(name = "phone_id"),
      inverseJoinColumns = @JoinColumn(name = "client_id")
  )
  private Set<Client> clients;
}
