package com.apirest.webflux.service;

import com.apirest.webflux.document.Playlist;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlaylistServiceAgreement {

	Flux<Playlist> findAll();

	Mono<Playlist> findById(String id);

	Mono<Playlist> save(Playlist playlist);

}
