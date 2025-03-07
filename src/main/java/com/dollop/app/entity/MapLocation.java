package com.dollop.app.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class MapLocation {

	   
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Integer id;
	
	   @Column(name = "map_address")
	   private String address;
	   
	   @Column(name = "map_lattitude")
	   private Double lattitude;
	   
	   @Column(name = "map_longitude")
	   private Double longitude;
	   
	   
}
