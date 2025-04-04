package com.example.current_account_service.entity

import jakarta.persistence.*

@Entity
@Table(name = "customer")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    var id: Long? = null,
    @Column(name = "name", nullable = false)
    val name: String,
    @Column(name = "surname", nullable = false)
    val surname: String,
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    val accounts: List<Account> = emptyList()
)