Create war by using command in terminal "mvn clean install"
Obtained war from /target copy to /tomcat/webapps
In tomcat_directory/conf/server.xml in section

      <Host name="localhost"  appBase="webapps"
            unpackWARs="false" autoDeploy="true">

change unpackWARs from true to false.
Start tomcat in terminal from directory /tomcat/bin by entering command "./startup.sh"