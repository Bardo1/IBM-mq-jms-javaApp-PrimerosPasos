package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ibm.mq.jms.JMSC;
import com.ibm.mq.jms.MQQueueConnectionFactory;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
	  
	  SpringApplication.run(DemoApplication.class, args);
		
      try {
      /*MQ Configuration*/
      MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
      mqQueueConnectionFactory.setHostName("localhost");
      mqQueueConnectionFactory.setChannel("MQ.CHANNEL");//Enlace de comunicaciones
      mqQueueConnectionFactory.setPort(1414);
      mqQueueConnectionFactory.setQueueManager("QM_Demo");//Servicio proveedor 
     // mqQueueConnectionFactory.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP);
      
      /*Crea conexión */
      QueueConnection queueConnection = mqQueueConnectionFactory.createQueueConnection();
      queueConnection.start();

      /*Crea sesion */
      QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

      /*Crea cola response  */
      Queue queue = queueSession.createQueue("QL1");

      /*Crea mensaje de texto */
      TextMessage textMessage = queueSession.createTextMessage("Este es nuestro mensajes de prueba");
      textMessage.setJMSReplyTo(queue);
      textMessage.setJMSType("mcd://xmlns");//Tipo de mensaje
      textMessage.setJMSExpiration(2*1000);//Expiración de mensaje
      textMessage.setJMSDeliveryMode(DeliveryMode.PERSISTENT); // Modo de entrega de mensajes, ya sea persistente o no persistente

      /*Crea cola sender */
      QueueSender queueSender = queueSession.createSender(queueSession.createQueue("QL1"));
      queueSender.setTimeToLive(2*1000);
      queueSender.send(textMessage);

      /*Después de enviar un mensaje obtenemos un ID de mensaje */
      System.out.println("Después de enviar un mensaje recibimos ID de mensaje"+ textMessage.getJMSMessageID());
      System.out.println("Esto es el mensaje que enviamos "+ textMessage.getText());
      String jmsCorrelationID = " JMSCorrelationID = '" + textMessage.getJMSMessageID() + "'";

      /*Dentro de la sesión tenemos que crear el receptor de cola*/
      QueueReceiver queueReceiver = queueSession.createReceiver(queue,jmsCorrelationID);
   
      /*Recibe el mensaje de*/
      Message message = queueReceiver.receive(2*1000);
  //    System.out.println("Y hasta acá 2");   
   //   String responseMsg =  ((TextMessage) message).getText();
   //   System.out.println(responseMsg);

      
  //    System.out.println("Y hasta acá 3");
      System.out.println("Esto es la respuesta del mensaje ");
    //  System.out.println("Esto es la respuesta del mensaje "+ responseMsg);
      queueSender.close();
      queueReceiver.close();
      queueSession.close();
      queueConnection.close();


  } catch (JMSException e) {
      e.printStackTrace();
  } catch (Exception e) {
      e.printStackTrace();
 }
	}
}
