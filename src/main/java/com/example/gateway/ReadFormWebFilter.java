package com.example.gateway;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/** @author Rob Winch */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ReadFormWebFilter implements WebFilter {
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    return exchange
        .getFormData()
        .flatMap(d -> Mono.justOrEmpty(d.getFirst("foo")))
        .then(chain.filter(exchange));
  }
}
