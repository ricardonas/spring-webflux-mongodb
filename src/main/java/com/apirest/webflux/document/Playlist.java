package com.apirest.webflux.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Playlist {

	private String id;

	private String name;

}
