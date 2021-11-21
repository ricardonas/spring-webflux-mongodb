package com.apirest.webflux;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.repository.PlaylistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
public class PersistenceHelper implements CommandLineRunner {

	private final PlaylistRepository playlistRepository;

	public PersistenceHelper(final PlaylistRepository playlistRepository) {
		this.playlistRepository = playlistRepository;
	}

	@Override
	public void run(final String... args) throws Exception {
		this.playlistRepository.deleteAll()
			  .thenMany(Flux.just("Rock", "New age", "Pop", "Jazz", "Funk", "Hard rock", "Hip-hop", "EDM", "Future bass", "Brazilian bass")
					.map(name -> new Playlist(UUID.randomUUID().toString(), name)))
			  .flatMap(playlistRepository::save)
			  .subscribe();
	}
}
