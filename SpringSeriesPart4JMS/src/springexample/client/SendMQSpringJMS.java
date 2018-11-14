package springexample.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author naveen balani
 */

public class SendMQSpringJMS {
	
	public static void main(String[] args){
	try
	 {
	System.out.println("SendMQSpringJMS started");
   
    
    ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] {
        "spring-mqseries-jms.xml"
    });
    
	System.out.println("Classpath loaded");
	

	
	JMSSender jmsSender = (JMSSender)
	appContext.getBean("jmsSender");
	
	jmsSender.sendMesage();
	
	System.out.println("SendMQSpringJMS end");
	 }
	catch(Exception e){
		e.printStackTrace();
	}
	}

}
