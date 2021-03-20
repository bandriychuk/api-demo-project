package com.socks.api.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class UserPayload{

	@JsonProperty("password")
	private String password;

	@JsonProperty("email")
	private String email;

	@JsonProperty("username")
	private String username;

}