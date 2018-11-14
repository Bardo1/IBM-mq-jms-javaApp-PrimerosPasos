
package springexample.client;

import javax.jms.Message;
import javax.jms.TextMessage;
import org.springframework.jms.core.JmsTemplate102;


/**
 * @author naveen balani
 */
public class JMSReceiver {
	
	private JmsTemplate102 jmsTemplate102;
	
	

	/**
	 * @return Returns the jmsTemplate102.
	 */
	public JmsTemplate102 getJmsTemplate102() {
		return jmsTemplate102;
	}
	/**
	 * @param jmsTemplate102 The jmsTemplate102 to set.
	 */
	public void setJmsTemplate102(JmsTemplate102 jmsTemplate102) {
		this.jmsTemplate102 = jmsTemplate102;
	}
	
	public void processMessage(){
		Message msg = jmsTemplate102.receive("JMS_RequestResponseQueue");
		try{
			TextMessage textMessage = (TextMessage) msg;
			if( msg!=null){
			System.out.println(" Message Received -->" + textMessage.getText());
			}
			
			
		}catch(Exception e){
				e.printStackTrace();
		}

		
	}
}
