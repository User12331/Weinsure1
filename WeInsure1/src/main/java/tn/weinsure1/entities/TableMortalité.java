package tn.weinsure1.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="table_mortalite")
public class TableMortalité  implements Serializable{
	
		@Id
		@GeneratedValue (strategy = GenerationType.IDENTITY)
		@Column(name="IdTable")
		private Long idTable ;
		

	@Column(name="x")
		private int x;
		
		@Column(name="TD99")
		private float Td_99;

		@Column(name="TV99")
		private float Tv_99;
}

