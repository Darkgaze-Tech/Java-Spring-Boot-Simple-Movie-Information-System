package com.project.movie.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomAccessDenied implements AccessDeniedHandler {
	private static final ObjectMapper mapper = new ObjectMapper();
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        PrintWriter writer = response.getWriter();
        String message = "Is not authorized";
        writer.write(mapper.writeValueAsString(new Response(403, message, accessDeniedException.getMessage())));
        writer.flush();
        writer.close();
	}
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


}
