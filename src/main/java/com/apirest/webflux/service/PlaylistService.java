package com.apirest.webflux.service;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlaylistService implements PlaylistServiceAgreement {

	@Autowired
	private PlaylistRepository playlistRepository;

	@Override
	public Flux<Playlist> findAll() {
		return this.playlistRepository.findAll();
	}

	@Override
	public Mono<Playlist> findById(
		  final String id) {
		return this.playlistRepository.findById(id);
	}

	@Override
	public Mono<Playlist> save(
		  final Playlist playlist) {
		return this.playlistRepository.save(playlist);
	}

}
