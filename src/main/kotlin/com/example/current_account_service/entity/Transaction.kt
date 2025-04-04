package com.example.current_account_service.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "transaction")
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "amount", nullable = false)
    var amount: Double,
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    var date: LocalDateTime = LocalDateTime.now(),
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    var account: Account,
)