# Smart_Intranet
Level 2 industry based project. this consist helpdesk system and leave management system.


-----requirments-----

apache tomcat server v8.0  
mysql database  
used IDE eclipse Neon  



----configurations----

database configuration  
go to src>>webapp>>WEB-INF>>app>>applicationontext.xml

next find the dataSource bean and give the database username and password  
<property name="username" value="username"></property>  
<property name="password" value="password"></property>  

then create database as project_test


email configuration  
go to src>>webapp>>WEB-INF>>app>>applicationontext.xml  

next find the mailSender bean and give the email address and password  

<property name="username" value="youremail"/>
<property name="password" value="password"/>

----defaultuser-----

username: admin  
password: 1111  


