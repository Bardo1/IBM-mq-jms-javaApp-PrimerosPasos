rem This is a batch file which creates a queue manager 
@echo Creating Application.QManager
crtmqm /q MQJMS.QManager
@echo Starting Queue Manager
strmqm
strmqm MQJMS.QManager
@echo Running mqconfig which creates the queues and channel required by Application
runmqsc < Appconfig.txt
