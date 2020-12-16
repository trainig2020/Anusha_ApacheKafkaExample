package io.anush.producer;

public class Message {
	
	private String msgBody;
	private String msgTitle;
	
	public Message() {
	}
	public Message(String msgBody, String msgTitle) {
		this.msgBody = msgBody;
		this.msgTitle = msgTitle;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
}