package com.project.movie.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{
	private static final ObjectMapper mapper = new ObjectMapper();


	class Response{
		public Response(int status, String message, String error) {
			super();
			this.status = status;
			this.message = message;
			this.error = error;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}
		private int status;
		private String message;
		private String error;
	}


	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException {

			response.setContentType("application/json;charset=UTF-8");
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        PrintWriter writer = response.getWriter();
	        String message = "Is not authorized";
	        writer.write(mapper.writeValueAsString(new Response(401, message, e.getMessage())));
	        writer.flush();
	        writer.close();
	}
	
}
