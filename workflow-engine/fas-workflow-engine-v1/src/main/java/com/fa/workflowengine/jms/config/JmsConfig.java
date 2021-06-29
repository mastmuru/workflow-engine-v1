/**
 * 
 */
package com.fa.workflowengine.jms.config;

import javax.jms.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.util.ErrorHandler;

/**
 * @author Muruganandam
 *
 */

@Configuration
@EnableJms
public class JmsConfig {

	private static final Logger log = LoggerFactory.getLogger(JmsConfig.class);

	// Only required due to defining myFactory in the receiver
	/****
	 * @author Muruganandam
	 * @param connectionFactory
	 * @param configurer
	 * @return
	 * @description: @EnableJms enable the @JmsListener annotated endpoints that are
	 *               created under the cover by a JmsListenerContainerFactory
	 * @description: JmsListenerContainerFactory is responsible to create the
	 *               listener container responsible for a particular endpoint
	 * @descrition: DefaultJmsListenerContainerFactory, provides the necessary
	 *              configuration options that are supported by the underlying
	 *              MessageListenerContainer
	 */
	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

		// anonymous class
		factory.setErrorHandler(new ErrorHandler() {
			@Override
			public void handleError(Throwable t) {
				log.warn("An error has occurred in the transaction", t);
			}
		});

		// lambda function
		factory.setErrorHandler(t -> log.warn("An error has occurred in the transaction", t));

		configurer.configure(factory, connectionFactory);
		return factory;
	}

	// Serialize message content to json using TextMessage
	/****
	 * @author Muruganandam
	 * @return
	 * @description: MappingJackson2MessageConverter is used to turn the payload of
	 *               a Message from serialized form to a typed Object and vice
	 *               versa.
	 * @description: We have configured MessageType.TEXT. This message type can be
	 *               used to transport text-based messages. When a client receives a
	 *               TextMessage, it is in read-only mode. If a client attempts to
	 *               write to the message at this point, a
	 *               MessageNotWriteableException is thrown.
	 */
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

}
