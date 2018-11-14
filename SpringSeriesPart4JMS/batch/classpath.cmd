rem set the MQ_JAVA_INSTALL and WSDK_INSTALL variable accordingly

SET MQ_JAVA_INSTALL_PATH=C:\Installables\Mqseries53\Java
SET PATH=%PATH%;%MQ_JAVA_INSTALL_PATH%\bin;%MQ_JAVA_INSTALL_PATH%\lib;
SET SPRING_SERIES_JMS_FOLDER=C:\springjms;
SET CLASSPATH=%SPRING_SERIES_JMS_FOLDER%;%MQ_JAVA_INSTALL_PATH%\lib\jms.jar;%MQ_JAVA_INSTALL_PATH%\lib\fscontext.jar;%MQ_JAVA_INSTALL_PATH%\lib\com.ibm.mq.jar;%MQ_JAVA_INSTALL_PATH%\lib\com.ibm.mqjms.jar;%MQ_JAVA_INSTALL_PATH%\lib\com.ibm.mqbind.jar;%MQ_JAVA_INSTALL_PATH%\lib\jndi.jar;%MQ_JAVA_INSTALL_PATH%\lib\providerutil.jar;%CLASSPATH%;