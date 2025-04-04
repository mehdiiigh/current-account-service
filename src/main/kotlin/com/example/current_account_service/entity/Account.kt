package com.example.current_account_service.entity

import jakarta.persistence.*

@Entity
@Table(name = "account")
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    var id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    var customer: Customer,
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    val transactions: List<Transaction> = emptyList()
)