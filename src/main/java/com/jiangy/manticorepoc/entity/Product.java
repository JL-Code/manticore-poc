package com.jiangy.manticorepoc.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">JL-Code</a>
 * <p>创建时间: 2025/7/6 </p>
 */
// 产品实体类
@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "category")
    private String category;

    @Column(name = "brand")
    private String brand;

    @Column(name = "tags")
    private String tags;
}