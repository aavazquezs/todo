package com.aavs.postgrado.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ApiModel("Tag Model")
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@ApiModelProperty(value = "tag's id")
	private Long id;
	@ApiModelProperty(value = "tag's name")
	private String name;
	@ApiModelProperty(value = "tag's color")
	private String color;
}
