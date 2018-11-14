package springexample.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * @author naveen balani
 */
public class ReceiveMQSpringJMS {
	
	public static void main(String[] args){
	try
	 {
	System.out.println("ReceiveMQSpringJMS started");
   
    
    ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] {
        "spring-mqseries-jms.xml"
    });
    
	System.out.println("Classpath loaded");
	

	
	JMSReceiver jmsReceiver = (JMSReceiver)
	appContext.getBean("jmsReceiver");
	
	jmsReceiver.processMessage();
	
	System.out.println("ReceiveMQSpringJMS end");
	 }
	catch(Exception e){
		e.printStackTrace();
	}
	}

}
