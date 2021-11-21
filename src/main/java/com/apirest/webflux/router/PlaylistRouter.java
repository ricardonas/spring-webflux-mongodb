package com.apirest.webflux.router;

import com.apirest.webflux.handler.PlaylistHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PlaylistRouter {

	@Bean
	public RouterFunction<ServerResponse> route(
		  final PlaylistHandler playlistHandler) {
		return RouterFunctions
			  .route(RequestPredicates.GET("/playlist").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), playlistHandler::findAll)
			  .andRoute(RequestPredicates.GET("/playlist/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), playlistHandler::findById)
			  .andRoute(RequestPredicates.POST("/playlist").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), playlistHandler::save)
			  .andRoute(RequestPredicates.GET("/playlist/events").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), playlistHandler::findAllEvents);
	}

}
