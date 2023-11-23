package org.mycontrib.hex.generic.util.notification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
public class Notification<T> {
	
	//constants (exemples of type of notification)
	public static final String UPDATED="updated";
	public static final String CREATED="created";
	public static final String DELETED="deleted";
	public static final String READ="read";//or "consult"
	
	
	private String type; //event/notification type (subtype in a topic)
	private String from; //may be null, sender information (information or "to avoid infinite loop")
	private T content; //main content of notification
	
	
	
	public Notification(T content,String type, String from) {
		this.type = type;
		this.from = from;
		this.content = content;
	}
	
	public Notification(T content,String type) {
		this(content,type,null);
	}
	
	public Notification(T content) {
		this(content,null,null);
	}
	
	
	
	
}
