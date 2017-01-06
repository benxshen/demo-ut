package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(of= {"id"})
public class User {

	private Long id;
	private String name;
	private String email;
}
