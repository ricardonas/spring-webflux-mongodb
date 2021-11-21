package com.apirest.webflux.handler;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.LocalTime;

@Component
public class PlaylistHandler {

	@Autowired
	private PlaylistService playlistService;

	public Mono<ServerResponse> findAll(
		  final ServerRequest serverRequest) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			  .body(playlistService.findAll(), Playlist.class);
	}

	public Mono<ServerResponse> findAllEvents(
		  final ServerRequest serverRequest) {
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));
		Flux<Playlist> events = this.playlistService.findAll();
		final Flux<Tuple2<Long, Playlist>> zip = Flux.zip(interval, events);

		return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(Mono.from(Flux.interval(Duration.ofSeconds(1))
			  .map(sequence  -> ServerSentEvent.builder()
					.id(String.valueOf(sequence))
					.event("periodic-event")
					.data("SSE - " + LocalTime.now().toString())
					.build())), ServerSentEvent.class);
	}

	public Mono<ServerResponse> findById(
		  final ServerRequest serverRequest) {
		final String id = serverRequest.pathVariable("id");
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			  .body(playlistService.findById(id), Playlist.class);
	}

	public Mono<ServerResponse> save(
		  final ServerRequest serverRequest) {
		final Mono<Playlist> playlist = serverRequest.bodyToMono(Playlist.class);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			  .body(BodyInserters.fromPublisher(playlist.flatMap(playlistService::save), Playlist.class));
	}

}
