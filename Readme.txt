Copy data.txt file from resources directory to tomcat directory.
Create war by using command in terminal "mvn clean install"
Obtained war from /target copy to /tomcat/webapps
In tomcat_directory/conf/server.xml in section

      <Host name="localhost"  appBase="webapps"
            unpackWARs="false" autoDeploy="true">

change unpackWARs from true to false.
In tomcat_directory/conf/catalina.properties set shared.loader, it's path to data txt, like this

shared.loader=/home/root/apache-tomcat-9.0.14

Start tomcat in terminal from directory /tomcat/bin by entering command "./startup.sh"